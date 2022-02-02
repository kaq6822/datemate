package com.datemate.api.member.service.impl;

import com.datemate.api.member.dao.MemberRepository;
import com.datemate.api.member.model.Member;
import com.datemate.api.member.service.MemberService;
import com.datemate.api.user.dao.UserRepository;
import com.datemate.api.user.model.User;
import com.datemate.common.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.debug("load User: {}", email);
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Constants.USER));

        return member;
    }

    @Override
    public boolean checkUserExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void addUser(Member member) {
        log.debug("add User: {}", member);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    @Override
    public void disableUser(String email) {
        User user = userRepository.findByEmail(email).get();
        user.setStatus("1");
        log.debug("disable User: {}", user);
        userRepository.save(user);
    }

    @Override
    public boolean authenticateByEmailAndPassword(String email, String password) throws BadCredentialsException, UsernameNotFoundException {
        log.debug("Password Authentication User: {}", email);
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new BadCredentialsException("Password not matched");
        }

        return true;
    }

}
