package org.project.model;

public class Validate {

    private ValidateType validateType;


    public Validate(ValidateType success)
    {
        this.validateType = success;
    }

    public ValidateType getType()
    {
        return validateType;
    }
}
