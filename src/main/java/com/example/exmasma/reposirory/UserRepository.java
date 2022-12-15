package com.example.exmasma.reposirory;

import com.example.exmasma.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
    User finduserByNomEAndPrenomE(String fName , String lName);

}
