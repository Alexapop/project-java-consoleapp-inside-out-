package org.factoriaf5.project_inside_out.application.usecase;

import java.time.LocalDate;

import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.factoriaf5.project_inside_out.domain.repository.MomentRepository;

public class ModifyMomentUseCase {

    private MomentRepository momentRepository;

public ModifyMomentUseCase (MomentRepository momentRepository){
    this.momentRepository=momentRepository;
}

public Moment execute ( Moment moment, String title, String description,Emotion emotion, LocalDate momentDate){
    moment.setTitle(title);
    moment.setDescription(description);
    moment.setEmotion(emotion);
    moment.setMomentDate(momentDate);

    return momentRepository.modify(moment);
}
}
