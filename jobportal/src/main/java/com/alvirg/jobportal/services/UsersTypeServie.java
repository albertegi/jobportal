package com.alvirg.jobportal.services;

import com.alvirg.jobportal.entity.UsersType;
import com.alvirg.jobportal.repository.UsersTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersTypeServie {

    private final UsersTypeRepository usersTypeRepository;

    @Autowired
    public UsersTypeServie(UsersTypeRepository usersTypeRepository) {
        this.usersTypeRepository = usersTypeRepository;
    }

    public List<UsersType> getAll(){
        return usersTypeRepository.findAll();
    }
}
