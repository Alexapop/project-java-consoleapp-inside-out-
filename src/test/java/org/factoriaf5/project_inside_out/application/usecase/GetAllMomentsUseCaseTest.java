package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.junit.jupiter.api.Test;

class GetAllMomentsUseCaseTest {

    @Test
    void shouldReturnAllMoments() {
        List<Moment> expectedMoments = List.of(createMoment(1L), createMoment(2L));
        FindAllMomentsRepositoryStub repository = new FindAllMomentsRepositoryStub(expectedMoments);
        GetAllMomentsUseCase useCase = new GetAllMomentsUseCase(repository);

        List<Moment> result = useCase.execute();

        assertEquals(expectedMoments, result);
    }

    private static Moment createMoment(Long id) {
        return new Moment(
                id,
                "Title " + id,
                "Description " + id,
                Emotion.ALEGRIA,
                LocalDate.of(2026, 8, 19),
                LocalDate.of(2026, 8, 19),
                null);
    }

    private static class FindAllMomentsRepositoryStub extends MomentRepositoryStub {
        private final List<Moment> moments;

        private FindAllMomentsRepositoryStub(List<Moment> moments) {
            this.moments = moments;
        }

        @Override
        public List<Moment> findAllMoments() {
            return moments;
        }
    }
}
