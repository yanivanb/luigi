package be.vdab.luigi.domain;

import java.time.LocalDate;

public class Gast {
    private final long id;
    private final LocalDate datum;
    private final String naam;
    private final String bericht;

    public Gast(long id, LocalDate datum, String naam, String bericht) {
        this.id = id;
        this.datum = datum;
        this.naam = naam;
        this.bericht = bericht;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public String getNaam() {
        return naam;
    }

    public String getBericht() {
        return bericht;
    }
}
