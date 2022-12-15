package com.example.exmasma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sprint {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id ;
        private Date startDate;
        private String description;
        @ManyToOne
        @JsonIgnore
        private Project projet;
}