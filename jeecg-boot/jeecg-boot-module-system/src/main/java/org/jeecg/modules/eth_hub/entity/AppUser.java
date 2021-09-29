package org.jeecg.modules.eth_hub.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AppUser {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String inviteBy;
}
