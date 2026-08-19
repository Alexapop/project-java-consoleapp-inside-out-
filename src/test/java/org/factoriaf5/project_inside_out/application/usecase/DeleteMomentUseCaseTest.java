package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeleteMomentUseCaseTest {

    @Test
    void shouldDeleteMomentWithProvidedId() {
        DeleteMomentRepositoryStub repository = new DeleteMomentRepositoryStub();
        DeleteMomentUseCase useCase = new DeleteMomentUseCase(repository);

        useCase.execute(5L);

        assertEquals(5L, repository.deletedId);
    }

    private static class DeleteMomentRepositoryStub extends MomentRepositoryStub {
        private Long deletedId;

        @Override
        public void delete(Long id) {
            deletedId = id;
        }
    }
}
