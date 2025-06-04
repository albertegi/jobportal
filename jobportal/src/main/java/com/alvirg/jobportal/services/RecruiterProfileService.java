package com.alvirg.jobportal.services;

import com.alvirg.jobportal.entity.RecruiterProfile;
import com.alvirg.jobportal.repository.RecruiterProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository) {
        this.recruiterProfileRepository = recruiterProfileRepository;
    }

    public Optional<RecruiterProfile> getOneUserProfile(Integer id){
        return recruiterProfileRepository.findById(id);
    }
}
