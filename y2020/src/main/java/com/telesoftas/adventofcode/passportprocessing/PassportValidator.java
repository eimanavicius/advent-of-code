package com.telesoftas.adventofcode.passportprocessing;

import lombok.RequiredArgsConstructor;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@RequiredArgsConstructor
public class PassportValidator {

    private final Validator validator;

    public PassportValidator() {
        ValidatorFactory factory = Validation.byDefaultProvider().configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory();
        validator = factory.getValidator();
    }

    public boolean isValid(Passport passport) {
        return validator.validate(passport).isEmpty();
    }
}
