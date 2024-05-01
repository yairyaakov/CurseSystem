// Importing the Scanner class for user input
import java.util.Scanner;

// RegisterCommand class implementing the Command interface
public class RegisterCommand implements Command {

    // Overriding the execute method of the Command interface
    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        boolean registrationSuccess = false;

        // Loop for registration process
        do {
            // Prompting the user to enter a new username
            System.out.print("To return to the previous menu, enter '0' as the username:\nEnter new username: ");
            String username = scanner.nextLine();

            // Checking if the user wants to return to the previous menu
            if (username.equals("0")) {
                return false; // Return to the previous menu
            }
            // Prompting the user to enter a password
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // Attempting to register the user
            registrationSuccess = UserManagerSingleton.getInstance().register(EntityFactory.create(Student.class, username, password));

        } while (!registrationSuccess); // Continuing the loop until registration is successful
        return false; // Do not return to the previous menu
    }
}