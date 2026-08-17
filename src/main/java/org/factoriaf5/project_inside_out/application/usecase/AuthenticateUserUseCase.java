package org.factoriaf5.project_inside_out.application.usecase;

import org.factoriaf5.project_inside_out.domain.repository.PasswordRepository;

public class AuthenticateUserUseCase {

    private PasswordRepository passwordRepository;

    public AuthenticateUserUseCase(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public boolean execute(String password) {
       return passwordRepository.verifyPassword(password);
}
}
