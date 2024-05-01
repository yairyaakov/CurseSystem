import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Student extends Entity implements Observer{

    private List<String> coursesIdTolisten;
    private List<Course> myCourses;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    private List<String> messages;
    public Student(String userName, String password) {
        super(userName, password);
        addCourseStrategy = new StudentAddCourseStrategy();
        this.myCourses = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.coursesIdTolisten = new ArrayList<>();
    }

    public Student() {
        super();
    }

    public void addCourse(Course course) {
        // Add the provided course to the list of courses
        myCourses.add(course);
    }

    public void print() {
        // Check if the list of courses is empty
        if (myCourses.isEmpty())
            throw new RuntimeException("there are no courses to delete");

        int index = 1;
        // Iterate through each course in the list and print its index and details
        for (Course course : myCourses) {
            System.out.println(index + ". " + course);
            index++;
        }
    }

    public int numberOfCourses() {
        // Return the number of courses in the list
        return this.myCourses.size();
    }

    public void deleteCourse(int courseNumber) {
        // Check if the provided course number is valid
        if (courseNumber <= 0 || courseNumber > myCourses.size()) {
            throw new InputMismatchException("Invalid course number.");
        }
        // Get the course to delete based on its index
        Course temp = myCourses.get(courseNumber - 1);
        // Decrease the available places in the course
        ManagerCourse.getInstance().decPlaceInCourseByName(temp.getName());
        // Remove the course from the list
        myCourses.remove(courseNumber - 1);
    }

    public void update(Course course) {
        // Add a message indicating an empty place in the specified course
        if(coursesIdTolisten.contains(course.getId())){
            messages.add("There is an empty place in course " + course.getName() + " id:" + course.getId() + ". Please choose before it's filled.");
            // Remove this object (observer) from observing course availability changes
            coursesIdTolisten.remove(course.getId());
            CourseAvailabilityNotifier.getInstance().removeObserver(this);
        }

    }

    public List<String> getMessagesAndDeleteThem() {
        // Create a copy of messages
        List<String> messagesCopy = new ArrayList<>(messages);
        // Clear the original list of messages
        messages.clear();
        // Return the copy of messages
        return messagesCopy;
    }
    public void addCourseToListen(String id) {
        coursesIdTolisten.add(id);
    }

}