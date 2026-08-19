package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.factoriaf5.project_inside_out.infrastructure.repository.InMemoryMomentRepository;
import org.junit.jupiter.api.Test;

class DeleteMomentUseCaseTest {

    @Test
    void shouldDeleteMomentWithProvidedId() {
        InMemoryMomentRepository repository = new InMemoryMomentRepository();
        Moment savedMoment = repository.add(new Moment(
                null,
                "A moment",
                "A description",
                Emotion.ALEGRIA,
                LocalDate.of(2026, 8, 19),
                LocalDate.of(2026, 8, 19),
                null));
        DeleteMomentUseCase useCase = new DeleteMomentUseCase(repository);

        useCase.execute(savedMoment.getId());

        assertTrue(repository.findAllMoments().isEmpty());
    }
}
