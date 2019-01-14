package org.kpi.course.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClient")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "SurName")
    private String surname;
    @Column(name = "MiddleName")
    private String middlename;
    @Column(name = "BirthDate")
    private Date birthdate;
    @Column(name = "Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Place> places;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<ClientService> clientServices;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Food> feed;
}
