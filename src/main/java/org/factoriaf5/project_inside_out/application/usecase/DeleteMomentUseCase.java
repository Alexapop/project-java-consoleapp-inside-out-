package org.factoriaf5.project_inside_out.application.usecase;

import org.factoriaf5.project_inside_out.domain.repository.MomentRepository;

public class DeleteMomentUseCase {

  private MomentRepository momentRepository;

  public DeleteMomentUseCase(MomentRepository momentRepository) {
    this.momentRepository = momentRepository;

  }

  public void execute(Long id) {
    this.momentRepository.delete(id);
  }

}
