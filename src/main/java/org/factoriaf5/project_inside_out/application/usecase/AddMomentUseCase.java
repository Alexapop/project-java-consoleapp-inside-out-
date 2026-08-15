package org.factoriaf5.project_inside_out.application.usecase;

import java.time.LocalDate;

import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.factoriaf5.project_inside_out.domain.repository.MomentRepository;

public class AddMomentUseCase {
  private MomentRepository momentRepository;

public AddMomentUseCase (MomentRepository momentRepository){
 this.momentRepository = momentRepository;
}

public Moment execute ( String title,String description, Emotion emotion, LocalDate momentDate,LocalDate creationDate){

    Moment moment = new Moment(title, description, emotion, momentDate, creationDate, null);
    return momentRepository.add(moment);
}
  
}
