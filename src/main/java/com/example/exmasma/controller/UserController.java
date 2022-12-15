package com.example.exmasma.controller;

import com.example.exmasma.entities.Project;
import com.example.exmasma.entities.User;
import com.example.exmasma.service.IService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class UserController {
    IService iService;

    @PostMapping("/addUser")
    @ResponseBody
    public User addEtudiant (@RequestBody User user){
        return iService.addUser(user);
    }
    @PostMapping("/addProject")
    @ResponseBody
    public Project addProject (@RequestBody Project project){
        return iService.addProject(project);
    }
    @PostMapping("/assignProjectToDevoloper/{projectId}/{devId}")
    public void assignProjectToDevoloper (@PathVariable("projectId")int projectId, @PathVariable("devId") int devId) {
        iService.assignProjectToDevoloper(projectId,devId);


    }
    @PostMapping("/assignProjectToScrum/{projectId}/{fName}/{lName}")
    public void assignProjectToScrum(@PathVariable("projectId")int projectId,@PathVariable("fName") String fName,@PathVariable("lName")String lName) {
        iService.assignProjectToScrum(projectId,fName,lName);

    }
    @GetMapping("/allprojectsBySm/{fName}/{lName}")
    public List<Project> getProjectsByScrumMaster(@PathVariable("fName")  String fName, @PathVariable("lName")  String lName ){

        return iService.getProjectsByScrumMaster(fName,lName);
    }

}
