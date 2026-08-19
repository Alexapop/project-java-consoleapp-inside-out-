package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CreatePasswordUseCaseTest {

    @Test
    void shouldSaveTheProvidedPassword() {
        SavePasswordRepositoryStub repository = new SavePasswordRepositoryStub();
        CreatePasswordUseCase useCase = new CreatePasswordUseCase(repository);

        useCase.execute("new-password");

        assertEquals("new-password", repository.savedPassword);
    }

    private static class SavePasswordRepositoryStub extends PasswordRepositoryStub {
        private String savedPassword;

        @Override
        public void savePassword(String password) {
            savedPassword = password;
        }
    }
}
