package com.example.exmasma.reposirory;

import com.example.exmasma.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SprintRepository extends JpaRepository<Sprint,Integer> {
    @Query("SELECT  s FROM Sprint s where  s.startDate <=?1 ")
    List<Sprint> ListSprintsInfADate(Date dateAuj);
}
