package com.multiplayer.pokedodge.auth;

import org.springframework.stereotype.Service;



@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public Player loginPlayer(String playerName){
        return playerRepository.findByPlayerName(playerName)
            .orElseGet(() -> playerRepository.save(new Player().setPlayerName(playerName)));
    }

}
