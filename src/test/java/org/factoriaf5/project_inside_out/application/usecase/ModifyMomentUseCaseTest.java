package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.factoriaf5.project_inside_out.application.dto.MomentResponse;
import org.factoriaf5.project_inside_out.application.dto.UpdateMomentRequest;
import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.factoriaf5.project_inside_out.infrastructure.repository.InMemoryMomentRepository;
import org.junit.jupiter.api.Test;

class ModifyMomentUseCaseTest {

    @Test
    void shouldModifyTheSelectedMomentAndReturnItsResponse() {
        InMemoryMomentRepository repository = new InMemoryMomentRepository();
        Moment existingMoment = repository.add(new Moment(
                null,
                "Old title",
                "Old description",
                Emotion.TRISTEZA,
                LocalDate.of(2026, 7, 1),
                LocalDate.of(2026, 7, 2),
                null));
        UpdateMomentRequest request = new UpdateMomentRequest(
                "New title",
                "New description",
                Emotion.ALEGRIA,
                LocalDate.of(2026, 8, 19));
        ModifyMomentUseCase useCase = new ModifyMomentUseCase(repository);

        MomentResponse response = useCase.execute(existingMoment.getId(), request);
        Moment modifiedMoment = repository.findById(existingMoment.getId());

        assertAll(
                () -> assertEquals(request.title(), modifiedMoment.getTitle()),
                () -> assertEquals(request.description(), modifiedMoment.getDescription()),
                () -> assertEquals(request.emotion(), modifiedMoment.getEmotion()),
                () -> assertEquals(request.momentDate(), modifiedMoment.getMomentDate()),
                () -> assertEquals(existingMoment.getId(), response.id()),
                () -> assertEquals(request.title(), response.title()),
                () -> assertEquals(request.description(), response.description()),
                () -> assertEquals(request.emotion(), response.emotion()),
                () -> assertEquals(request.momentDate(), response.momentDate()));
    }
}
