package org.sid.cinema.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double prix;
    private String nomClient;
    @Column(unique = false, nullable = true)
    private Integer codePayement;
    private boolean reservee;
    @ManyToOne
    private Place place;
    @ManyToOne
    private Projection projection;
}
