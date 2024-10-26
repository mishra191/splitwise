package org.project.processor;

import org.project.model.Command;
import org.project.result.Result;

public interface Processor {

    public Result process(Command command);
}
