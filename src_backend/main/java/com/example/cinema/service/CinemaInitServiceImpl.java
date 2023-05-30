package com.example.cinema.service;
import java.text.*;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import com.example.cinema.dao.*;
import com.example.cinema.entities.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService {
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private SceanceRepository sceanceRepository;
    @Autowired
    private PalceRepository palceRepository;

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TicketRepository ticketRepository;
    public void initVilles() {
        Stream.of("casablanca", "marrakech", "rabat", "tanger").forEach(nameVille -> {
            Ville ville = new Ville();
            ville.setName(nameVille);
            villeRepository.save(ville);
        });
    }

    public void initCinemas() {
      villeRepository.findAll().forEach(v->{ Stream.of("megarama", "Imax", "founoun", "chahrazadr","daouliz").forEach(nameCinema -> {
          Cinema cinema=new Cinema();
          cinema.setName(nameCinema);
          cinema.setNombresalles(3+(int)(Math.random()*7));
         cinema.setVille(v);
         cinemaRepository.save(cinema);
      });});
    }

    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema->{
          for(int i=0;i<cinema.getNombresalles();i++){
              Salle salle=new Salle();
              salle.setName("selle"+(i+1));
              salle.setCinema(cinema);
              salle.setNombreplace(15+(int) (Math.random()*20));
              salleRepository.save(salle);
          }
        });
    }

    public void initSeances() {
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(s->{
    Sceance sceance=new Sceance();
    try{
        sceance.setHeuredebut(dateFormat.parse(s));
        sceanceRepository.save(sceance);
    }catch (ParseException e){
        e.printStackTrace();
    }

});
    }

    public void initPlaces() {
        salleRepository.findAll().forEach(salle->{
            for(int i=0;i<salle.getNombreplace();i++){
                Place place=new Place();
            place.setNumero(i+1);
                place.setSalle(salle);
              palceRepository.save(place);
            }
        });
    }

    public void initCategories() {
       Stream.of("histoires","actions","fiction","drama").forEach(cat->{
           Categorie categorie=new Categorie();
           categorie.setName(cat);
           categoryRepository.save(categorie);
       });
    }

    public void initfilms() {
        double[] durees=new double[] {1,1.5,2,2.5,3};
        List<Categorie> categories=categoryRepository.findAll();
       Stream.of("12 homes en colaire","Forrest gump","Green Book","La Ligne Verte","Le parin","Le seigneur des anneaux")
               .forEach(titrefilm->{
                   Film film=new Film();
                   film.setTitre(titrefilm);
                   film.setDuree(durees[new Random().nextInt(durees.length)]);
                   film.setPhoto(titrefilm.replaceAll(" "," ")+".jpg");
                   film.setCategorie(categories.get(new Random().nextInt(categories.size())));
                   filmRepository.save(film);
               });
    }

    public void initProjections() {
    double[] prices=new double[] {30,50,60,70,90,100};
    List<Film> films=  filmRepository.findAll();
    villeRepository.findAll().forEach(ville -> {
        ville.getCinema().forEach(cinema -> {
            cinema.getSalles().forEach(salle->{
                int index=new Random().nextInt(films.size());
              //  filmRepository.findAll().forEach(film ->
                Film film=films.get(index);
                        sceanceRepository.findAll().forEach(sceance -> {
                            Projection projection=new Projection();
                            projection.setDateProjection(new Date());
                            projection.setFilm(film);
                            projection.setPrix(prices[new Random().nextInt(prices.length)]);
                            projection.setSalle(salle);
                            projection.setSceance(sceance);
                            projectionRepository.save(projection);
                        });
            });
        });
    });
    }

    public void initTickets() {
        projectionRepository.findAll().forEach(p->{
            p.getSalle().getPlaces().forEach(place->{
                Ticket ticket=new Ticket();
                ticket.setPlace(place);
                ticket.setPrix(p.getPrix());
                ticket.setProjection(p);
                ticket.setReserve(false);
                ticketRepository.save(ticket);
            });
        });
    }
}
