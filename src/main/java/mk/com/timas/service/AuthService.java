package mk.com.timas.service;

import mk.com.timas.model.User;

import java.util.List;
import java.util.Optional;

public interface AuthService {
    User login(String username, String password);
}

