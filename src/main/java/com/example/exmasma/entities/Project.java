package com.example.exmasma.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public class Project {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id ;
        private String title;
        private String description;
        @OneToMany(mappedBy = "projet")
        private Set<Sprint> sprints;

        @ManyToMany(mappedBy = "projets")
        private Set<User>users;
    }

