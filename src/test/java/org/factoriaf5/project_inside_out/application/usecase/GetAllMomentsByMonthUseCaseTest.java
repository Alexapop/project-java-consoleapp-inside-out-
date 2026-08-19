package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.factoriaf5.project_inside_out.infrastructure.repository.InMemoryMomentRepository;
import org.junit.jupiter.api.Test;

class GetAllMomentsByMonthUseCaseTest {

    @Test
    void shouldReturnMomentsFilteredByMonthAndYear() {
        InMemoryMomentRepository repository = new InMemoryMomentRepository();
        Moment expectedMoment = repository.add(createMoment(LocalDate.of(2026, 8, 10)));
        repository.add(createMoment(LocalDate.of(2026, 7, 10)));
        GetAllMomentsByMonthUseCase useCase = new GetAllMomentsByMonthUseCase(repository);

        List<Moment> result = useCase.execute(8, 2026);

        assertEquals(List.of(expectedMoment), result);
    }

    private static Moment createMoment(LocalDate momentDate) {
        return new Moment(
                null,
                "Summer day",
                "A summer moment",
                Emotion.ALEGRIA,
                momentDate,
                LocalDate.of(2026, 8, 11),
                null);
    }
}
