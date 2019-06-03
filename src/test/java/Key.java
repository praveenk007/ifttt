import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author praveenkamath
 **/
@Retention(RetentionPolicy.RUNTIME)
public @interface Key {

    String value();
}