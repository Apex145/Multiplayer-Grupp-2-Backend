package com.multiplayer.pokedodge.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    private List<String> loggedInPlayers = new ArrayList<>();

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<String> getLoggedInPlayers() {
        return this.loggedInPlayers;
    }

    public Player loginPlayer(String playerName) {

        if (playerName == null || playerName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Playername required");
        } else {
            if (loggedInPlayers.size() >= 4) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lobby is full");
            }
            loggedInPlayers.add(playerName);
        }

        return playerRepository.findByPlayerName(playerName)
                .orElseGet(() -> playerRepository.save(new Player().setPlayerName(playerName)));
    }
}
