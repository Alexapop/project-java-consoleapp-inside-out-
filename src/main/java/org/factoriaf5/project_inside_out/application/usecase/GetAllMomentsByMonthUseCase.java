package org.factoriaf5.project_inside_out.application.usecase;

import java.util.List;

import org.factoriaf5.project_inside_out.domain.repository.MomentRepository;
import org.factoriaf5.project_inside_out.domain.entities.Moment;

public class GetAllMomentsByMonthUseCase {

    private MomentRepository momentRepository;

    public GetAllMomentsByMonthUseCase(MomentRepository momentRepository) {
        this.momentRepository = momentRepository;
    }

    public List<Moment> execute(int month, int year) {
        return this.momentRepository.filterByMonth(month, year);
    }

}
