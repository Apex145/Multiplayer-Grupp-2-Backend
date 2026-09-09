package com.multiplayer.pokedodge.game;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.multiplayer.pokedodge.auth.PlayerService;

@Component
public class GameLoop {

    private final PlayerService playerService;
    private final SimpMessagingTemplate msgTemp;

    public GameLoop(PlayerService playerService, SimpMessagingTemplate msgTemp) {
        this.playerService = playerService;
        this.msgTemp = msgTemp;
    }

    // Scheduled to make the server update every x milliseconds
    @Scheduled(fixedDelay = 20)
    public void tick() {
        if (playerService.getLoggedInPlayers().isEmpty()) {
            return;
        }
        playerService.tick();
        msgTemp.convertAndSend("/pokemon/state", playerService.getLoggedInPlayers());
    }
}
