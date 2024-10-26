package org.project.user;

import org.project.model.User;
import org.project.store.UserStore;

public class UserManager
{

    UserStore userStore;

    public UserManager(UserStore userStore)
    {
        this.userStore = userStore;

    }

    public void createUser(User User)
    {
        userStore.createUser(User);
    }
}
