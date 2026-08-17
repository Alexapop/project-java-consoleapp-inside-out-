package org.factoriaf5.project_inside_out.domain.repository;

public interface PasswordRepository {

    boolean passwordExists();

    void savePassword(String password);

    boolean verifyPassword(String password);
}
