package com.main.comic.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategorySelectValidator implements ConstraintValidator<CategorySelectConstraint, String> {
    @Override
    public void initialize(CategorySelectConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String category, ConstraintValidatorContext cxt) {
        return category != null && !category.equals("NONE");
    }
}
