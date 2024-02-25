package mark.valkanouski.service.impl;

import lombok.RequiredArgsConstructor;
import mark.valkanouski.entity.User;
import mark.valkanouski.repository.UserRepository;
import mark.valkanouski.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
