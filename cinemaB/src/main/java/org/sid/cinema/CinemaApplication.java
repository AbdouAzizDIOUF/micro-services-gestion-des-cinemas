package org.sid.cinema;

import org.sid.cinema.entity.*;
import org.sid.cinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("*")
public class CinemaApplication implements CommandLineRunner {

    public CinemaApplication(ICinemaInitService iCinemaInitService, RepositoryRestConfiguration repositoryRestConfiguration) {
        repositoryRestConfiguration.exposeIdsFor(
                Projection.class,
                Ville.class,
                Cinema.class,
                Film.class,
                Place.class,
                Salle.class,
                Ville.class,
                Seance.class,
                Ticket.class
        );
    }

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
  /*      iCinemaInitService.initVilles();
        iCinemaInitService.initCinemas();
        iCinemaInitService.initSalles();
        iCinemaInitService.initPlaces();
        iCinemaInitService.initSeances();
        iCinemaInitService.initCategories();
        iCinemaInitService.initFilms();
        iCinemaInitService.initProjections();
        iCinemaInitService.initTickets();*/
    }
}
