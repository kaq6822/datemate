package com.datemate.api.member.service;

import com.datemate.api.member.model.Member;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface MemberService {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    boolean checkUserExist(String email);

    void addUser(Member member);

    void disableUser(String email);

    boolean authenticateByEmailAndPassword(String email, String passwd) throws BadCredentialsException, UsernameNotFoundException;

}
