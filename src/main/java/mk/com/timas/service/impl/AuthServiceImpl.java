package mk.com.timas.service.impl;

import mk.com.timas.model.User;
import mk.com.timas.model.exceptions.InvalidArgumentsException;
import mk.com.timas.model.exceptions.InvalidUserCredentialsException;
import mk.com.timas.repository.UserRepository;
import mk.com.timas.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}

