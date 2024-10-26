package org.project.manager;

import org.project.model.Command;
import org.project.processor.Processor;
import org.project.validate.Validator;

import java.util.Map;


public interface Manager {

    public void setProcessor(Map<String, Processor> processorMap);

    public Processor getProcessor(Command command);

    public void setValidator(Map<String, Validator> validatorMap);

    public Validator getValidator(Command command);
}
