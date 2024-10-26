package org.project.validate.impl;

import org.project.model.Command;
import org.project.model.Validate;
import org.project.model.ValidateType;
import org.project.validate.Validator;


public class EqualValidator implements Validator
{

    @Override
    public Validate validate(Command command) throws Exception
    {
        return new Validate(ValidateType.SUCCESS);
    }


    //   In case of percent, you need to verify if
//     the total sum of percentage shares is 100 or not.
//     In case of exact, you need to
//     verify if the total sum of shares is equal to the total amount or not.
}
