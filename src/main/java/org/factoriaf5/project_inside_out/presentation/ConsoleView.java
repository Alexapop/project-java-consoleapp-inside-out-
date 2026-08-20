package org.factoriaf5.project_inside_out.presentation;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.factoriaf5.project_inside_out.domain.entities.Moment;

public class ConsoleView {

    private final MomentController controller;
    private final PasswordController passwordController;
    private final Scanner scanner;

    // // Constructor injection guarantees the view has access to the controller

    public ConsoleView(
            MomentController controller,
            PasswordController passwordController) {
        this.controller = controller;
        this.passwordController = passwordController;
        this.scanner = new Scanner(System.in);
    }

    // method that boots up the terminat interface

    public void start() {

        if (!handleAuthentication()) {
            System.err.println("Acceso denegado");
            return;

        }
        // use a "running" variable, true =active, false =stopped to keep track if app
        // should stay alive
        boolean running = true;

        // use "while" to create a loop which repeat as long running stays true

        while (running) {

            printMenu();

            // 4. Freeze execution here and wait for the user to type something and hit
            // ENTER

            String choice = scanner.nextLine().trim();

            // Use 'switch' to evaluate what text string the user stored inside the 'choice'
            // variable.

            switch (choice) {
                case "1":
                    handleAddMoment();

                    break;

                case "2":
                    handleGetAllMoments();
                    break;

                case "3":
                    handleDeleteMoment();

                    break;

                case "4":
                    handleFilterByEmotion();
                    break;

                case "5":
                    handleFilterByMonth();
                    break;

                case "6":
                    handleExportMomentsToCsv();
                    break;

                case "7":
                    System.err.println("Hasta la próxima!!!");
                    // When the switch block ends, the 'while' loop detects that 'running' is false
                    // and breaks out.
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige un número del 1 al 7.");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("=== INSIDE OUT: My Diario ===");
        System.out.println("1. Añadir momento");
        System.out.println("2. Ver todos los momentos disponibles");
        System.out.println("3. Eliminar un momento");
        System.out.println("4. Filtrar los momentos según su emocion");
        System.out.println("5. Filtrar los momentos en un mes determinado");
        System.out.println("6. Exportar momentos a archivo CSV");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción:");
    }

    private boolean handleAuthentication() {

        if (!passwordController.passwordExists()) {
            System.out.println("Crea tu contraseña:");

            String newPassword = scanner.nextLine();

            passwordController.createPassword(newPassword);
            System.out.println("Contraseña creada correctamente.");

            return true;
        }

        System.out.println("Introduce tu contraseña:");

        String password = scanner.nextLine();

        return passwordController.authenticate(password);
    }

    private void handleAddMoment() {
        System.out.println("\n--- Registrar nuevo momento ---");

        System.out.println("Ingrese el título:");
        // waiting for user to write something and click Enter
        String title = scanner.nextLine();

        System.out.println("Ingresa la fecha ((dd/MM/yyyy):");
        String momentDate = scanner.nextLine();

        System.out.print("Introduce la descripción: ");
        String description = scanner.nextLine();

        System.out.print("Selecciona una emoción(ej. Alegria, Tristeza, etc.): ");
        String emotion = scanner.nextLine();

        // Pass variables to your controller implementation
        controller.addMoment(title, momentDate, description, emotion);
        System.out.println("Momento vivido añadido correctamente.");

    }

    private void handleGetAllMoments() {
        System.out.println("\n---Lista de momentos vividos:---");

        List<Moment> moments = controller.getAllMoments();

        if (moments.isEmpty()) {
            System.out.println("No hay momentos disponibles.");
            return;
        }

        for (Moment moment : moments) {
            System.out.println("ID: " + moment.getId());
            System.out.println("Ocurrió el: " + moment.getMomentDate());
            System.out.println("Título: " + moment.getTitle());
            System.out.println("Descripción: " + moment.getDescription());
            System.out.println("Emoción: " + moment.getEmotion());
            System.out.println("-----------------------------");
        }
    }

    private void handleDeleteMoment() {
        System.out.println("\n--- Eliminar un momento ---");

        System.out.print("Introduce el ID del momento: ");

        String idInput = scanner.nextLine().trim();

        // Convert the ID from String to Long
        Long id = Long.parseLong(idInput);

        // Send the ID to the controller
        controller.deleteMoment(id);

        System.out.println("Momento eliminado correctamente.");
    }

    private void handleFilterByEmotion() {
        System.out.println("\n--- Filtrar momentos por emoción ---");
        System.out.print("Introduce una emoción: ");

        String emotion = scanner.nextLine().trim();
        List<Moment> moments = controller.filterByEmotion(emotion);

        if (moments.isEmpty()) {
            System.out.println("No hay momentos con esa emoción.");
            return;
        }

        for (Moment moment : moments) {
            System.out.println("ID: " + moment.getId());
            System.out.println("Ocurrió el: " + moment.getMomentDate());
            System.out.println("Título: " + moment.getTitle());
            System.out.println("Descripción: " + moment.getDescription());
            System.out.println("Emoción: " + moment.getEmotion());
            System.out.println("-----------------------------");
        }
    }

    private void handleFilterByMonth() {
        System.out.println("\n--- Filtrar momentos por mes ---");
        System.out.print("Introduce el mes (1-12): ");
        int month = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Introduce el año: ");
        int year = Integer.parseInt(scanner.nextLine().trim());

        List<Moment> moments = controller.filterByMonth(month, year);

        if (moments.isEmpty()) {
            System.out.println("No hay momentos en ese mes.");
            return;
        }

        for (Moment moment : moments) {
            System.out.println("ID: " + moment.getId());
            System.out.println("Ocurrió el: " + moment.getMomentDate());
            System.out.println("Título: " + moment.getTitle());
            System.out.println("Descripción: " + moment.getDescription());
            System.out.println("Emoción: " + moment.getEmotion());
            System.out.println("-----------------------------");
        }
    }

    private void handleExportMomentsToCsv() {
        System.out.println("\n--- Exportar momentos a CSV ---");

        try {
            controller.exportMomentsToCsv();
            System.out.println("Momentos exportados correctamente.");
        } catch (IOException exception) {
            System.out.println("No se pudieron exportar los momentos: " + exception.getMessage());
        }
    }

}
