package mark.valkanouski.service;

import mark.valkanouski.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
