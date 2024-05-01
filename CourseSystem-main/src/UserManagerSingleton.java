import java.util.*;

public class UserManagerSingleton {

    private static UserManagerSingleton instance;
    private List<Entity> registeredUsers;
    private Set<String> loggedInUsers;
    private int totalUsers;

    private UserManagerSingleton() {
        registeredUsers = new ArrayList<Entity>();
        loggedInUsers = new HashSet<>();
        totalUsers = 0;
        registeredUsers.add(EntityFactory.create(Practitioner.class, "moshe", "123"));
        registeredUsers.add(EntityFactory.create(Lecturer.class, "dor", "123"));
    }

    public static UserManagerSingleton getInstance() {
        if (instance == null) {
            instance = new UserManagerSingleton();
        }
        return instance;
    }

    public boolean register(Entity newEntity) {

        // Check if username already exists
        for (Entity entity : registeredUsers) {
            if (entity.getUserName().equals(newEntity.getUserName())) {
                System.out.println("Username '" + newEntity.getUserName() + "' already exists. Please choose a different username.");
                return false;
            }
        }
        System.out.println("Registration successful.");
        registeredUsers.add(newEntity);
        totalUsers++;
        return true;
    }

    public boolean login(String username, String password) {

        for (Entity entity : registeredUsers) {
            if (entity.getUserName().equals(username) && entity.getPassword().equals(password)) {
                if (loggedInUsers.size() >= 100) {
                    System.out.println("Sorry, our system is at full capacity. Please try again.");
                    return false; // System is at full capacity, cannot login
                } else {
                    loggedInUsers.add(username);
                    return true; // Successful login
                }
            }
        }
        // If username or password is invalid
        System.out.println("Invalid username or password.");
        return false;
    }

    public void logout(String username) {
        loggedInUsers.remove(username);
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public int getLoggedInUsersCount() {
        return loggedInUsers.size();
    }

    public void printRegisteredUsers() {
        System.out.println("Registered Users:");
        for (Entity entity : registeredUsers) {
            System.out.println("Username: " + entity.getUserName() + ", Password: " + entity.getPassword());
        }
    }

    public void printLoggedInUsers() {
        System.out.println("Logged In Users:");
        for (String username : loggedInUsers) {
            System.out.println("Username: " + username);
        }
    }

    public Entity getUserByUserName(String username) {
        for (Entity entity : registeredUsers) {
            if (entity.getUserName().equals(username)) {
                return entity; // Return the user entity with matching username
            }
        }
        return null;//for compiler
    }

    public boolean isSenior(String username) {
        Entity user = getUserByUserName(username);
        return user instanceof Senior;
    }
}