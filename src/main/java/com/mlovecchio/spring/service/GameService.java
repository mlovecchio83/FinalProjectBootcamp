package com.mlovecchio.spring.service;

import com.mlovecchio.spring.Payload.GameRequest;
import com.mlovecchio.spring.dao.GameRepository;
import com.mlovecchio.spring.dao.ItemRepository;
import com.mlovecchio.spring.exceptions.ExceptionHandlerAdvice;
import com.mlovecchio.spring.exceptions.ResourceException;
import com.mlovecchio.spring.model.Description;
import com.mlovecchio.spring.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.function.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Game> findAll()  {
        List<Game> games = (List<Game>) gameRepository.findAll();
        if(!games.isEmpty()){
            return games;
        }
        else {
            throw new ResourceException(HttpStatus.NOT_FOUND, "There are no games to show.");
        }
    }

    @Override
    public Game findGameById(Long id) {
        Game game= gameRepository.findById(id).orElse(null);
        if(game!=null){
            return game;
        }
        else{
            throw new ResourceException(HttpStatus.NOT_FOUND, "We can't find the game.");
        }
    }

    @Override
    public Game findByName(String name) {
        Game game = gameRepository.findByName(name);
        if(game != null){
            return game;
        }
        else {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We couldn't find the game");
        }
    }

    @Override
    public void save(Game game) {
        gameRepository.save(game);

    }



    @Override
    public void deleteById(Long id) {
        if(gameRepository.findById(id).isPresent()){
            Game game = gameRepository.findById(id).orElse(null);
            game.setActive(false);
            game.setId(id);
            gameRepository.save(game);
            throw new ResourceException(HttpStatus.OK, "Game successfully deleted");
        }
        else {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We couldn't find the game you want to erase");
        }

    }

    @Override
    public boolean validateGameExist(Long id) {
        return gameRepository.existsById(id);
    }

    @Override
    public void saveGame(GameRequest gameRequest) {
        Description description = new Description();
        description.setMobileDescription(gameRequest.getMobileDescription());
        description.setWebDescription(gameRequest.getWebDescription());

        Game game=new Game();
        game.setName(gameRequest.getName());
        game.setDescription(description);
        game.setActive(true);
        gameRepository.save(game);
        throw new ResourceException(HttpStatus.OK, "Game has been saved.");

    }

    @Override
    public void updateGame(GameRequest gameRequest, long id) {
        if (gameRepository.findById(id).isPresent()) {
            Game game = gameRepository.findById(id).get();
            game.setName(gameRequest.getName());
            gameRepository.save(game);
            throw new ResourceException(HttpStatus.OK, "Game modified successfuly.");
        }
        else
        {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We were unable to find the game.");
        }
    }


}
