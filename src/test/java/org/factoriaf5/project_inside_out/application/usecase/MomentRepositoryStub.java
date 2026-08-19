package org.factoriaf5.project_inside_out.application.usecase;

import java.util.List;

import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.factoriaf5.project_inside_out.domain.repository.MomentRepository;

abstract class MomentRepositoryStub implements MomentRepository {

    @Override
    public Moment add(Moment moment) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Moment findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Moment modify(Moment moment) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Moment> findAllMoments() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Moment> filterByEmotion(Emotion emotion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Moment> filterByMonth(int month, int year) {
        throw new UnsupportedOperationException();
    }
}
