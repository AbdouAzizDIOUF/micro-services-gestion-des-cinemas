package org.sid.cinema.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TicketForm{
    private String nomClient;
    private int codePayement;
    private List<Long> tickets = new ArrayList<>();
}