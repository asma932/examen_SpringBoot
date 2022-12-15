package com.example.exmasma.service;

import com.example.exmasma.entities.Project;
import com.example.exmasma.entities.Sprint;
import com.example.exmasma.entities.User;
import com.example.exmasma.reposirory.ProjectRepository;
import com.example.exmasma.reposirory.SprintRepository;
import com.example.exmasma.reposirory.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;



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
    public Project addProject(Project projet) {
        Sprint sprint = saveSprint(projet);
        sprint.setProjet(projet);
        projectRepository.save(projet);
        return projet;
    }


    private Sprint saveSprint(Project projet) {

            Set<Sprint> Sprint = (Set<Sprint>) projet.getSprints();
            for (Sprint sprint : projet.getSprints()) {
                return sprintRepository.save(sprint);
            }
            return null;
        }
    @Override
    public void assignProjectToScrum(int projectId, String fName, String lName) {
        Project e = projectRepository.findById(projectId).orElse(null);
        User u= userRepository.finduserByNomEAndPrenomE(fName,lName);
        projectRepository.save(e);



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
         User u= userRepository.finduserByNomEAndPrenomE(fName, lname);
        List<Project > s = new ArrayList<>(u.getProjet());

        return s;
    }

}