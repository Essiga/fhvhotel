package at.fhv.hotelsoftware.view.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = DateValidator.class)
public @interface DateNotNullOrEarlierThanToday {


    String message() default "Illegal Date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
