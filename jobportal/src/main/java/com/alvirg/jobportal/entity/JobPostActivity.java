package com.alvirg.jobportal.entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class JobPostActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobPostId;

    @ManyToOne
    @JoinColumn(name = "jobsPostedActivity_userId",
            referencedColumnName="userId")
    private Users userJobPostBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "joblocation_id",
            referencedColumnName = "id"
    )
    private JobLocation jobLocation;


    @ManyToOne(
            cascade = CascadeType.ALL)
    @JoinColumn(
            name = "jobCompany_id",
            referencedColumnName = "id"
    )
    private JobCompany jobCompany;

    @Transient
    private Boolean isActive;

    @Transient
    private Boolean isSaved;

    @Length(max = 10000)
    private String descriptionOfJob;

    private String jobType;
    private String jobTitle;
    private String salary;
    private String remote;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date postedDate;

    public JobPostActivity() {
    }

    public JobPostActivity(Integer jobPostId, Users userJobPostBy, JobLocation jobLocation, JobCompany jobCompany, Boolean isActive, Boolean isSaved, String descriptionOfJob, String jobType, String jobTitle, String salary, String remote, Date postedDate) {
        this.jobPostId = jobPostId;
        this.userJobPostBy = userJobPostBy;
        this.jobLocation = jobLocation;
        this.jobCompany = jobCompany;
        this.isActive = isActive;
        this.isSaved = isSaved;
        this.descriptionOfJob = descriptionOfJob;
        this.jobType = jobType;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.remote = remote;
        this.postedDate = postedDate;
    }

    public Integer getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Integer jobPostId) {
        this.jobPostId = jobPostId;
    }

    public Users getUserJobPostBy() {
        return userJobPostBy;
    }

    public void setUserJobPostBy(Users userJobPostBy) {
        this.userJobPostBy = userJobPostBy;
    }

    public JobLocation getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(JobLocation jobLocation) {
        this.jobLocation = jobLocation;
    }

    public JobCompany getJobCompany() {
        return jobCompany;
    }

    public void setJobCompany(JobCompany jobCompany) {
        this.jobCompany = jobCompany;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getSaved() {
        return isSaved;
    }

    public void setSaved(Boolean saved) {
        isSaved = saved;
    }

    public @Length(max = 10000) String getDescriptionOfJob() {
        return descriptionOfJob;
    }

    public void setDescriptionOfJob(@Length(max = 10000) String descriptionOfJob) {
        this.descriptionOfJob = descriptionOfJob;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    @Override
    public String toString() {
        return "JobPostActivity{" +
                "jobPostId=" + jobPostId +
                ", userJobPostBy=" + userJobPostBy +
                ", jobLocation=" + jobLocation +
                ", jobCompany=" + jobCompany +
                ", isActive=" + isActive +
                ", isSaved=" + isSaved +
                ", descriptionOfJob='" + descriptionOfJob + '\'' +
                ", jobType='" + jobType + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", salary='" + salary + '\'' +
                ", remote='" + remote + '\'' +
                ", postedDate=" + postedDate +
                '}';
    }
}
