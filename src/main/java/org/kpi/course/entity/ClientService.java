package org.kpi.course.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class ClientService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClientService")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idClient")
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idTravel")
    private Travel travel;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idProduct")
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idRent")
    private RadioRent radio;
}
