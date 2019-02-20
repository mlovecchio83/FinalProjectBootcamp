package com.mlovecchio.spring.model;


import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.*;


@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name="users")
public class User extends BaseNamedEntity  {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name= "UserItem",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="item_id", referencedColumnName = "id"))
    private List<Item> items;


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }




}

