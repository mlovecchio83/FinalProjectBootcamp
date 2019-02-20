package com.mlovecchio.spring.dao;

import com.mlovecchio.spring.model.Game;
import com.mlovecchio.spring.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface GameRepository extends CrudRepository<Game, Long> {


    Game findByName(String name);
}
