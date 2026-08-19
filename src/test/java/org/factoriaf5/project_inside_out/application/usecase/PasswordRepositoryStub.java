package org.factoriaf5.project_inside_out.application.usecase;

import org.factoriaf5.project_inside_out.domain.repository.PasswordRepository;

abstract class PasswordRepositoryStub implements PasswordRepository {

    @Override
    public boolean passwordExists() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void savePassword(String password) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean verifyPassword(String password) {
        throw new UnsupportedOperationException();
    }
}
