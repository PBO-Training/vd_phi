package com.brycen.hrm.config.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brycen.hrm.entity.UserEntity;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    UserRepository userRepository;
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameAndCompanyID) throws UsernameNotFoundException {
        String[] userInfo = usernameAndCompanyID.split("---");
        UserEntity user = userRepository.findByUsernameAndCompanyID(userInfo[0], Integer.parseInt(userInfo[1]))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userInfo[0]));

        return UserDetailsImpl.build(user);
    }
}
