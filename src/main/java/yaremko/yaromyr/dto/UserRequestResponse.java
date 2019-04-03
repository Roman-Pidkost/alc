package yaremko.yaromyr.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yaremko.yaromyr.entity.User;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestResponse {

    private Long id;

    private String name;

    private String surName;

    private String email;

    private String phoneNumber;

    private String password;

    private Boolean registered;

    private String role;

    public UserRequestResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surName = user.getSurName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.password = user.getPassword();
        this.registered = user.getRegistered();
        this.role = user.getRole().name();
    }

}
