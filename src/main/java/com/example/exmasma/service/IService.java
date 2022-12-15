package com.example.exmasma.service;

import com.example.exmasma.entities.Project;
import com.example.exmasma.entities.Sprint;
import com.example.exmasma.entities.User;

import java.util.List;

public interface IService {

    public User addUser(User user);
    public Project addProject(Project projet);
    public void assignProjectToDevoloper(int projectId, int devId);
    public void assignProjectToScrum(int projectId,String fName,String lName);
    public void addSprintAndAssignToProject(Sprint sprint, int idProject);
    public void getProjectsCurrentSprints();
    List<Project> getProjectsByScrumMaster(String fName, String lname);


}
