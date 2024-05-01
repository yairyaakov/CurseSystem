import java.util.ArrayList; // Importing ArrayList class from java.util package
import java.util.InputMismatchException;
import java.util.List; // Importing List interface from java.util package
import java.util.Scanner; // Importing Scanner class from java.util package

// A class representing a menu with options
public class Menu {

    private List<Option> options = new ArrayList<>(); // List to store menu options
    private String title; // Title of the menu

    // Constructor to initialize the menu with a title
    public Menu(String title) {
        this.title = title;
    }

    // Method to add an option to the menu
    public void add(String name, Command command) {
        options.add(new Option(name, command)); // Adding a new Option to the list
    }

    // Method to print the options of the menu
    private void printOptions() {
        System.out.println(title); // Printing the title of the menu
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i).getName()); // Printing each option with its index
        }
    }

    // Method to activate the menu
    public void activate() {

        Scanner scanner = new Scanner(System.in);
        int optionIndex;
        boolean exitFromMenu = false;

        do {
            try {
                printOptions(); // Printing the options of the menu
                System.out.print("Enter your choice (1-" + options.size() + "): ");
                optionIndex = scanner.nextInt(); // Reading the user's choice
                if (optionIndex >= 1 && optionIndex <= options.size()) {
                    // If the choice is valid, executing the corresponding command
                    exitFromMenu = options.get(optionIndex - 1).getCommand().execute();
                } else {
                    System.out.println("Invalid choice. Please try again."); // Informing the user of invalid input
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        } while (!exitFromMenu); // Loop until user chooses to exit
    }
}