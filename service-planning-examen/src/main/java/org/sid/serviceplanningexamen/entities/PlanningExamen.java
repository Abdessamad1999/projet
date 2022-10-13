package org.sid.serviceplanningexamen.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class PlanningExamen {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomFiliere;
    private String AU;
    //@Transient
    //private AU AU;
    //private Long idAU
    private String session;
    private String semester;
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] avisExamen;
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] planningExamen;
}
