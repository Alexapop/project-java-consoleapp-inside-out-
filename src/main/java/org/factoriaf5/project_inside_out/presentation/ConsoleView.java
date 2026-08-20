package org.factoriaf5.project_inside_out.presentation;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.factoriaf5.project_inside_out.domain.entities.Emotion;
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

        Map<String, Runnable> menuActions = Map.of(
                "1", this::handleAddMoment,
                "2", this::handleGetAllMoments,
                "3", this::handleModifyMoment,
                "4", this::handleDeleteMoment,
                "5", this::handleFilterByEmotion,
                "6", this::handleFilterByMonth,
                "7", this::handleExportMomentsToCsv);

        // use "while" to create a loop which repeat as long running stays true

        while (running) {

            printMenu();

            // 4. Freeze execution here and wait for the user to type something and hit
            // ENTER

            String choice = scanner.nextLine().trim();

            if (choice.equals("8")) {
                System.err.println("Hasta la próxima!!!");
                running = false;
                continue;
            }

            Runnable selectedAction = menuActions.get(choice);

            if (selectedAction == null) {
                System.out.println("Opción no válida. Por favor, elige un número del 1 al 8.");
                continue;
            }

            selectedAction.run();
        }
    }

    private String readValidDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            System.out.println("Ingresa la fecha (dd/MM/yyyy):");
            String date = scanner.nextLine().trim();

            try {
                LocalDate.parse(date, formatter);
                return date;
            } catch (DateTimeParseException exception) {
                System.err.println(
                        "Fecha inválida. Utiliza el formato dd/MM/yyyy.");
            }
        }
    }

    private String readRequiredText(String message, String errorMessage) {
        while (true) {
            System.out.print(message);
            String text = scanner.nextLine().trim();

            if (!text.isBlank()) {
                return text;
            }

            System.err.println(errorMessage);
        }
    }

    private String readValidEmotion() {
        System.out.println("Emociones disponibles:");

        for (Emotion availableEmotion : Emotion.values()) {
            System.out.println("- " + availableEmotion);
        }

        while (true) {
            System.out.print("Selecciona una emoción: ");
            String emotion = scanner.nextLine().trim().toUpperCase();

            try {
                Emotion.valueOf(emotion);
                return emotion;
            } catch (IllegalArgumentException exception) {
                System.err.println(
                        "Emoción inválida. Selecciona una emoción de la lista.");
            }
        }
    }

    private void printMenu() {
        System.out.println("=== INSIDE OUT: My Diario ===");
        System.out.println("1. Añadir momento");
        System.out.println("2. Ver todos los momentos disponibles");
        System.out.println("3. Modificar un momento");
        System.out.println("4. Eliminar un momento");
        System.out.println("5. Filtrar los momentos según su emocion");
        System.out.println("6. Filtrar los momentos en un mes determinado");
        System.out.println("7. Exportar momentos a archivo CSV");
        System.out.println("8. Salir");
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

        String title = readRequiredText(
                "Ingrese el título: ",
                "El título no puede estar vacío.");

        String momentDate = readValidDate();
        String description = readRequiredText(
                "Introduce la descripción: ",
                "La descripción no puede estar vacía.");

        String emotion = readValidEmotion();

        // Pass variables to your controller implementation
        controller.addMoment(title, momentDate, description, emotion);
        System.out.println("Momento vivido añadido correctamente.");

    }

    private void handleGetAllMoments() {
        System.out.println("\n---Lista de momentos vividos:---");

        List<Moment> moments = controller.getAllMoments();
        printMoments(moments, "No hay momentos disponibles.");
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

    private void handleModifyMoment() {
        System.out.println("\n--- Modificar un momento ---");

        System.out.print("Introduce el ID del momento: ");
        Long id = Long.parseLong(scanner.nextLine().trim());

        String title = readRequiredText(
                "Introduce el nuevo título: ",
                "El título no puede estar vacío.");

        String momentDate = readValidDate();

        String description = readRequiredText(
                "Introduce la nueva descripción: ",
                "La descripción no puede estar vacía.");

        String emotion = readValidEmotion();

        controller.modifyMoment(id, title, momentDate, description, emotion);

        System.out.println("Momento modificado correctamente.");
    }

    private void handleFilterByEmotion() {
        System.out.println("\n--- Filtrar momentos por emoción ---");
        System.out.print("Introduce una emoción: ");

        String emotion = scanner.nextLine().trim();
        List<Moment> moments = controller.filterByEmotion(emotion);
        printMoments(moments, "No hay momentos con esa emoción.");
    }

    private void handleFilterByMonth() {
        System.out.println("\n--- Filtrar momentos por mes ---");
        System.out.print("Introduce el mes (1-12): ");
        int month = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Introduce el año: ");
        int year = Integer.parseInt(scanner.nextLine().trim());

        List<Moment> moments = controller.filterByMonth(month, year);
        printMoments(moments, "No hay momentos en ese mes.");
    }

    private void printMoments(List<Moment> moments, String emptyMessage) {
        if (moments.isEmpty()) {
            System.out.println(emptyMessage);
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
