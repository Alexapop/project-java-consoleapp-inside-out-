package org.factoriaf5.project_inside_out.application.usecase;

import java.util.List;

import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.factoriaf5.project_inside_out.domain.repository.MomentRepository;

public class GetAllMomentsUseCase {

    private MomentRepository momentRepository;

    public GetAllMomentsUseCase(MomentRepository momentRepository) {
        this.momentRepository = momentRepository;
    }

    public List<Moment> execute() {
        return momentRepository.findAllMoments();
    }
}
