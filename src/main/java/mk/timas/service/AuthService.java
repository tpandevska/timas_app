package mk.timas.service;

import mk.timas.model.User;

public interface AuthService {
    User login(String username, String password);
}

