package com.mlovecchio.spring.service;

import com.mlovecchio.spring.Payload.GameRequest;
import com.mlovecchio.spring.exceptions.ResourceException;
import com.mlovecchio.spring.model.Game;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public interface IGameService {
    List<Game> findAll() throws ResourceException;
    Game findGameById(Long id);
    Game findByName(String name);
    void save(Game game);
    void deleteById(Long id);
    boolean validateGameExist(Long id);
    void saveGame (GameRequest gameRequest);
    void updateGame (GameRequest gameRequest, long id);

}
