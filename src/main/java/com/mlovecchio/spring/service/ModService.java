package com.mlovecchio.spring.service;

import com.mlovecchio.spring.Payload.ModRequest;
import com.mlovecchio.spring.dao.GameRepository;
import com.mlovecchio.spring.dao.ItemRepository;
import com.mlovecchio.spring.dao.ModRepository;
import com.mlovecchio.spring.exceptions.ResourceException;
import com.mlovecchio.spring.model.Description;
import com.mlovecchio.spring.model.Game;
import com.mlovecchio.spring.model.Mod;
import javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("Duplicates")
public class ModService implements IModInterface {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ModRepository modRepository;




    @Override
    public List<Mod> findAll(){
        List<Mod> mods = (List<Mod>) modRepository.findAll();
        if(!mods.isEmpty()){
            return mods;
        }
        else{
            throw new ResourceException(HttpStatus.NOT_FOUND, "There are no mods.");
        }

    }

    @Override
    public Mod findById(Long id) {
        Mod mod=modRepository.findById(id).orElse(null);
        if(mod!=null){
            return mod;
        }
        else {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We couldn't find the mod");
        }
    }

    @Override
    public Mod findModByName(String name) {
        Mod mod = modRepository.findByName(name);
        if(mod!=null){
            return mod;
        }
        else{
            throw new ResourceException(HttpStatus.NOT_FOUND, "We couldn't find the mod");
        }

    }

    @Override
    public void save(Mod mod) {
        modRepository.save(mod);
    }

    @Override
    public void deleteById(Long id) {
        if (modRepository.findById(id).isPresent()){
            Mod mod = modRepository.findById(id).orElse(null);
            mod.setActive(false);
            mod.setId(id);
            modRepository.save(mod);
            throw new ResourceException(HttpStatus.OK, "Mod deleted successfully.");
        }
        else
        {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We couldn't find the mod.");
        }
    }

    @Override
    public boolean validateModExists(Long id) {
        return modRepository.existsById(id);
    }

    @Override
    public void saveMod(ModRequest modRequest) {

        Game game = gameRepository.findById(modRequest.getId()).orElse(null);
        if(game == null && game.isActive()==false)
        {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We were unable to find the mod.");
        }
        else
        {
            Mod mod = new Mod();
            Description description = new Description();

            description.setMobileDescription(modRequest.getMobileDescription());
            description.setWebDescription(modRequest.getWebDescription());
            mod.setDescription(description);

            mod.setGame(game);
            mod.setName(modRequest.getName());
            mod.setActive(true);
            modRepository.save(mod);
            throw new ResourceException(HttpStatus.OK, "Mod saved successfuly.");
        }

    }

    @Override
    public void updateMod(ModRequest modRequest, long id) {

        if (modRepository.findById(id).isPresent()) {

            Mod mod = modRepository.findById(id).get();
            mod.setName(modRequest.getName());
            modRepository.save(mod);
            throw new ResourceException(HttpStatus.OK, "Mod modified successfuly.");
        }
        else
        {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We were unable to find the mod.");
        }
    }



}
