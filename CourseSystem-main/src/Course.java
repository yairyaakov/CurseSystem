import java.util.List;

public class Course {
    private int numberOfParticipants;
    private String name;
    private String id;

    public int getParticipantCounter() {
        return participantCounter;
    }

    public void setParticipantCounter(int participantCounter) {
        this.participantCounter = participantCounter;
    }

    private int participantCounter = 0;

    public CourseTypes getType() {
        return type;
    }

    public void setType(CourseTypes type) {
        this.type = type;
    }

    private CourseTypes type;

    // Constructor
    public Course(int numberOfParticipants, String name, String id ,CourseTypes type) {
        this.numberOfParticipants = numberOfParticipants;
        this.name = name;
        this.id = id;
        this.type = type;
    }

    // Getters and setters
    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // toString method to represent the course as a string
    @Override
    public String toString() {
        return " " +
                "numberOfParticipants=" + numberOfParticipants +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'';
    }

    // Static method to check if a course with the given ID already exists
    public boolean isIdUnique(String id, List<Course> courses) {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                return false; // ID already exists
            }
        }
        return true; // ID is unique
    }

    // Static method to check if a course with the given name already exists
    public boolean isNameUnique(String name, List<Course> courses) {
        for (Course course : courses) {
            if (course.getName().equalsIgnoreCase(name)) {
                return false; // Name already exists
            }
        }
        return true; // Name is unique
    }

    public void incrementParticipantCounter() {
        participantCounter++;
    }
}