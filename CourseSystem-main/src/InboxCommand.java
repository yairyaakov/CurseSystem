import java.util.List;

public class InboxCommand implements Command {

    private String username;

    public InboxCommand(String username) {
        this.username = username;
    }

    @Override
    public boolean execute() {
        List<String> messages = ((Student) UserManagerSingleton.getInstance().getUserByUserName(this.username)).getMessagesAndDeleteThem();

        if (!messages.isEmpty()) {
            System.out.println("You have " + messages.size() + " messages.");
            // Print each message with its index
            for (int i = 0; i < messages.size(); i++) {
                System.out.println((i + 1) + ". " + messages.get(i));
            }
        } else {
            System.out.println("You have no messages.");
        }
        return false;
    }
}