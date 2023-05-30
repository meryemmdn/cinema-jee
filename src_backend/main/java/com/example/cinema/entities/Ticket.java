package com.example.cinema.entities;

import com.example.cinema.entities.Place;
import com.example.cinema.entities.Projection;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private  String nomclient;
    private double prix;
    @Column(unique = false,nullable =true)
    private Integer codepayement;
    private boolean reserve;
    @ManyToOne
    private Place place;
    @ManyToOne
   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Projection projection;
}
