package com.mlovecchio.spring.service;

import com.mlovecchio.spring.Payload.ModRequest;
import com.mlovecchio.spring.model.Mod;

import java.util.List;

public interface IModInterface {
    
    List<Mod> findAll();
    Mod findById(Long id);
    Mod findModByName(String name);
    void save(Mod mod);
    void deleteById(Long id);
    boolean validateModExists(Long id);
    void saveMod (ModRequest modRequest);
    void updateMod (ModRequest modRequest, long id);

}
