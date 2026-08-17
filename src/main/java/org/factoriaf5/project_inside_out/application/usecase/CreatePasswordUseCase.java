package org.factoriaf5.project_inside_out.application.usecase;

import org.factoriaf5.project_inside_out.domain.repository.PasswordRepository;

public class CreatePasswordUseCase {

    private PasswordRepository passwordRepository;

    public CreatePasswordUseCase(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public void execute(String password) {
        passwordRepository.savePassword(password);

    }

}
