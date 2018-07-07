package main.com.ifttt.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author praveenkamath
 **/
@Retention(RetentionPolicy.RUNTIME)
public @interface Fact {

    String value();
}
