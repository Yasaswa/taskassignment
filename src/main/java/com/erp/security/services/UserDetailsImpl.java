package com.erp.security.services;

import com.erp.users.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final int company_id;

    private final int company_branch_id;

    private final int department_id;

    private final String user;

    private final String user_type;

    private final String user_code;

    private final Integer user_id;

    private final String username;

    private final String company_name;

    private final String company_branch_name;

    private final String company_branch_address;

    private final String branch_short_name;

    private final String department_name;

    private final String company_access;

    @JsonIgnore
    private final String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(int company_id, int company_branch_id, String user, String user_type, String user_code, Integer user_id, String username, String company_name, String company_branch_name, String company_branch_address, String branch_short_name, String password, int department_id, String department_name, String company_access) {
        this.company_id = company_id;
        this.company_branch_id = company_branch_id;
        this.department_id = department_id;
        this.user = user;
        this.user_type = user_type;
        this.user_code = user_code;
        this.user_id = user_id;
        this.username = username;
        this.company_name = company_name;
        this.company_branch_name = company_branch_name;
        this.company_branch_address = company_branch_address;
        this.branch_short_name = branch_short_name;
        this.department_name = department_name;
        this.password = password;
        this.company_access = company_access;
    }

    public String getBranch_short_name() {
        return branch_short_name;
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getCompany_id(),
                user.getCompany_branch_id(),
                user.getUser(),
                user.getUser_type(),
                user.getUser_code(),
                user.getUser_id(),
                user.getUsername(),
                user.getCompany_name(),
                user.getCompany_branch_name(),
                user.getCompany_branch_address(),
                user.getBranch_short_name(),
                user.getPassword(),
                user.getDepartment_id(),
                user.getDepartment_name(), user.getCompany_access()
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Integer getId() {
        return user_id;
    }

    public String getUser_code() {
        return user_code;
    }

    public int getCompany_id() {
        return company_id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public int getCompany_branch_id() {
        return company_branch_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public String getUser() {
        return user;
    }

    public String getUser_type() {
        return user_type;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getCompany_branch_name() {
        return company_branch_name;
    }

    public String getCompany_branch_address() {
        return company_branch_address;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public String getCompany_access() {
        return company_access;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(user_id, user.user_id);
    }

}
