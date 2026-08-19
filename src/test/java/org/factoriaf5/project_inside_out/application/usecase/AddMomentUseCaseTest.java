package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.factoriaf5.project_inside_out.application.dto.CreateMomentRequest;
import org.factoriaf5.project_inside_out.application.dto.MomentResponse;
import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.factoriaf5.project_inside_out.infrastructure.repository.InMemoryMomentRepository;
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
        InMemoryMomentRepository repository = new InMemoryMomentRepository();
        AddMomentUseCase useCase = new AddMomentUseCase(repository);

        MomentResponse response = useCase.execute(request);
        Moment savedMoment = repository.findById(response.id());

        assertAll(
                () -> assertEquals(1L, savedMoment.getId()),
                () -> assertEquals(request.title(), savedMoment.getTitle()),
                () -> assertEquals(request.description(), savedMoment.getDescription()),
                () -> assertEquals(request.emotion(), savedMoment.getEmotion()),
                () -> assertEquals(request.momentDate(), savedMoment.getMomentDate()),
                () -> assertEquals(request.creationDate(), savedMoment.getCreationDate()),
                () -> assertNull(savedMoment.getModificationDate()),
                () -> assertEquals(1L, response.id()),
                () -> assertEquals(request.title(), response.title()),
                () -> assertEquals(request.description(), response.description()),
                () -> assertEquals(request.emotion(), response.emotion()),
                () -> assertEquals(request.momentDate(), response.momentDate()));
    }
}
