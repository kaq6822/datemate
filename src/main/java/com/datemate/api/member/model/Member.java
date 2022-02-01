package com.datemate.api.member.model;

import com.datemate.common.model.AbstractTimestampEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "dm_user_master")
public class Member extends AbstractTimestampEntity implements UserDetails {
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userSeq;

    @Id
    private String email;

    private String userName;

    @Column(nullable = false)
    private String password;

    private String status = "0";

    private String token;

    @Transient
    private boolean ENABLED;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority("USER"));

        return authList;
    }

    @Override
    public String getUsername() {
        return email;
    }

    /**
     * 계정 만료 여부
     * true: 유효
     * false: 만료
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠김 여부
     * true: 유효
     * false: 잠김
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 비밀번호 만료 여부
     * true: 유효
     * false: 만료
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 사용자 활성화 여부
     * true: 활성화
     * false: 비활성화
     * @return
     */
    @Override
    public boolean isEnabled() {
        return ENABLED;
    }

}
