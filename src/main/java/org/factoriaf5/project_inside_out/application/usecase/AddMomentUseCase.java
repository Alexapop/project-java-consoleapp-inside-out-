package org.factoriaf5.project_inside_out.application.usecase;

import org.factoriaf5.project_inside_out.application.dto.CreateMomentRequest;
import org.factoriaf5.project_inside_out.application.dto.MomentResponse;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.factoriaf5.project_inside_out.domain.repository.MomentRepository;

public class AddMomentUseCase {
  private MomentRepository momentRepository;

  public AddMomentUseCase(MomentRepository momentRepository) {
    this.momentRepository = momentRepository;
  }

  public MomentResponse execute(CreateMomentRequest request) {

    Moment moment = new Moment(
        null,
        request.title(),
        request.description(),
        request.emotion(),
        request.momentDate(),
        request.creationDate(),
        null);

    Moment savedMoment = momentRepository.add(moment);

    return new MomentResponse(
        savedMoment.getId(),
        savedMoment.getTitle(),
        savedMoment.getDescription(),
        savedMoment.getEmotion(),
        savedMoment.getMomentDate());
  }

}
