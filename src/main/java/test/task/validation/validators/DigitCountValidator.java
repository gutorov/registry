package test.task.validation.validators;

import test.task.validation.annotations.DigitCount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DigitCountValidator implements ConstraintValidator<DigitCount, Long> {
    private Long min;
    private Long max;

    @Override
    public void initialize(DigitCount constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return true;  // null is considered valid
        int length = String.valueOf(value).length();
        return length >= min && length <= max;
    }
}
