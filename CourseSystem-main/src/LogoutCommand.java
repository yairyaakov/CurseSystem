class LogoutCommand implements Command {

    private String username;

    public LogoutCommand(String username) {
        this.username = username;
    }

    @Override
    public boolean execute() {
        UserManagerSingleton.getInstance().logout(username);
        return true;
    }
}