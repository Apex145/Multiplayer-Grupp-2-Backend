package com.multiplayer.pokedodge.auth;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }


    public Player loginPlayer(String playerName){

        if (playerName == null || playerName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Playername required");
        }

        return playerRepository.findByPlayerName(playerName)
            .orElseGet(() -> playerRepository.save(new Player().setPlayerName(playerName)));
    }

}
