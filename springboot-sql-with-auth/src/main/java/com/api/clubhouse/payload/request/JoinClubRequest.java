package com.api.clubhouse.payload.request;

import javax.validation.constraints.NotBlank;

public class JoinClubRequest {
    @NotBlank
    private Long id;
    @NotBlank
    private String joiningPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJoiningPassword() {
        return joiningPassword;
    }

    public void setJoiningPassword(String joiningPassword) {
        this.joiningPassword = joiningPassword;
    }
}
