package org.factoriaf5.project_inside_out.presentation;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.factoriaf5.project_inside_out.application.dto.CreateMomentRequest;
import org.factoriaf5.project_inside_out.application.dto.MomentResponse;
import org.factoriaf5.project_inside_out.application.dto.UpdateMomentRequest;
import org.factoriaf5.project_inside_out.application.usecase.AddMomentUseCase;
import org.factoriaf5.project_inside_out.application.usecase.DeleteMomentUseCase;
import org.factoriaf5.project_inside_out.application.usecase.ExportMomentsToCSVUseCase;
import org.factoriaf5.project_inside_out.application.usecase.GetAllMomentsUseCase;
import org.factoriaf5.project_inside_out.application.usecase.GetAllMomentsByEmotionUseCase;
import org.factoriaf5.project_inside_out.application.usecase.GetAllMomentsByMonthUseCase;
import org.factoriaf5.project_inside_out.application.usecase.ModifyMomentUseCase;
import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;

public class MomentController {

    // Store the use case responsible for adding a new moment
    private final AddMomentUseCase addMomentUseCase;

    // Store the use case responsible for retrieving all moments
    private final GetAllMomentsUseCase getAllMomentsUseCase;

    private final DeleteMomentUseCase deleteMomentUseCase;

    private final ModifyMomentUseCase modifyMomentUseCase;

    private final GetAllMomentsByEmotionUseCase getAllMomentsByEmotionUseCase;

    private final GetAllMomentsByMonthUseCase getAllMomentsByMonthUseCase;

    private final ExportMomentsToCSVUseCase exportMomentsToCSVUseCase;

    // Receive the use cases required by the controller
    public MomentController(
            AddMomentUseCase addMomentUseCase,
            GetAllMomentsUseCase getAllMomentsUseCase,
            DeleteMomentUseCase deleteMomentUseCase,
            ModifyMomentUseCase modifyMomentUseCase,
            GetAllMomentsByEmotionUseCase getAllMomentsByEmotionUseCase,
            GetAllMomentsByMonthUseCase getAllMomentsByMonthUseCase,
            ExportMomentsToCSVUseCase exportMomentsToCSVUseCase) {

        // Save the received AddMomentUseCase in this controlle
        // Save the received GetAllMomentsUseCase in this controller,etc.
        this.addMomentUseCase = addMomentUseCase;
        this.getAllMomentsUseCase = getAllMomentsUseCase;
        this.deleteMomentUseCase = deleteMomentUseCase;
        this.modifyMomentUseCase = modifyMomentUseCase;
        this.getAllMomentsByEmotionUseCase = getAllMomentsByEmotionUseCase;
        this.getAllMomentsByMonthUseCase = getAllMomentsByMonthUseCase;
        this.exportMomentsToCSVUseCase = exportMomentsToCSVUseCase;
    }

    // Receive the text entered by the user and add a new moment
    public MomentResponse addMoment(
            String title,
            String momentDate,
            String description,
            String emotion) {

        // Define the date format expected from the console
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Convert the date from String to LocalDate
        LocalDate parsedMomentDate = LocalDate.parse(momentDate, dateFormatter);

        // Clean the emotion text and convert it to uppercase
        String formattedEmotion = emotion.trim().toUpperCase();

        // Convert the emotion text to an Emotion enum value
        Emotion parsedEmotion = Emotion.valueOf(formattedEmotion);

        // Create the request required by AddMomentUseCase
        CreateMomentRequest request = new CreateMomentRequest(
                title,
                description,
                parsedEmotion,
                parsedMomentDate,
                LocalDate.now());

        // Execute the use case and return the created moment
        return addMomentUseCase.execute(request);
    }

    // Retrieve and return every stored moment
    public List<Moment> getAllMoments() {
        return getAllMomentsUseCase.execute();
    }

    // Delete the moment identified by the received ID
    public void deleteMoment(Long id) {
        deleteMomentUseCase.execute(id);
    }

    public MomentResponse modifyMoment(
            Long id,
            String title,
            String momentDate,
            String description,
            String emotion) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedMomentDate = LocalDate.parse(momentDate, dateFormatter);
        String formattedEmotion = emotion.trim().toUpperCase();
        Emotion parsedEmotion = Emotion.valueOf(formattedEmotion);

        UpdateMomentRequest request = new UpdateMomentRequest(
                title,
                description,
                parsedEmotion,
                parsedMomentDate);

        return modifyMomentUseCase.execute(id, request);
    }

    public List<Moment> filterByEmotion(String emotion) {
        String formattedEmotion = emotion.trim().toUpperCase();
        Emotion parsedEmotion = Emotion.valueOf(formattedEmotion);
        return getAllMomentsByEmotionUseCase.execute(parsedEmotion);
    }

    public List<Moment> filterByMonth(int month, int year) {
        return getAllMomentsByMonthUseCase.execute(month, year);
    }

    public void exportMomentsToCsv() throws IOException {
        exportMomentsToCSVUseCase.execute();
    }

}
