package test.task.validation.annotations;

import test.task.validation.validators.DigitCountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DigitCountValidator.class)
public @interface DigitCount {
    int min() default 0;
    int max() default Integer.MAX_VALUE;
    String message() default "The number of digits is out of acceptable range";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

