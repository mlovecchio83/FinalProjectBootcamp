package com.mlovecchio.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Where(clause = "active=1")
public abstract class Item extends BaseNamedEntity {


    @JsonIgnore
    @ManyToMany(mappedBy = "items")
    private Set<User> users= new HashSet<>();



    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    @Embedded
    Description description;




}
