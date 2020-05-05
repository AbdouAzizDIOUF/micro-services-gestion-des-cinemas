package org.sid.cinema.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.Date;

@CrossOrigin("*")
@Projection(name = "p1", types = {org.sid.cinema.entity.Projection.class})
public interface ProjectionProjection {
    /*@Value("#{target.id}")*/
    public Long getId();
    public double getPrix();
    public Date getDateProjection();
    public Salle getSalle();
    public Film getFilm();
    public Seance getSeance();
    public Collection<Ticket> getTickets();
}
