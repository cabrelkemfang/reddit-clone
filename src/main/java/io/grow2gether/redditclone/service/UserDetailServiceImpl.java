package io.grow2gether.redditclone.service;

import io.grow2gether.redditclone.exceptions.SpringRedditException;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional()
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(s).orElseThrow(() -> new SpringRedditException("No user found"));
        return new org.springframework.security
                .core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(),
                true, true, true, getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }
}
