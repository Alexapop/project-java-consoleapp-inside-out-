package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.factoriaf5.project_inside_out.infrastructure.repository.InMemoryMomentRepository;
import org.junit.jupiter.api.Test;

class GetAllMomentsUseCaseTest {

    @Test
    void shouldReturnAllMoments() {
        InMemoryMomentRepository repository = new InMemoryMomentRepository();
        List<Moment> expectedMoments = List.of(
                repository.add(createMoment()),
                repository.add(createMoment()));
        GetAllMomentsUseCase useCase = new GetAllMomentsUseCase(repository);

        List<Moment> result = useCase.execute();

        assertEquals(expectedMoments, result);
    }

    private static Moment createMoment() {
        return new Moment(
                null,
                "A title",
                "A description",
                Emotion.ALEGRIA,
                LocalDate.of(2026, 8, 19),
                LocalDate.of(2026, 8, 19),
                null);
    }
}
