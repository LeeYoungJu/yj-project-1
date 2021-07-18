package com.yjproject1.config.auth.service;

import com.yjproject1.config.auth.dto.CustomUserDetails;
import com.yjproject1.domain.user.User;
import com.yjproject1.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);

        if(user == null) {
            throw new UsernameNotFoundException(username + "is not found");
        }

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUsername(user.getName());
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.setAuthorities(getAuthorities(user.getRoleKey()));
        customUserDetails.setEnabled(true);
        customUserDetails.setAccountNonExpired(true);
        customUserDetails.setAccountNonLocked(true);
        customUserDetails.setCredentialsNonExpired(true);

        return customUserDetails;
    }

    public Collection<GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}
