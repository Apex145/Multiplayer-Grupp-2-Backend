package com.multiplayer.pokedodge.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    private List<PlayerGameStatus> playersInLobby = new ArrayList<PlayerGameStatus>();

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerGameStatus> getLoggedInPlayers() {
        return playersInLobby;
    }

    public Player loginPlayer(String playerName) {

        if (playerName == null || playerName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Playername required");
        } 
        return playerRepository.findByPlayerName(playerName)
                .orElseGet(() -> playerRepository.save(new Player().setPlayerName(playerName)));
    }

    public void removePlayer(String sessionId) {
        for (PlayerGameStatus player : playersInLobby) {
            if (player.getSessionId().equals(sessionId)) {
                playersInLobby.remove(player);
            }
        }
    }

    public void joinLobby(String sessionId, String playerName) {
/*         if (loggedInPlayers.size() >= 4) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lobby is full");
        } */
            /// EJ PROPERLY IMPLEMENTERAD 
        PlayerGameStatus player = new PlayerGameStatus();            
        switch (playersInLobby.size()) {
            case 0 : player.setSlot(1); break;
            case 1 : player.setSlot(2); break;
            case 2 : player.setSlot(3); break;
            case 3 : player.setSlot(4); break;
            default : throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lobby is full");
        }
        player.setPlayerName(playerName);
        player.setSessionId(sessionId);

        playersInLobby.add(player);
    }
}
