package org.factoriaf5.project_inside_out.application.dto;

import java.time.LocalDate;

import org.factoriaf5.project_inside_out.domain.entities.Emotion;

public record CreateMomentRequest (
       
        String title,
        String description,
        Emotion emotion,
        LocalDate momentDate,
        LocalDate creationDate
){

}
