package com.spring.model.enums;

import org.springframework.security.core.GrantedAuthority;



public enum Role implements GrantedAuthority {
    ROLE_WAITER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}
