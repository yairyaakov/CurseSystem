public abstract class Entity {
    private String userName;
    private String password;
    protected AddCourseStrategy addCourseStrategy;


    public Entity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Entity() {

    }
    // Getters and setters for userName and password

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addCourse(){
        this.addCourseStrategy.addCourse(userName);
    }
}