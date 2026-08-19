package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.factoriaf5.project_inside_out.infrastructure.repository.InMemoryMomentRepository;
import org.junit.jupiter.api.Test;

class GetAllMomentsByEmotionUseCaseTest {

    @Test
    void shouldReturnMomentsFilteredByEmotion() {
        InMemoryMomentRepository repository = new InMemoryMomentRepository();
        Moment expectedMoment = repository.add(createMoment(Emotion.NOSTALGIA));
        repository.add(createMoment(Emotion.ALEGRIA));
        GetAllMomentsByEmotionUseCase useCase = new GetAllMomentsByEmotionUseCase(repository);

        List<Moment> result = useCase.execute(Emotion.NOSTALGIA);

        assertEquals(List.of(expectedMoment), result);
    }

    private static Moment createMoment(Emotion emotion) {
        return new Moment(
                null,
                "A memory",
                "A meaningful memory",
                emotion,
                LocalDate.of(2026, 8, 19),
                LocalDate.of(2026, 8, 19),
                null);
    }
}
