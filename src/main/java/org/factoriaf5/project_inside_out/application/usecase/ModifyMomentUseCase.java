package org.factoriaf5.project_inside_out.application.usecase;

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
        Moment moment = momentRepository.findById(id);

        moment.setTitle(request.title());
        moment.setDescription(request.description());
        moment.setEmotion(request.emotion());
        moment.setMomentDate(request.momentDate());

        Moment updatedMoment = momentRepository.modify(moment);

        return new MomentResponse(
               updatedMoment.getId(),
                updatedMoment.getTitle(),
                updatedMoment.getDescription(),
                updatedMoment.getEmotion(),
                updatedMoment.getMomentDate());
    }
}
