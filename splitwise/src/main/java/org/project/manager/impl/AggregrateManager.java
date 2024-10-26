package org.project.manager.impl;

import org.project.manager.Manager;
import org.project.model.AggregrateCommand;
import org.project.model.Command;
import org.project.processor.Processor;
import org.project.validate.Validator;

import java.util.Map;


public class AggregrateManager implements Manager {

    private Map<String, Processor> processorMap;
    private Map<String, Validator> validatorMap;
    @Override
    public void setProcessor(Map<String, Processor> processorMap)
    {
        this.processorMap = processorMap;
    }

    @Override
    public Processor getProcessor(Command command)
    {
        return processorMap.get(command.getName());
    }

    @Override
    public void setValidator(Map<String, Validator> validatorMap)
    {
        this.validatorMap = validatorMap;
    }

    @Override
    public Validator getValidator(Command command)
    {
        return validatorMap.get(command.getName());
    }
}
