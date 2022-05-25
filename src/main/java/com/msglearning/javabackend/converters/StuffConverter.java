package com.msglearning.javabackend.converters;

import com.msglearning.javabackend.entity.Stuff;
import com.msglearning.javabackend.entity.User;
import com.msglearning.javabackend.to.StuffTO;
import com.msglearning.javabackend.to.UserTO;

public class StuffConverter {

    public static final StuffTO convertToTO(Stuff entity) {
        return new StuffTO(entity.getId(), entity.getName(), entity.getValue(),
                entity.getOwner().getId());
    }

    public static final Stuff convertToEntity(StuffTO to) {
        return new Stuff(to.getId(), to.getName(), to.getValue(), null); //has to be set later
    }

}
