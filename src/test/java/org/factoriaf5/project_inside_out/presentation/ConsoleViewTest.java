package org.factoriaf5.project_inside_out.presentation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.factoriaf5.project_inside_out.application.usecase.AddMomentUseCase;
import org.factoriaf5.project_inside_out.application.usecase.DeleteMomentUseCase;
import org.factoriaf5.project_inside_out.application.usecase.ExportMomentsToCSVUseCase;
import org.factoriaf5.project_inside_out.application.usecase.GetAllMomentsByEmotionUseCase;
import org.factoriaf5.project_inside_out.application.usecase.GetAllMomentsByMonthUseCase;
import org.factoriaf5.project_inside_out.application.usecase.GetAllMomentsUseCase;
import org.factoriaf5.project_inside_out.domain.repository.MomentRepository;
import org.factoriaf5.project_inside_out.infrastructure.repository.InMemoryMomentRepository;
import org.junit.jupiter.api.Test;

class ConsoleViewTest {

    @Test
    void shouldDeleteMoment() {
        String result = runView("""
                1
                A moment to delete
                19/08/2026
                A description
                TRISTEZA
                3
                1
                2
                7
                """);

        assertTrue(result.contains("Momento eliminado correctamente."));
        assertTrue(result.contains("No hay momentos disponibles."));
    }

    @Test
    void shouldDisplayMessageWhenEmotionHasNoMoments() {
        String result = runView("""
                4
                ALEGRIA
                7
                """);

        assertTrue(result.contains("No hay momentos con esa emoción."));
    }

    private String runView(String input) {
        InputStream originalInput = System.in;
        PrintStream originalOutput = System.out;
        PrintStream originalError = System.err;

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

            ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(consoleOutput, true, StandardCharsets.UTF_8);
            System.setOut(printStream);
            System.setErr(printStream);

            createView().start();

            return consoleOutput.toString(StandardCharsets.UTF_8);
        } finally {
            System.setIn(originalInput);
            System.setOut(originalOutput);
            System.setErr(originalError);
        }
    }

    private ConsoleView createView() {
        // Use one repository instance for all operations
        MomentRepository repository = new InMemoryMomentRepository();

        MomentController controller = new MomentController(
                new AddMomentUseCase(repository),
                new GetAllMomentsUseCase(repository),
                new DeleteMomentUseCase(repository),
                new GetAllMomentsByEmotionUseCase(repository),
                new GetAllMomentsByMonthUseCase(repository),
                new ExportMomentsToCSVUseCase(repository));

        return new ConsoleView(controller);
    }
}
