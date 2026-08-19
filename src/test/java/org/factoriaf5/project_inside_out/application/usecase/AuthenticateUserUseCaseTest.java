package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicReference;

import org.factoriaf5.project_inside_out.application.dto.AuthenticateUserRequest;
import org.factoriaf5.project_inside_out.application.dto.AuthenticateUserResponse;
import org.factoriaf5.project_inside_out.domain.repository.PasswordRepository;
import org.junit.jupiter.api.Test;

class AuthenticateUserUseCaseTest {

    @Test
    void shouldAuthenticateWhenPasswordIsCorrect() {
        AtomicReference<String> receivedPassword = new AtomicReference<>();
        PasswordRepository repository = createPasswordRepository(true, receivedPassword);
        AuthenticateUserUseCase useCase = new AuthenticateUserUseCase(repository);

        AuthenticateUserResponse response = useCase.execute(
                new AuthenticateUserRequest("correct-password"));

        assertTrue(response.authenticated());
        assertEquals("correct-password", receivedPassword.get());
    }

    @Test
    void shouldRejectAuthenticationWhenPasswordIsIncorrect() {
        AtomicReference<String> receivedPassword = new AtomicReference<>();
        PasswordRepository repository = createPasswordRepository(false, receivedPassword);
        AuthenticateUserUseCase useCase = new AuthenticateUserUseCase(repository);

        AuthenticateUserResponse response = useCase.execute(
                new AuthenticateUserRequest("incorrect-password"));

        assertFalse(response.authenticated());
        assertEquals("incorrect-password", receivedPassword.get());
    }

    private static PasswordRepository createPasswordRepository(
            boolean verificationResult,
            AtomicReference<String> receivedPassword) {
        return new PasswordRepository() {
            @Override
            public boolean passwordExists() {
                return false;
            }

            @Override
            public void savePassword(String password) {
            }

            @Override
            public boolean verifyPassword(String password) {
                receivedPassword.set(password);
                return verificationResult;
            }
        };
    }
}
