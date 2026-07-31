package org.example.Service;

import org.example.Model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public List<User> getAllUsers()
    {
        List<User> usersResp=new ArrayList<>();
        for(User user:userDb.values())
        {
            usersResp.add(user);
        }
        return usersResp;
    }

    public User getUserById(Integer id)
    {
    return userDb.get(id);
    }
}
