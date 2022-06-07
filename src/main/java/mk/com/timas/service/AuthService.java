package mk.com.timas.service;

import mk.com.timas.model.User;

public interface AuthService {

    User login(String username, String password);
}

