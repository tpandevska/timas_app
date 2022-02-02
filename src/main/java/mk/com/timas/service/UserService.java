package mk.com.timas.service;

import mk.com.timas.model.Role;
import mk.com.timas.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}

