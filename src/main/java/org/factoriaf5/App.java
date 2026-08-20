package org.factoriaf5;

import org.factoriaf5.project_inside_out.application.usecase.AddMomentUseCase;
import org.factoriaf5.project_inside_out.application.usecase.AuthenticateUserUseCase;
import org.factoriaf5.project_inside_out.application.usecase.CreatePasswordUseCase;
import org.factoriaf5.project_inside_out.application.usecase.DeleteMomentUseCase;
import org.factoriaf5.project_inside_out.application.usecase.GetAllMomentsUseCase;
import org.factoriaf5.project_inside_out.domain.repository.MomentRepository;
import org.factoriaf5.project_inside_out.domain.repository.PasswordRepository;
import org.factoriaf5.project_inside_out.infrastructure.repository.InMemoryMomentRepository;
import org.factoriaf5.project_inside_out.presentation.ConsoleView;
import org.factoriaf5.project_inside_out.presentation.MomentController;
import org.factoriaf5.project_inside_out.presentation.PasswordController;
import org.factoriaf5.project_inside_out.application.usecase.ExportMomentsToCSVUseCase;
import org.factoriaf5.project_inside_out.application.usecase.GetAllMomentsByEmotionUseCase;
import org.factoriaf5.project_inside_out.application.usecase.GetAllMomentsByMonthUseCase;

public final class App {

        private App() {
        }

        public static void main(String[] args) {

                PasswordRepository passwordRepository = new InMemoryPasswordRepository();
                CreatePasswordUseCase createPasswordUseCase = new CreatePasswordUseCase(passwordRepository);
                AuthenticateUserUseCase authenticateUserUseCase = new AuthenticateUserUseCase(passwordRepository);

                PasswordController passwordController = new PasswordController(
                                createPasswordUseCase,
                                authenticateUserUseCase);

                // Create the repository that stores the moments
                MomentRepository momentRepository = new InMemoryMomentRepository();

                // Create the use case responsible for adding moments
                AddMomentUseCase addMomentUseCase = new AddMomentUseCase(momentRepository);

                // Create the use case responsible for retrieving moments
                GetAllMomentsUseCase getAllMomentsUseCase = new GetAllMomentsUseCase(momentRepository);

                // Create the use case responsible for deleting moments,etc.
                DeleteMomentUseCase deleteMomentUseCase = new DeleteMomentUseCase(momentRepository);

                GetAllMomentsByEmotionUseCase getAllMomentsByEmotionUseCase = new GetAllMomentsByEmotionUseCase(
                                momentRepository);

                GetAllMomentsByMonthUseCase getAllMomentsByMonthUseCase = new GetAllMomentsByMonthUseCase(
                                momentRepository);

                ExportMomentsToCSVUseCase exportMomentsToCSVUseCase = new ExportMomentsToCSVUseCase(momentRepository);

                // Connect the use cases to the controller
                MomentController momentController = new MomentController(
                                addMomentUseCase,
                                getAllMomentsUseCase,
                                deleteMomentUseCase,
                                getAllMomentsByEmotionUseCase,
                                getAllMomentsByMonthUseCase,
                                exportMomentsToCSVUseCase);

                // Connect the controller to the console view
                ConsoleView consoleView = new ConsoleView(momentController, passwordController);

                // Start the application
                consoleView.start();
        }

        private static final class InMemoryPasswordRepository implements PasswordRepository {

                private String savedPassword;

                @Override
                public boolean passwordExists() {
                        return savedPassword != null;
                }

                @Override
                public void savePassword(String password) {
                        this.savedPassword = password;
                }

                @Override
                public boolean verifyPassword(String password) {
                        return savedPassword != null && savedPassword.equals(password);
                }
        }
}
