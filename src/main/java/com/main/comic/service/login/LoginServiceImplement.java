package com.main.comic.service.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.main.comic.dao.user.UserDAO;
import com.main.comic.entity.Role;
import com.main.comic.entity.User;
import com.main.comic.utils.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service("myUserDetails")
public class LoginServiceImplement implements UserDetailsService {
    private final UserDAO userDAO;

    @Autowired
    public LoginServiceImplement(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findUserByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found by username: " + username);
        }

        Set<Role> roleList = user.getRoles();

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        for (Role role : roleList) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
            grantedAuthorityList.add(grantedAuthority);
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), "{bcrypt}" + user.getPassword(), grantedAuthorityList);
    }

}
