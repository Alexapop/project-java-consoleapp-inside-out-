package org.factoriaf5.project_inside_out.application.usecase;

import org.factoriaf5.project_inside_out.application.dto.AuthenticateUserRequest;
import org.factoriaf5.project_inside_out.application.dto.AuthenticateUserResponse;
import org.factoriaf5.project_inside_out.domain.repository.PasswordRepository;

public class AuthenticateUserUseCase {

    private PasswordRepository passwordRepository;

    public AuthenticateUserUseCase(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public AuthenticateUserResponse execute(AuthenticateUserRequest request)
     {
       return new AuthenticateUserResponse(passwordRepository.verifyPassword(request.password()));
}
}
