package org.kpi.course.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFood")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Price")
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idClient")
    private Client client;
}
