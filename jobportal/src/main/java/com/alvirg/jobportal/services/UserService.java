package com.alvirg.jobportal.services;

import com.alvirg.jobportal.entity.Users;
import com.alvirg.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users addNew(Users users){
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        return usersRepository.save(users);
    }

    public Optional<Users> getUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }
}
