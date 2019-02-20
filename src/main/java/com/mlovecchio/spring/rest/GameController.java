package com.mlovecchio.spring.rest;

import com.mlovecchio.spring.Payload.GameRequest;
import com.mlovecchio.spring.dao.GameRepository;
import com.mlovecchio.spring.dao.ItemRepository;
import com.mlovecchio.spring.model.Game;
import com.mlovecchio.spring.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping(path="/games")
public class GameController {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameService gameService;

    @Autowired
    ItemRepository itemRepository;

    private List<Game> game;

    @PostMapping(path="/addGame")
    public @ResponseBody void addNewGame(@RequestBody GameRequest gameRequest) {
        gameService.saveGame(gameRequest);
    }

    @GetMapping(path = "/allGames")
    public @ResponseBody List<Game> findAll(){
        List<Game> games= (List<Game>) gameRepository.findAll();
        return games;
    }

    @GetMapping(path = "/findGame/{id}")
    public @ResponseBody
    Optional<Game> findGameById(@PathVariable("id") Long id) {
        return gameRepository.findById(id);
    }

    @PostMapping(path = "/findByName")
    public @ResponseBody Game findByName(@RequestBody GameRequest gameRequest) {
        return gameService.findByName(gameRequest.getName());
    }


    @DeleteMapping("/deleteGame/{id}")
    public @ResponseBody String deleteById(@PathVariable("id") Long id){
        if(gameService.validateGameExist(id) == true){
            gameService.deleteById(id);
            return "Game erased";
        }
        gameRepository.deleteById(id);
        return "Game doesn't exist";
    }

    @PostMapping(path = "/update/{id}")
    public @ResponseBody void updateGame(@RequestBody GameRequest gameRequest, @PathVariable(value="id") long id){
        gameService.updateGame(gameRequest, id);
    }


}
