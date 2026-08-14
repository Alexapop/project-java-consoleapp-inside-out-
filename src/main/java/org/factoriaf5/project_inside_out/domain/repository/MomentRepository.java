package org.factoriaf5.project_inside_out.domain.repository;

import java.util.List;

import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;

public interface MomentRepository {

    Moment add(Moment moment);

    Moment modify(Moment moment);

    List<Moment> findAllMoments();

    void delete(Long id);

    List<Moment> filterByEmotion(Emotion emotion);

    List<Moment> filterByMonth(int month, int year);

}
