package com.mlovecchio.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="mods")
public class Mod extends Item {

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "games_id")//, referencedColumnName = "id")
    @JsonIgnore
    Game game;


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }



}
