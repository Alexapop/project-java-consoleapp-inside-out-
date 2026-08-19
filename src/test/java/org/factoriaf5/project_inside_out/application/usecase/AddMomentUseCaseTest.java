package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.factoriaf5.project_inside_out.application.dto.CreateMomentRequest;
import org.factoriaf5.project_inside_out.application.dto.MomentResponse;
import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.junit.jupiter.api.Test;

class AddMomentUseCaseTest {

    @Test
    void shouldAddMomentAndReturnItsResponse() {
        LocalDate momentDate = LocalDate.of(2026, 8, 10);
        LocalDate creationDate = LocalDate.of(2026, 8, 19);
        CreateMomentRequest request = new CreateMomentRequest(
                "A special day",
                "I visited my family",
                Emotion.ALEGRIA,
                momentDate,
                creationDate);
        AddMomentRepositoryStub repository = new AddMomentRepositoryStub();
        AddMomentUseCase useCase = new AddMomentUseCase(repository);

        MomentResponse response = useCase.execute(request);

        assertAll(
                () -> assertNull(repository.addedMoment.getId()),
                () -> assertEquals(request.title(), repository.addedMoment.getTitle()),
                () -> assertEquals(request.description(), repository.addedMoment.getDescription()),
                () -> assertEquals(request.emotion(), repository.addedMoment.getEmotion()),
                () -> assertEquals(request.momentDate(), repository.addedMoment.getMomentDate()),
                () -> assertEquals(request.creationDate(), repository.addedMoment.getCreationDate()),
                () -> assertNull(repository.addedMoment.getModificationDate()),
                () -> assertEquals(1L, response.id()),
                () -> assertEquals(request.title(), response.title()),
                () -> assertEquals(request.description(), response.description()),
                () -> assertEquals(request.emotion(), response.emotion()),
                () -> assertEquals(request.momentDate(), response.momentDate()));
    }

    private static class AddMomentRepositoryStub extends MomentRepositoryStub {
        private Moment addedMoment;

        @Override
        public Moment add(Moment moment) {
            addedMoment = moment;
            return new Moment(
                    1L,
                    moment.getTitle(),
                    moment.getDescription(),
                    moment.getEmotion(),
                    moment.getMomentDate(),
                    moment.getCreationDate(),
                    moment.getModificationDate());
        }
    }
}
