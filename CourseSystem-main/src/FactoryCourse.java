public class FactoryCourse {
    public static Course createCourse(int numberOfParticipants, String name, String number,CourseTypes courseTypes) {
        return new Course(numberOfParticipants,name, number,courseTypes);
    }
}