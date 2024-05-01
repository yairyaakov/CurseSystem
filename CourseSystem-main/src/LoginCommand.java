import java.util.Scanner;

class LoginCommand implements Command {

    @Override
    public boolean execute() {

        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        String username;
        do {
            System.out.print("To return to the previous menu, enter '0' as the username: \n");
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            if (username.equals("0")) {
                return false; // Return to previous menu
            }

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            loggedIn = UserManagerSingleton.getInstance().login(username, password);
        } while (!loggedIn);


        AppController.getInstance().initMenu(username);


        return false;
    }
}