public class AppController {
    private static AppController instance;
    private final ManagerCourse managerCourse;

    // Private constructor to prevent instantiation from outside
    private AppController() {
        // Initialize ManagerCourse
        managerCourse = ManagerCourse.getInstance();
    }

    // Static method to get the singleton instance
    public static AppController getInstance() {
        if (instance == null) {
            instance = new AppController();
        }
        return instance;
    }

    // Method to enter the application
    public void enterApplication() {
        // Implement the logic to transition the user into the application
        System.out.println("Entering application...");



        // Example: Display main menu or start main functionality
    }

    // Getter for ManagerCourse instance
    public ManagerCourse getManagerCourse() {
        return managerCourse;
    }

    public void initMenu(String username) {

        Menu menu = new Menu("Hello " + username + " - Courses system");
        menu.add("Add course", new AddItemCommand(username));
        if(!UserManagerSingleton.getInstance().isSenior(username)) {
            menu.add("delete course", new DeleteCommand(username));
            menu.add("Inbox", new InboxCommand(username));
        }
        menu.add( "Logout", new LogoutCommand(username));
        menu.activate();

    }
}