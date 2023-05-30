package com.example.cinema.entities;

import com.fasterxml.jackson.databind.DatabindException;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;
import com.example.cinema.entities.Ticket;
@Projection(name="p1",types = {com.example.cinema.entities.Projection.class})
public interface Projectionproj {
    public Long getId();
    public double getprix();
    public Date getdateProjection();
    public Salle getsalle();
    public Film getFilm();
    public Sceance getsceance();
    public Collection<Ticket> getTickets();

}
