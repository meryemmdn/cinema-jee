package com.example.cinema.web;

import com.example.cinema.dao.FilmRepository;
import com.example.cinema.dao.TicketRepository;
import com.example.cinema.entities.Film;
import com.example.cinema.entities.Ticket;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CinemaRestController {/*
    @Autowired
    private FilmRepository filmRepository;
    @GetMapping("/listFilms")
    public List<Film> films(){
        return filmRepository.findAll();
    }*/
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @GetMapping(path="/imageFilm/{id}",produces=MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name="id")Long id) throws Exception {
        Film f=filmRepository.findById(id).get();
        String photoname=f.getPhoto();
        File file=new File(System.getProperty("user.home")+"/cinema/images"+photoname);
        Path path= Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }
    @PostMapping("/payertickets")
    @Transactional
    public List<Ticket> payerticket(@RequestBody Ticketfrom ticketfrom){
        List<Ticket> listTicket=new ArrayList<>();
        ticketfrom.getTickets().forEach(idticket->{
            System.out.println(idticket);
            Ticket ticket=ticketRepository.findById(idticket).get();
            ticket.setNomclient(ticketfrom.getNomclent());
            ticket.setReserve(true);
            ticketRepository.save(ticket);
            listTicket.add(ticket);
        });
        return listTicket;
    }

}
@Data
class Ticketfrom{
    private String nomclent;
    private List<Long> tickets=new ArrayList<>();
}
