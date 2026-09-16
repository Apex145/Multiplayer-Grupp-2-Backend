package com.multiplayer.pokedodge.auth;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlayerService {

    private static final int MAX_PLAYERS = 4;
    private static final double TICK_STEP = 2.5; // how far a player moves per tick

    private PlayerRepository playerRepository;

    private final Map<String, PlayerGameStatus> playersInLobby = new ConcurrentHashMap<>();

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void setPlayersAlive() {
        playersInLobby.forEach((sessionId, player) -> player.setAlive(true));
    }

    public List<PlayerGameStatus> getLoggedInPlayers() {
        return playersInLobby.values().stream()
                .sorted(Comparator.comparingInt(PlayerGameStatus::getSlot))
                .toList();
    }

    public Player loginPlayer(String playerName) {

        if (playerName == null || playerName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Playername required");
        }
        return playerRepository.findByPlayerName(playerName)
                .orElseGet(() -> playerRepository.save(new Player().setPlayerName(playerName)));
    }

    public void removePlayer(String sessionId) {
        playersInLobby.remove(sessionId);
    }

    public void joinLobby(String sessionId, String playerName) {
        if (playersInLobby.containsKey(sessionId)) {
            return;
        }
        playersInLobby.put(sessionId, new PlayerGameStatus()
                .setPlayerName(playerName)
                .setSessionId(sessionId)
                .setSlot(setPlayerSlot())
                .setX(50)
                .setY(100)
                .setAlive(true));
    }

    private int setPlayerSlot() {
        if (playersInLobby.size() >= MAX_PLAYERS) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lobby is full");
        }
        return playersInLobby.size() + 1;
    }

    public void setDirection(String sessionId, String direction) {
        PlayerGameStatus player = playersInLobby.get(sessionId);
        if (player != null) {
            switch (direction) {
                case "left" -> player.setDirection(-1);
                case "right" -> player.setDirection(1);
                case "none" -> player.setDirection(0);
            }
        }
    }

    public void resetPlayers() {
        for (PlayerGameStatus player : playersInLobby.values()) {
            player.setAlive(true);
            player.setX(50);
            player.setY(100);
            player.setDirection(0);
        }
    }

    public void tick() {
        for (PlayerGameStatus player : playersInLobby.values()) {
            player.advance(TICK_STEP);
        }
    }

}
