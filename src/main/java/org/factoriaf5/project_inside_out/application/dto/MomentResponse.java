package org.factoriaf5.project_inside_out.application.dto;

import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import java.time.LocalDate;

public record MomentResponse(

                Long id,
                String title,
                String description,
                Emotion emotion,
                LocalDate momentDate) {

}
