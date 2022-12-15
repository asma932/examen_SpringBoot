package com.example.exmasma.service;

import com.example.exmasma.entities.Project;
import com.example.exmasma.entities.Sprint;
import com.example.exmasma.entities.User;
import com.example.exmasma.reposirory.ProjectRepository;
import com.example.exmasma.reposirory.SprintRepository;
import com.example.exmasma.reposirory.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




@AllArgsConstructor
@Service
public class ServiceImpl implements IService {
    UserRepository userRepository;
    ProjectRepository projectRepository;

    SprintRepository sprintRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);

    }

    @Override
    @Transactional
    public Project addProject(Project pro) {
        for(Sprint s : pro.getSprints()){

            s.setProjet(pro);
            sprintRepository.save(s);
        }
        return projectRepository.save(pro);
    }
    @Override
    public void assignProjectToScrum(int projectId, String fName, String lName) {
        Project p = projectRepository.findById(projectId).orElse(null);
        User u= userRepository.finduserByNomEAndPrenomE(fName,lName);
        p.getUsers().add(u);
        u.getProjet().add(p);
        userRepository.save(u);
    }



    @Override
    public void assignProjectToDevoloper(int projectId, int devId) {
        Project e = projectRepository.findById(projectId).orElse(null);
        User u = userRepository.findById(devId).orElse(null);
        u.getProjet().add(e);
        projectRepository.save(e);
    }

    @Override
    public void addSprintAndAssignToProject(Sprint sprint, int idProject) {
        Project project = projectRepository.findById(idProject).orElse(null);
        sprint.setProjet(project);
        sprintRepository.save(sprint);
    }
    @Override
    @Scheduled(cron = "*/30 * * * * *" )
    public void getProjectsCurrentSprints() {
        Date d = new Date(System.currentTimeMillis());
        List<Sprint> sprints = sprintRepository.ListSprintsInfADate(d);
        System.out.println("Listes des Sprints : ");
        for (Sprint s:sprints) {
            System.out.println("Sprint : Sprint Description : " +s.getDescription()+"\n Sprint startDate : "+s.getStartDate());
        }
    }

    @Override
    public List<Project> getProjectsByScrumMaster(String fName, String lname) {

            User user = userRepository.finduserByNomEAndPrenomE(fName,lname);
            List<Project> listProject = new ArrayList<>(user.getProjet());
            return listProject;
        }


}


