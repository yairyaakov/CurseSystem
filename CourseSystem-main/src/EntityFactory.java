import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class EntityFactory {
    private static Map<Class<? extends Entity>, Constructor<? extends Entity>> constructorMap = new HashMap<>();

    static {
        try {
            constructorMap.put(Student.class, Student.class.getConstructor(String.class, String.class));
            constructorMap.put(Senior.class, Senior.class.getConstructor(String.class, String.class));
            constructorMap.put(Practitioner.class, Practitioner.class.getConstructor(String.class, String.class));
            constructorMap.put(Lecturer.class, Lecturer.class.getConstructor(String.class, String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Entity> T create(Class<T> clazz, String userName, String password) {
        Constructor<? extends Entity> constructor = constructorMap.get(clazz);
        if (constructor == null) {
            throw new IllegalArgumentException("Unsupported entity class: " + clazz.getName());
        }

        try {
            return clazz.cast(constructor.newInstance(userName, password));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}