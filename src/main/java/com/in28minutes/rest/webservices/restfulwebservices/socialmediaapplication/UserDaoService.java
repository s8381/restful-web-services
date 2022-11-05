package com.in28minutes.rest.webservices.restfulwebservices.socialmediaapplication;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(2, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(3, "Jin", LocalDate.now().minusYears(20)));
    }

    public List<User> findAll(){
        return users;
    }

    public void deleteById(Integer id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public User findWithId(Integer id){
        return users.stream()
                .filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public User createUser(User user){
        user.setId(users.size() + 1);
        users.add(user);
        return user;
    }

}
