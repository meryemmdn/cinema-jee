package com.example.cinema.entities;

import com.example.cinema.entities.Cinema;
import com.example.cinema.entities.Place;
import com.example.cinema.entities.Projection;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
private  String name;
private int nombreplace;
@ManyToOne
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
private Cinema cinema;
@OneToMany(mappedBy = "salle")

@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Place> places;
@OneToMany(mappedBy = "salle")

@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Projection> projection;
}
