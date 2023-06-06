package com.msglearning.javabackend.to;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class UserCTO extends UserTO implements Serializable {

    String password;
    public UserCTO(Long id, String firstName, String lastName, String email, String phone, String occupation) {
        super(id, firstName, lastName, email, phone, occupation);
    }

    public UserCTO(Long id, String firstName, String lastName, String email, String phone, String occupation, String password) {
        super(id, firstName, lastName, email, phone, occupation);
        this.password = password;
    }
}
