
import java.util.InputMismatchException;
import java.util.Scanner;

// A class representing a command to delete a course
public class DeleteCommand implements Command {

    String username;

    public DeleteCommand(String username) {
        this.username = username;
    }

    @Override
    public boolean execute() {
        try {

            ((Student) UserManagerSingleton.getInstance().getUserByUserName(this.username)).print();
            Scanner scanner = new Scanner(System.in); // Creating a Scanner object to read input

            // Asking the user for the number of the course to delete
            System.out.print("Enter the number of the course you want to delete: ");
            int courseNumber = scanner.nextInt(); // Reading the course number
            scanner.nextLine(); // Consume newline character

            // Checking if the course is full and notifying observers if it is
            boolean isCourseFull = ManagerCourse.getInstance().isCourseFull(courseNumber);
            if (isCourseFull) {
                CourseAvailabilityNotifier.getInstance().notifyObservers(ManagerCourse.getInstance().getCourseByNumber(courseNumber));
            }

            // Deleting the course from the student's schedule
            ((Student) UserManagerSingleton.getInstance().getUserByUserName(this.username)).deleteCourse(courseNumber);
            System.out.println("The Course deleted successfully.");

            return false; // Do not return to the previous menu
        } catch (InputMismatchException e) {
            // Handling invalid input exception
            System.out.println("Invalid input. Please enter a valid number.");
            return false; // Do not return to the previous menu
        } catch (RuntimeException e) {
            return false; // Do not return to the previous menu
        }

    }
}
