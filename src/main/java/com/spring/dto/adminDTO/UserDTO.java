package com.spring.dto.adminDTO;

import com.spring.model.User;

public class UserDTO {

    private Long id;

    private String name;

    private Boolean enabled;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.name = user.getUsername();
        this.enabled = user.isEnabled();
        this.id = user.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
