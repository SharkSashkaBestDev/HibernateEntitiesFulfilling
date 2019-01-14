package org.kpi.course.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlace")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Price")
    private Integer price;
    @Column(name = "DateStart")
    private Date dateStart;
    @Column(name = "DateEnd")
    private Date dateEnd;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idClient")
    private Client client;
}
