package org.factoriaf5.project_inside_out.application.usecase;

import java.util.List;

import org.factoriaf5.project_inside_out.domain.repository.MomentRepository;
import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;

public class GetAllMomentsByEmotionUseCase {
    private MomentRepository momentRepository;

    public GetAllMomentsByEmotionUseCase(MomentRepository momentRepository) {
        this.momentRepository = momentRepository;
    }

    public List<Moment> execute(Emotion emotion) {
        return momentRepository.filterByEmotion(emotion);

    }
}
