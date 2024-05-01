public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu("Courses system");
        menu.add("Login", new LoginCommand());
        menu.add("Register", new RegisterCommand());
        menu.add( "Quit", new QuitCommand());
        menu.activate();

    }
}