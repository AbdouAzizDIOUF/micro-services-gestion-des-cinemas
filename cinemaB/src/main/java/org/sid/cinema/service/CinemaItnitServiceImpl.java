package org.sid.cinema.service;

import org.sid.cinema.Repository.*;
import org.sid.cinema.entity.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaItnitServiceImpl implements ICinemaInitService {

    private final VilleRepository villeRepository;
    private final CinemaRepository cinemaRepository;
    private final SalleRepository salleRepository;
    private final FilmRepository filmRepository;
    private final CategorieRepository categorieRepository;
    private final PlaceRepository placeRepository;
    private final ProjectionRepository projectionRepository;
    private final SeanceRepository seanceRepository;
    private final TicketRepository ticketRepository;

    public CinemaItnitServiceImpl(VilleRepository villeRepository, CinemaRepository cinemaRepository, SalleRepository salleRepository, FilmRepository filmRepository, CategorieRepository categorieRepository, PlaceRepository placeRepository, ProjectionRepository projectionRepository, SeanceRepository seanceRepository, TicketRepository ticketRepository) {
        this.villeRepository = villeRepository;
        this.cinemaRepository = cinemaRepository;
        this.salleRepository = salleRepository;
        this.filmRepository = filmRepository;
        this.categorieRepository = categorieRepository;
        this.placeRepository = placeRepository;
        this.projectionRepository = projectionRepository;
        this.seanceRepository = seanceRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void initVilles() {
        Stream.of("Dakar", "Ziguinchor", "Thies", "Kaolack", "Tambacounda").forEach(v->{
            Ville ville =  new Ville();
            ville.setName(v);
            villeRepository.save(ville);
        });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(v->{
            Stream.of("Sembene Ousmane", "Canal Olympia", "Cinekap", "Leuz Studio", "Black Retina").forEach(nameCiema->{
                Cinema cinema = new Cinema();
                cinema.setName(nameCiema);
                cinema.setVille(v);
                cinema.setNombreSalles(3+ (int) (Math.random() * 7));
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema->{
            for (int i=0; i<cinema.getNombreSalles(); i++){
                Salle salle = new Salle();
                salle.setName("Salle "+(i+1));
                salle.setCinema(cinema);
                salle.setNombrePlace(15+(int)(Math.random()*20));
                salleRepository.save(salle);
            }
        });
    }


    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i=0; i<salle.getNombrePlace(); i++){
                Place place = new Place();
                place.setNumero(i+1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat =  new SimpleDateFormat("HH:mm");
        Stream.of("12:00", "15:00", "17:00", "19:00", "21:00").forEach(s -> {
            Seance seance = new Seance();
            try {
                seance.setHeureDebut(dateFormat.parse(s));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Histoire", "Action", "Fiction", "Drame", "Aventure").forEach(c->{
            Categorie categorie = new Categorie();
            categorie.setName(c);
            categorieRepository.save(categorie);
        });
    }

    @Override
    public void initFilms() {
        double[] durees = new double[]{1, 1.5, 2, 2.5, 3};
        List<Categorie> categories = categorieRepository.findAll();
        Stream.of("Game of the throw", "12 hommes en coleres", "The Soldier", "Survivant Designe", "The Last Schip", "Segneur des Anneaux", "Green Bock").forEach(f->{
            Film film = new Film();
            film.setTitle(f);
            film.setDuree(durees[new Random().nextInt(durees.length)]);
            film.setPhoto(f.replaceAll(" ", ""));
            film.setCategorie(categories.get(new Random().nextInt(categories.size())));
            filmRepository.save(film);

        });
    }

    @Override
    public void initProjections() {
        double[] prices = new double[]{30.0, 50.0, 60.0, 70.0, 90.0, 100};
        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    filmRepository.findAll().forEach(film -> {
                        seanceRepository.findAll().forEach(seance -> {
                            Projection projection = new Projection();
                            projection.setDateProjection(new Date());
                            projection.setFilm(film);
                            projection.setPrix(new Random().nextInt(prices.length));
                            projection.setSalle(salle);
                            projection.setSeance(seance);
                            projectionRepository.save(projection);
                        });
                    });
                });
            });
        });
    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(p->{
            p.getSalle().getPlaces().forEach(place -> {
                Ticket ticket = new Ticket();
                ticket.setPlace(place);
                ticket.setPrix(p.getPrix());
                ticket.setProjection(p);
                ticket.setReservee(false);
                ticketRepository.save(ticket);
            });
        });
    }
}
