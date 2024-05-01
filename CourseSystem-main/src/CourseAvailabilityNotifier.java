import java.util.ArrayList;
import java.util.List;

public class CourseAvailabilityNotifier  implements Subject {
    private static CourseAvailabilityNotifier instance;
    private final List<Student> observers;

    private CourseAvailabilityNotifier() {
        this.observers = new ArrayList<>();
    }

    public static CourseAvailabilityNotifier getInstance() {
        if (instance == null) {
            instance = new CourseAvailabilityNotifier();
        }
        return instance;
    }

    public void addObserver(Student student) {
        observers.add(student);
    }

    public void removeObserver(Student student) {
        for (int i = 0; i < observers.size(); i++) {
            if (observers.get(i).equals(student)) {
                observers.remove(i);
                break; // Exit the loop after removing the first occurrence
            }
        }
    }

    public void notifyObservers(Course course) {
        for (Student observer : observers) {
            observer.update(course);
        }
    }
}