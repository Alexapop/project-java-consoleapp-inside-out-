package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;

import org.factoriaf5.project_inside_out.application.dto.MomentResponse;
import org.factoriaf5.project_inside_out.application.dto.UpdateMomentRequest;
import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.junit.jupiter.api.Test;

class ModifyMomentUseCaseTest {

    @Test
    void shouldModifyTheSelectedMomentAndReturnItsResponse() {
        Moment existingMoment = new Moment(
                7L,
                "Old title",
                "Old description",
                Emotion.TRISTEZA,
                LocalDate.of(2026, 7, 1),
                LocalDate.of(2026, 7, 2),
                null);
        UpdateMomentRequest request = new UpdateMomentRequest(
                "New title",
                "New description",
                Emotion.ALEGRIA,
                LocalDate.of(2026, 8, 19));
        ModifyMomentRepositoryStub repository = new ModifyMomentRepositoryStub(existingMoment);
        ModifyMomentUseCase useCase = new ModifyMomentUseCase(repository);

        MomentResponse response = useCase.execute(7L, request);

        assertAll(
                () -> assertEquals(7L, repository.requestedId),
                () -> assertSame(existingMoment, repository.modifiedMoment),
                () -> assertEquals(request.title(), existingMoment.getTitle()),
                () -> assertEquals(request.description(), existingMoment.getDescription()),
                () -> assertEquals(request.emotion(), existingMoment.getEmotion()),
                () -> assertEquals(request.momentDate(), existingMoment.getMomentDate()),
                () -> assertEquals(7L, response.id()),
                () -> assertEquals(request.title(), response.title()),
                () -> assertEquals(request.description(), response.description()),
                () -> assertEquals(request.emotion(), response.emotion()),
                () -> assertEquals(request.momentDate(), response.momentDate()));
    }

    private static class ModifyMomentRepositoryStub extends MomentRepositoryStub {
        private final Moment existingMoment;
        private Long requestedId;
        private Moment modifiedMoment;

        private ModifyMomentRepositoryStub(Moment existingMoment) {
            this.existingMoment = existingMoment;
        }

        @Override
        public Moment findById(Long id) {
            requestedId = id;
            return existingMoment;
        }

        @Override
        public Moment modify(Moment moment) {
            modifiedMoment = moment;
            return moment;
        }
    }
}
