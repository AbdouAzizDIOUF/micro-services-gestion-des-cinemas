package org.sid.cinema.controlleur;

import lombok.Data;
import org.sid.cinema.Repository.FilmRepository;
import org.sid.cinema.Repository.TicketRepository;
import org.sid.cinema.entity.Film;
import org.sid.cinema.entity.Ticket;
import org.sid.cinema.form.TicketForm;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
public class CinemaRestControlleur {

    private final FilmRepository filmRepository;
    private final TicketRepository ticketRepository;

    public CinemaRestControlleur(FilmRepository filmRepository, TicketRepository ticketRepository) {
        this.filmRepository = filmRepository;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping(path = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public  byte[] getImage(@PathVariable("id") Long id) throws IOException {
        Film f = filmRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/imagesProjects/l9-project/film/"+f.getPhoto()+".jpg"));
    }

    @PostMapping(path = "/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
        List<Ticket> listTickets = new ArrayList<>();
        ticketForm.getTickets().forEach(idTicket->{
            Ticket ticket = ticketRepository.findById(idTicket).get();
            ticket.setNomClient(ticketForm.getNomClient());
            ticket.setCodePayement((ticketForm.getCodePayement()));
            ticket.setReservee(true);
            ticketRepository.save(ticket);
            listTickets.add(ticket);
        });
        return listTickets;
    }
}
