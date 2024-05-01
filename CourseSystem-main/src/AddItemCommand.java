public class AddItemCommand implements Command {

    String username;
    public AddItemCommand(String username) {
        this.username = username;

    }
    @Override
    public boolean execute() {
        UserManagerSingleton.getInstance().getUserByUserName(this.username).addCourse();
        return false;
    }
}