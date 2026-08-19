package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.junit.jupiter.api.Test;

class GetAllMomentsByEmotionUseCaseTest {

    @Test
    void shouldReturnMomentsFilteredByEmotion() {
        List<Moment> expectedMoments = List.of(createMoment());
        FilterByEmotionRepositoryStub repository = new FilterByEmotionRepositoryStub(expectedMoments);
        GetAllMomentsByEmotionUseCase useCase = new GetAllMomentsByEmotionUseCase(repository);

        List<Moment> result = useCase.execute(Emotion.NOSTALGIA);

        assertEquals(Emotion.NOSTALGIA, repository.receivedEmotion);
        assertEquals(expectedMoments, result);
    }

    private static Moment createMoment() {
        return new Moment(
                1L,
                "A memory",
                "A nostalgic memory",
                Emotion.NOSTALGIA,
                LocalDate.of(2026, 8, 19),
                LocalDate.of(2026, 8, 19),
                null);
    }

    private static class FilterByEmotionRepositoryStub extends MomentRepositoryStub {
        private final List<Moment> moments;
        private Emotion receivedEmotion;

        private FilterByEmotionRepositoryStub(List<Moment> moments) {
            this.moments = moments;
        }

        @Override
        public List<Moment> filterByEmotion(Emotion emotion) {
            receivedEmotion = emotion;
            return moments;
        }
    }
}
