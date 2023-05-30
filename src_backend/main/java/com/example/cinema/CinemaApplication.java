package com.example.cinema;

import com.example.cinema.entities.Film;
import com.example.cinema.entities.Salle;
import com.example.cinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {
@Autowired
private ICinemaInitService cinemaInitService;
@Autowired
private RepositoryRestConfiguration restConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        restConfiguration.exposeIdsFor(Film.class, Salle.class);
cinemaInitService.initVilles();
cinemaInitService.initCinemas();
cinemaInitService.initSalles();
cinemaInitService.initPlaces();
cinemaInitService.initSeances();
cinemaInitService.initCategories();
cinemaInitService.initfilms();
cinemaInitService.initProjections();
cinemaInitService.initTickets();
    }
}
