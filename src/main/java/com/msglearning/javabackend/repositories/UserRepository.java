package com.msglearning.javabackend.repositories;

import com.msglearning.javabackend.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long > {

    List<User> findAll();

    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:token% or u.lastName LIKE %:token% ")
    List<User> findByName(@Param("token") String token);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    @Query("SELECT u.profileImage FROM User u WHERE u.id = :id")
    Optional<String> findProfileImageById(@Param("id") Long id);

}
