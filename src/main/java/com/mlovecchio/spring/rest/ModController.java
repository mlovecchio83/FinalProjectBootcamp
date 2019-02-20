package com.mlovecchio.spring.rest;


import com.mlovecchio.spring.Payload.GameRequest;
import com.mlovecchio.spring.Payload.ModRequest;
import com.mlovecchio.spring.dao.ModRepository;
import com.mlovecchio.spring.model.Game;
import com.mlovecchio.spring.model.Mod;
import com.mlovecchio.spring.service.ModService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/mods")
public class ModController {

    @Autowired
    private ModService modService;

    @PostMapping("/addMod")
    public @ResponseBody void addNewMod(@RequestBody ModRequest modRequest){
        modService.saveMod(modRequest);
    }


    @GetMapping(path = "/allMods")
    public @ResponseBody  List<Mod> getAllMods(){
        return modService.findAll();
    }

    @GetMapping(path = "/findModById/{id}")
    public @ResponseBody Mod getModById (@PathVariable("id") Long id){
        return modService.findById(id);
    }

    @PostMapping(path = "/findByName")
    public @ResponseBody
    Mod findModByName(@RequestBody ModRequest modRequest) {
        return modService.findModByName(modRequest.getName());
    }


    @DeleteMapping(path = "/deleteMod/{id}")
    public @ResponseBody String deleteModById(@PathVariable("id") Long id){
        if(modService.validateModExists(id)){
            modService.deleteById(id);
            return "Mod Deleted";
        }
        else return "There is no mod with that id";
    }

    @PostMapping(path = "/update")
    public @ResponseBody String updateMod(@RequestBody ModRequest modRequest){
        if(modService.validateModExists(modRequest.getId())){
            modService.updateMod(modRequest, modRequest.getId());
            return "Mod updated";
        }
        return "There is no mod with that ID.";
    }

}
