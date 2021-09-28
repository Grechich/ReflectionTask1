import java.lang.annotation.*;
import java.lang.reflect.*;

@Inherited
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface AnnotationMethod {
    int a();
    int b();
}

public class Task1 {
    @AnnotationMethod(a = 2, b = 5)
    public void test(int a, int b) {
        System.out.println(a+b);
    }
}

class Reflection {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        final Class<?> cls = Task1.class;
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(AnnotationMethod.class)) {
                AnnotationMethod an = method.getAnnotation(AnnotationMethod.class);
                method.invoke(cls.getConstructor().newInstance(), an.a(), an.b());
            }
        }
    }
}