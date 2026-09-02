package com.multiplayer.pokedodge.game;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameWebSocketController {

    @MessageMapping("/game/move")
    @SendTo("/pokemon/game")
    public PlayerMoveMessage movePlayer(PlayerMoveMessage message) {
        
        return message;
    }
}