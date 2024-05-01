import java.util.ArrayList;
import java.util.List;

// A class representing a manager for courses
public class ManagerCourse {

    private static ManagerCourse instance; // Singleton instance of ManagerCourse
    private final List<Course> courses; // List to store courses

    // Private constructor to enforce singleton pattern and initialize courses list
    private ManagerCourse() {
        this.courses = new ArrayList<>(); // Initializing courses list
    }

    // Method to get the singleton instance of ManagerCourse
    public static ManagerCourse getInstance() {
        if (instance == null) {
            instance = new ManagerCourse(); // Creating a new instance if it doesn't exist
        }
        return instance;
    }

    // Method to add a new course to the manager
    public boolean addCourse(Course newCourse) {
        // Checking if a course with the same ID or name already exists
        for (Course course : courses) {
            if (course.getId().equals(newCourse.getId())) {
                // Printing existing course data and returning false if a course with the same ID exists
                System.out.println("Course with ID " + newCourse.getId() + " already exists:");
                System.out.println(course);
                return false;
            }
            if (course.getName().equalsIgnoreCase(newCourse.getName())) {
                // Printing existing course data and returning false if a course with the same name exists
                System.out.println("Course with name '" + newCourse.getName() + "' already exists:");
                System.out.println(course);
                return false;
            }
        }
        // Adding the new course to the list and returning true
        courses.add(newCourse);
        System.out.println("Course '" + newCourse.getName() + "' added successfully.");
        return true;
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    // Method to get all courses managed by the manager
    public List<Course> getAllCourses() {
        return courses;
    }

    // Method to get the count of courses managed by the manager
    public int getCourseCount() {
        return courses.size();
    }

    // Method to get a course by its index in the manager's list
    public Course getCourseByNumber(int number) {
        int index = number - 1;
        if (index >= 0 && index < this.courses.size()) {
            return courses.get(index);
        } else {
            // Printing an error message if the specified course number does not exist
            System.out.println("Course number " + number + " does not exist.");
            return null;
        }
    }

    // Method to print information about all courses managed by the manager
    public void printAllCourses() {
        if (courses.isEmpty()) {
            // Throwing an exception if there are no courses available to add
            throw new IllegalStateException("There are no courses available to add.");
        }
        System.out.println("All Courses:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i));
        }
    }

    // Method to check if a course has space for more students based on its index
    public boolean hasSpaceForMoreStudents(int index) {
        return this.courses.get(index - 1).getParticipantCounter() < this.courses.get(index - 1).getNumberOfParticipants();
    }

    // Method to decrease the participant count of a course by its name
    public void decPlaceInCourseByName(String name) {
        for (Course course : courses) {
            if (course.getName().equals(name)) {
                course.setParticipantCounter(course.getParticipantCounter() - 1);
            }
        }
    }

    // Method to check if a course is full based on its index
    public boolean isCourseFull(int courseNumber) {
        return !hasSpaceForMoreStudents(courseNumber);
    }
}