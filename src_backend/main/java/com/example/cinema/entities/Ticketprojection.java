package com.example.cinema.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="ticketproj",types=Ticket.class)
public interface Ticketprojection {
    public Long getId();
    public String getNomClient();
    public double getPrix();
    public Integer getcodepayement();
    public boolean getreserve();
    public Place getplace();
}
