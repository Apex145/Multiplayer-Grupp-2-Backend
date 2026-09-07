package com.multiplayer.pokedodge.game;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.multiplayer.pokedodge.auth.Player;
import com.multiplayer.pokedodge.auth.PlayerService;

@Controller
public class GameWebSocketController {

    private PlayerService playerService;

    public GameWebSocketController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @MessageMapping("/game/players")
    @SendTo("/pokemon/players")
    public List<String> players() {
        return playerService.getLoggedInPlayers();
    }

    @MessageMapping("/game/move")
    @SendTo("/pokemon/game")
    public PlayerMoveMessage movePlayer(PlayerMoveMessage message) {

        return message;
    }
}