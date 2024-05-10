package test.task.validation.validators;

import test.task.validation.annotations.DigitCount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DigitCountValidator implements ConstraintValidator<DigitCount, Integer> {
    private int min;
    private int max;

    @Override
    public void initialize(DigitCount constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) return true;  // null is considered valid
        int length = String.valueOf(value).length();
        return length >= min && length <= max;
    }
}
