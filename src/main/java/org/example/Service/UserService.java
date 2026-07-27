package org.example.Service;

import org.example.Model.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<Integer , User> userDb;

    public UserService()
    {
        userDb=new HashMap<>();
    }

    public User createuser(User userReq)
    {
        userDb.put(userReq.getId(),userReq);
        return userReq;
    }
}
