package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.atomic.AtomicReference;

import org.factoriaf5.project_inside_out.domain.repository.PasswordRepository;
import org.junit.jupiter.api.Test;

class CreatePasswordUseCaseTest {

    @Test
    void shouldSaveTheProvidedPassword() {
        AtomicReference<String> savedPassword = new AtomicReference<>();
        PasswordRepository repository = new PasswordRepository() {
            @Override
            public boolean passwordExists() {
                return false;
            }

            @Override
            public void savePassword(String password) {
                savedPassword.set(password);
            }

            @Override
            public boolean verifyPassword(String password) {
                return false;
            }
        };
        CreatePasswordUseCase useCase = new CreatePasswordUseCase(repository);

        useCase.execute("new-password");

        assertEquals("new-password", savedPassword.get());
    }
}
