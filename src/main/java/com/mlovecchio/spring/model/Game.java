package com.mlovecchio.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.scenario.effect.Offset;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "games")
public class Game extends Item{


    @Column(name="mods")
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    List<Mod> mods;

    public List<Mod> getMods() {
        return mods;
    }

    public void setMods(List<Mod> mods) {
        this.mods = mods;
    }






}
