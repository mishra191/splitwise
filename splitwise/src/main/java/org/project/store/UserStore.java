package org.project.store;

import org.project.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserStore
{
    Map<String, User> userMap = new HashMap<>();

    public void createUser(User user)
    {
        userMap.put(user.getUserName(), user);
    }

    public User getUser(String userName)
    {
        return userMap.get(userName);
    }
}
