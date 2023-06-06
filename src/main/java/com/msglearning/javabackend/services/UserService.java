package com.msglearning.javabackend.services;

import com.msglearning.javabackend.converters.UserConverter;
import com.msglearning.javabackend.entity.User;
import com.msglearning.javabackend.repositories.UserRepository;
import com.msglearning.javabackend.to.UserCTO;
import com.msglearning.javabackend.to.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(UserCTO userTO) throws Exception {

        //validate Phone
        //validate email
        //check firstname NotNull or empty
        //check lastName NotNull or empty

        userTO.setPassword(PasswordService.getSaltedHash(userTO.getPassword()));

        return userRepository.save(UserConverter.convertToEntity(userTO));
    }

    public List<UserTO> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty())
            return Collections.emptyList();
        else
            return users.stream()
                    .map(UserConverter::convertToTO)
                    .collect(Collectors.toList());
    }

    public List<UserTO> findByName(String token) {
        List<User> users = userRepository.findByName(token);
        if (users.isEmpty())
            return Collections.emptyList();
        else
            return users.stream()
                    .map(UserConverter::convertToTO)
                    .collect(Collectors.toList());
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<String> getProfileImage(Long userId) {
        return userRepository.findProfileImageById(userId);
    }

    public List<User> getGmailUsers() {
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                    .filter(u -> u.getEmail().endsWith("gmail.com"))
                    .collect(Collectors.toList());

    }

    public List<String> getNamesSorted() {
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .map(u->u.getFirstName() + " " + u.getLastName())
                .sorted()
                .collect(Collectors.toList());

    }

    public boolean existsWithName(String name) {
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .anyMatch(u-> u.getFirstName().contains(name) || u.getLastName().contains(name) );

    }

    public Map<String, List<User>> groupByOccupation () {
        List<User> allUsers = userRepository.findAll();

        Map<String, List<User>> toReturn = allUsers.
                stream().collect(Collectors.groupingBy(User::getOccupation));
        return toReturn;
    }
}
