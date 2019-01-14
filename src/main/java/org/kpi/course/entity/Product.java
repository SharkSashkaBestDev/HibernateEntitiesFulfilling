package org.kpi.course.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Price")
    private Integer price;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ClientService> clientServices;
}
