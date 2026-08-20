package org.factoriaf5.project_inside_out.presentation;

import org.factoriaf5.project_inside_out.application.dto.AuthenticateUserRequest;
import org.factoriaf5.project_inside_out.application.dto.AuthenticateUserResponse;
import org.factoriaf5.project_inside_out.application.usecase.AuthenticateUserUseCase;
import org.factoriaf5.project_inside_out.application.usecase.CreatePasswordUseCase;

public class PasswordController {

    private CreatePasswordUseCase createPasswordUseCase;
    private AuthenticateUserUseCase authenticateUserUseCase;

    public PasswordController(CreatePasswordUseCase createPasswordUseCase,
            AuthenticateUserUseCase authenticateUserUseCase) {
        this.createPasswordUseCase = createPasswordUseCase;
        this.authenticateUserUseCase = authenticateUserUseCase;
    }

    // check if the password exists, if not create one
    // send password to CreatePassword usecase
    public boolean passwordExists() {
        return createPasswordUseCase.passwordExists();

    }

    public void createPassword(String password) {
        createPasswordUseCase.execute(password);
    }

     // send request & receive request via DTO
    public boolean authenticate(String password) {
        AuthenticateUserRequest request = new AuthenticateUserRequest(password);

        AuthenticateUserResponse response = authenticateUserUseCase.execute(request);

        return response.authenticated();
    }
}
