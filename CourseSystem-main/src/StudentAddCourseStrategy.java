import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentAddCourseStrategy implements AddCourseStrategy {

    @Override
    public void addCourse(String currentUserName) {

        try{
            while (true) {
                try {
                    Scanner scanner = new Scanner(System.in);
                    int courseNumber = -1;

                    ManagerCourse.getInstance().printAllCourses();
                    System.out.print("Enter the number of the course you want to add: ");
                    courseNumber = scanner.nextInt();
                    scanner.nextLine();
                    Course chosenCourse = ManagerCourse.getInstance().getCourseByNumber(courseNumber);

                    // Check if the chosen course is valid
                    if (chosenCourse != null) {
                        handleCourseAvailabilityResponse(courseNumber, currentUserName, chosenCourse);
                        return;
                    } else {
                        System.out.println("Invalid course number. Course not added.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

        }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage()); // Print the exception message
        }


    }


    private void handleCourseAvailabilityResponse(int courseNumber, String currentUserName,Course chosenCourse) {
        Scanner scanner = new Scanner(System.in); // Creating a Scanner object to read input
        if (!ManagerCourse.getInstance().hasSpaceForMoreStudents(courseNumber)) {
            System.out.println("The course is currently full. Do you want to receive a message when there is space available? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("yes")) {
                // Add the user as an observer to get a message
                CourseAvailabilityNotifier.getInstance().addObserver(((Student) UserManagerSingleton.getInstance().getUserByUserName(currentUserName)));
                ((Student) UserManagerSingleton.getInstance().getUserByUserName(currentUserName)).addCourseToListen(chosenCourse.getId());
            }
        } else {
            // If there is space in the course, add the user to the course and increment the participant counter
            ManagerCourse.getInstance().getCourseByNumber(courseNumber).incrementParticipantCounter();
            ((Student) UserManagerSingleton.getInstance().getUserByUserName(currentUserName)).addCourse(chosenCourse);
            System.out.println("Course added.");
        }
    }

}