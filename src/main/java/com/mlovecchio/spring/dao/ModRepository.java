package com.mlovecchio.spring.dao;

import com.mlovecchio.spring.model.Item;
import com.mlovecchio.spring.model.Mod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ModRepository extends CrudRepository<Mod, Long> {
    Mod findByName(String name);

}
