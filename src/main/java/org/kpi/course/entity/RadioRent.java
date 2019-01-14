package org.kpi.course.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
public class RadioRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRadioRent")
    private Integer id;
    @Column(name = "RentStart")
    private Date rentStart;
    @Column(name = "RentEnd")
    private Date rentEnd;

    @OneToMany(mappedBy = "radio", fetch = FetchType.LAZY)
    private List<ClientService> clientServices;
}
