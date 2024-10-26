package org.project.validate;

import org.project.model.Command;
import org.project.model.Validate;

public interface Validator {

    public Validate validate(Command command) throws Exception;
}
