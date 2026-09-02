package com.multiplayer.pokedodge.auth;

import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import java.util.Optional;

@Service
public class PlayerService {


    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }


    public Player createNewPlayer(String playerName){

        Optional<Player> player = playerRepository.findByPlayerName(playerName);

        if (player.isPresent()){
            throw new MongoException("Player Already exists");
        }

        Player newPlayer = new Player()
            .setPlayerName(playerName);

        playerRepository.save(newPlayer);

        return newPlayer;
    }


}
