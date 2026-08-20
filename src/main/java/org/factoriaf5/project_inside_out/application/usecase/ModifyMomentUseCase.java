package org.factoriaf5.project_inside_out.application.usecase;

import java.time.LocalDate;

import org.factoriaf5.project_inside_out.application.dto.MomentResponse;
import org.factoriaf5.project_inside_out.application.dto.UpdateMomentRequest;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.factoriaf5.project_inside_out.domain.repository.MomentRepository;

public class ModifyMomentUseCase {

    private MomentRepository momentRepository;

    public ModifyMomentUseCase(MomentRepository momentRepository) {
        this.momentRepository = momentRepository;
    }

    public MomentResponse execute (Long id, UpdateMomentRequest request) {
        Moment existingMoment = momentRepository.findById(id);

        Moment momentToUpdate = new Moment(
                existingMoment.getId(),
                request.title(),
                request.description(),
                request.emotion(),
                request.momentDate(),
                existingMoment.getCreationDate(),
                LocalDate.now());

        Moment updatedMoment = momentRepository.modify(momentToUpdate);

        return new MomentResponse(
               updatedMoment.getId(),
                updatedMoment.getTitle(),
                updatedMoment.getDescription(),
                updatedMoment.getEmotion(),
                updatedMoment.getMomentDate());
    }
}
