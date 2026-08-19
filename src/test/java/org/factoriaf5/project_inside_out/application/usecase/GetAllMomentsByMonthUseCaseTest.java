package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.junit.jupiter.api.Test;

class GetAllMomentsByMonthUseCaseTest {

    @Test
    void shouldReturnMomentsFilteredByMonthAndYear() {
        List<Moment> expectedMoments = List.of(createMoment());
        FilterByMonthRepositoryStub repository = new FilterByMonthRepositoryStub(expectedMoments);
        GetAllMomentsByMonthUseCase useCase = new GetAllMomentsByMonthUseCase(repository);

        List<Moment> result = useCase.execute(8, 2026);

        assertEquals(8, repository.receivedMonth);
        assertEquals(2026, repository.receivedYear);
        assertEquals(expectedMoments, result);
    }

    private static Moment createMoment() {
        return new Moment(
                1L,
                "Summer day",
                "A moment from August",
                Emotion.ALEGRIA,
                LocalDate.of(2026, 8, 10),
                LocalDate.of(2026, 8, 11),
                null);
    }

    private static class FilterByMonthRepositoryStub extends MomentRepositoryStub {
        private final List<Moment> moments;
        private int receivedMonth;
        private int receivedYear;

        private FilterByMonthRepositoryStub(List<Moment> moments) {
            this.moments = moments;
        }

        @Override
        public List<Moment> filterByMonth(int month, int year) {
            receivedMonth = month;
            receivedYear = year;
            return moments;
        }
    }
}
