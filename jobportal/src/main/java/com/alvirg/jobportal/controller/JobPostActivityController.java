package com.alvirg.jobportal.controller;

import com.alvirg.jobportal.entity.JobPostActivity;
import com.alvirg.jobportal.entity.Users;
import com.alvirg.jobportal.services.JobPostActivityService;
import com.alvirg.jobportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class JobPostActivityController {

    private final UserService userService;
    private final JobPostActivityService jobPostActivityService;



    @Autowired
    public JobPostActivityController(UserService userService,
                                     JobPostActivityService jobPostActivityService) {
        this.userService = userService;
        this.jobPostActivityService = jobPostActivityService;
    }

    @GetMapping("/dashboard/")
    public String searchJobs(Model model){

        Object currentUserProfile = userService.getCurrentUserProfile();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            model.addAttribute("username", currentUsername);
        }
        model.addAttribute("user", currentUserProfile);


        return "dashboard";
    }

    @GetMapping("/dashboard/add")
    public String addJobs(Model model){
        model.addAttribute("jobPostActivity", new JobPostActivity());
        model.addAttribute("user", userService.getCurrentUserProfile());
        return "add-jobs";
    }

    @PostMapping("/dashboard/addNew")
    public String addNew(JobPostActivity jobPostActivity, Model model){

        // Firs get the handle to the current user who's logged in.
        Users user = userService.getCurrentUser();

        if(user != null){
            jobPostActivity.setUserJobPostBy(user);
        }
        jobPostActivity.setPostedDate(new Date());
        model.addAttribute("jobPostActivity", jobPostActivity);
        JobPostActivity savedJobPostActivity = jobPostActivityService.addNew(jobPostActivity);



        return "redirect:/dashboard/";

    }



}
