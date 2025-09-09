package com.devlog.domain.user;

public enum Role {
    USER, ADMIN;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
