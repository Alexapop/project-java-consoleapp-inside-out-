package org.factoriaf5.project_inside_out.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.factoriaf5.project_inside_out.application.dto.AuthenticateUserRequest;
import org.factoriaf5.project_inside_out.application.dto.AuthenticateUserResponse;
import org.junit.jupiter.api.Test;

class AuthenticateUserUseCaseTest {

    @Test
    void shouldAuthenticateWhenPasswordIsCorrect() {
        VerifyPasswordRepositoryStub repository = new VerifyPasswordRepositoryStub(true);
        AuthenticateUserUseCase useCase = new AuthenticateUserUseCase(repository);

        AuthenticateUserResponse response = useCase.execute(
                new AuthenticateUserRequest("correct-password"));

        assertTrue(response.authenticated());
        assertEquals("correct-password", repository.receivedPassword);
    }

    @Test
    void shouldRejectAuthenticationWhenPasswordIsIncorrect() {
        VerifyPasswordRepositoryStub repository = new VerifyPasswordRepositoryStub(false);
        AuthenticateUserUseCase useCase = new AuthenticateUserUseCase(repository);

        AuthenticateUserResponse response = useCase.execute(
                new AuthenticateUserRequest("incorrect-password"));

        assertFalse(response.authenticated());
        assertEquals("incorrect-password", repository.receivedPassword);
    }

    private static class VerifyPasswordRepositoryStub extends PasswordRepositoryStub {
        private final boolean verificationResult;
        private String receivedPassword;

        private VerifyPasswordRepositoryStub(boolean verificationResult) {
            this.verificationResult = verificationResult;
        }

        @Override
        public boolean verifyPassword(String password) {
            receivedPassword = password;
            return verificationResult;
        }
    }
}
