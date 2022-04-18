package mk.timas.service.impl;

import mk.timas.model.User;
import mk.timas.model.exceptions.InvalidArgumentsException;
import mk.timas.model.exceptions.InvalidUserCredentialsException;
import mk.timas.repository.UserRepository;
import mk.timas.service.AuthService;
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

