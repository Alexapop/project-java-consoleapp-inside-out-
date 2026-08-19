package org.factoriaf5.project_inside_out.domain.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class MomentTest {

    @Test
    void shouldCreateMomentWithProvidedData() {
        Long id = 1L;
        LocalDate momentDate = LocalDate.of(2026, 8, 3);
        LocalDate creationDate = LocalDate.of(2026, 8, 1);
        LocalDate modificationDate = LocalDate.of(2026, 8, 12);

        Moment moment = new Moment(
                id,
                "Un día especial",
                "Visitar a mi familia",
                Emotion.ALEGRIA,
                momentDate,
                creationDate,
                modificationDate);

        assertAll(
                () -> assertEquals("Un día especial", moment.getTitle()),
                () -> assertEquals("Visitar a mi familia", moment.getDescription()),
                () -> assertEquals(Emotion.ALEGRIA, moment.getEmotion()),
                () -> assertEquals(momentDate, moment.getMomentDate()),
                () -> assertEquals(creationDate, moment.getCreationDate()),
                () -> assertEquals(modificationDate, moment.getModificationDate()));
    }

    @Test
    void shouldUpdateTitle() {
        Moment moment = createMoment();

        moment.setTitle("Nuevo título");

        assertEquals("Nuevo título", moment.getTitle());
    }

    @Test
    void shouldUpdateDescription() {
        Moment moment = createMoment();

        moment.setDescription("Nueva descripción");

        assertEquals("Nueva descripción", moment.getDescription());
    }

    @Test
    void shouldUpdateEmotion() {
        Moment moment = createMoment();

        moment.setEmotion(Emotion.NOSTALGIA);

        assertEquals(Emotion.NOSTALGIA, moment.getEmotion());
    }

    @Test
    void shouldUpdateMomentDate() {
        Moment moment = createMoment();
        LocalDate newDate = LocalDate.of(2026, 8, 14);

        moment.setMomentDate(newDate);

        assertEquals(newDate, moment.getMomentDate());
    }

    private Moment createMoment() {
        return new Moment(
                 1L,
                "Título",
                "Descripción",
                Emotion.ALEGRIA,
                LocalDate.of(2026, 8, 10),
                LocalDate.of(2026, 8, 11),
                LocalDate.of(2026, 8, 11));
    }
}
