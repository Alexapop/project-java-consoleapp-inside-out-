package org.factoriaf5.project_inside_out.application.usecase;

import org.factoriaf5.project_inside_out.domain.repository.PasswordRepository;

public class CreatePasswordUseCase {

    private PasswordRepository passwordRepository;

    public CreatePasswordUseCase(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public boolean passwordExists() {
        return passwordRepository.passwordExists();
    }

    public void execute(String password) {

        if (passwordRepository.passwordExists()) {
            throw new IllegalStateException("La contraseña ya existe.");
        }
        passwordRepository.savePassword(password);

    }

}
