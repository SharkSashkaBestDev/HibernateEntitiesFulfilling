package org.kpi.course.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTravel")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "DateStart")
    private Date dateStart;
    @Column(name = "DateEnd")
    private Date dateEnd;

    @OneToMany(mappedBy = "travel", fetch = FetchType.LAZY)
    private List<ClientService> clientServices;
}
