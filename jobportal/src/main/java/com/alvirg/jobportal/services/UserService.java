package com.alvirg.jobportal.services;

import com.alvirg.jobportal.entity.JobSeekerProfile;
import com.alvirg.jobportal.entity.RecruiterProfile;
import com.alvirg.jobportal.entity.Users;
import com.alvirg.jobportal.repository.JobSeekerProfileRepository;
import com.alvirg.jobportal.repository.RecruiterProfileRepository;
import com.alvirg.jobportal.repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private final UsersRepository usersRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UsersRepository usersRepository,
                       JobSeekerProfileRepository jobSeekerProfileRepository,
                       RecruiterProfileRepository recruiterProfileRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users addNew(Users users){
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Users savedUser = usersRepository.save(users);

        if (users.getUserType() == null) {
            throw new IllegalArgumentException("User type must not be null");
        }

        int userTypeId = users.getUserType().getUserTypeId();
        if(userTypeId == 1){
            recruiterProfileRepository.save(new RecruiterProfile(savedUser));
        }else{
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }

        return savedUser;
    }

    public Optional<Users> getUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }
}
