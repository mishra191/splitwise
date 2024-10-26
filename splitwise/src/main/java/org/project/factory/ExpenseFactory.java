package org.project.factory;

import org.project.manager.Manager;
import org.project.manager.impl.AggregrateManager;
import org.project.model.Command;
import org.project.manager.impl.ExpenseManager;

import java.util.HashMap;
import java.util.Map;

public class ExpenseFactory {

    private static Map<String, Manager> managerMap = new HashMap<>();

    public void setManagerMap(Map<String, Manager> managerMap) {
        this.managerMap = managerMap;
    }

    public static ExpenseFactory getInstance()
    {
        return new ExpenseFactory();
    }

    public Manager getManager(Command command)
    {
       return managerMap.get(command.getName());
    }
}
