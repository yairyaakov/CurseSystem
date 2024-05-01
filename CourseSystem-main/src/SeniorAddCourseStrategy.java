import java.util.Scanner;

public class SeniorAddCourseStrategy implements AddCourseStrategy{

    @Override
    public void addCourse(String currentUserName) {

        Scanner scanner = new Scanner(System.in);
        String tryAgain;

        do {
            System.out.println("Please enter course details:");
            int numberOfParticipants = 0;
            boolean successInput = false;
            String id = null, name = null;

            while (!successInput) {
                try {
                    System.out.print("Course ID(String): ");
                    id = scanner.nextLine().trim();

                    System.out.print("Course Name(string): ");
                    name = scanner.nextLine().trim();

                    System.out.print("Number of Participants(number): ");
                    numberOfParticipants = Integer.parseInt(scanner.nextLine());
                    successInput = true;


                    CourseTypes courseType = null;
                    boolean validCourseType = false;
                    while (!validCourseType) {

                        System.out.println("Available course types:");
                        int typeNumber = 1;
                        for (CourseTypes type : CourseTypes.values()) {
                            System.out.println(typeNumber + ": " + type);
                            typeNumber++;
                        }
                        System.out.print("Select a course type by entering its number: ");
                        int courseTypeNumber = Integer.parseInt(scanner.nextLine());
                        if (courseTypeNumber >= 1 && courseTypeNumber <= CourseTypes.values().length) {
                            courseType = CourseTypes.values()[courseTypeNumber - 1];
                            validCourseType = true;
                        } else {
                            System.out.println("Invalid number. Please select from the available options.");
                        }
                    }

                    Course newCourse = new Course(numberOfParticipants, name, id, courseType);

                    boolean success = ManagerCourse.getInstance().addCourse(newCourse);

                    if (success) {
                        System.out.println("Course added successfully.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid input");
                }
            }

            System.out.println("Do you want to try again? Enter 'yes' or choose another character for 'no'.");
            tryAgain = scanner.nextLine().trim().toLowerCase();

            if (tryAgain.equals("yes")) {
                System.out.println("Please try again.");
            }

        } while (tryAgain.equals("yes"));
    }
}