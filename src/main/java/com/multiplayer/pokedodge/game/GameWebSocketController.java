package com.multiplayer.pokedodge.game;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.multiplayer.pokedodge.auth.Player;

@Controller
public class GameWebSocketController {

    @MessageMapping("/game/players")
    @SendTo("/pokemon/players")
    public String players(Player player) {

        return player.getPlayerName();

    }

    @MessageMapping("/game/move")
    @SendTo("/pokemon/game")
    public PlayerMoveMessage movePlayer(PlayerMoveMessage message) {

        return message;
    }
}