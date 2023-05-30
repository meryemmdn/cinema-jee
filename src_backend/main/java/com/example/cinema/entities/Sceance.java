package com.example.cinema.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sceance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    @Temporal(TemporalType.TIME)
    private Date heuredebut;
}
