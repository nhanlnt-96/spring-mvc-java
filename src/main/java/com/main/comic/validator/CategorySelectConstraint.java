package com.main.comic.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategorySelectValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CategorySelectConstraint {
    String message() default "Category can not be null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
