package com.security.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {
    @Autowired
    private IUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String nomeCliente) throws UsernameNotFoundException {
        UserModel user = repository.findByUsername(nomeCliente);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + nomeCliente);
        }
        return user;
    }
}
