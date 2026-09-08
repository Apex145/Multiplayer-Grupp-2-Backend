package com.multiplayer.pokedodge.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    private final Map<String, String> loggedInPlayers = new ConcurrentHashMap<>();

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<String> getLoggedInPlayers() {
        return new ArrayList<>(loggedInPlayers.values());
    }

    public Player loginPlayer(String playerName) {

        if (playerName == null || playerName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Playername required");
        } else {
            if (loggedInPlayers.size() >= 4) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lobby is full");
            }
        }

        return playerRepository.findByPlayerName(playerName)
                .orElseGet(() -> playerRepository.save(new Player().setPlayerName(playerName)));
    }

    public void removePlayer(String sessionId) {
        loggedInPlayers.remove(sessionId);
    }

    public void joinLobby(String sessionId, String playerName) {
        loggedInPlayers.put(sessionId, playerName);
    }
}
