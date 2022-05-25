package com.msglearning.javabackend.services;

import com.msglearning.javabackend.converters.StuffConverter;
import com.msglearning.javabackend.converters.UserConverter;
import com.msglearning.javabackend.entity.Stuff;
import com.msglearning.javabackend.entity.User;
import com.msglearning.javabackend.repositories.StuffRepository;
import com.msglearning.javabackend.repositories.UserRepository;
import com.msglearning.javabackend.to.StuffTO;
import com.msglearning.javabackend.to.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StuffService {

    @Autowired
    StuffRepository stuffRepository;

    public List<StuffTO> findByOwner(Long userid) {
        List<StuffTO> toReturn = new ArrayList<>();

        return stuffRepository.findByOwner(userid).stream()
                .map(StuffConverter::convertToTO)
                .collect(Collectors.toList());
    }

}
