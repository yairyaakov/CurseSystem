public class Senior extends Entity{
    public Senior(String userName, String password) {
        super(userName, password);
        addCourseStrategy = new SeniorAddCourseStrategy();
    }

    public Senior() {
    }
}