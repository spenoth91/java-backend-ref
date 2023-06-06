package com.msglearning.javabackend.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTO implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String occupation;

    @Override
    public String toString() {
        return "UserTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
