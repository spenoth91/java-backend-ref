package com.msglearning.javabackend.repositories;

import com.msglearning.javabackend.entity.Stuff;
import com.msglearning.javabackend.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StuffRepository extends CrudRepository<User, Long > {

    @Query("SELECT s FROM Stuff s WHERE s.owner.id = :ownerId")
    List<Stuff> findByOwner(@Param("ownerId") Long ownerId);

}
