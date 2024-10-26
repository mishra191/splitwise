package org.project.validate.impl;

import org.project.model.Command;
import org.project.model.Validate;
import org.project.model.ValidateType;
import org.project.validate.Validator;

public class AggregrateValidator implements Validator
{

    @Override
    public Validate validate(Command command) throws Exception {
        return new Validate(ValidateType.SUCCESS);
    }
}
