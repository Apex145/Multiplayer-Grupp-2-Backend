package com.multiplayer.pokedodge.game;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.multiplayer.pokedodge.auth.PlayerService;

@Component
public class GameLoop {

    private final PlayerService playerService;
    private final SimpMessagingTemplate msgTemp;
    private final GameService gameService;

    public GameLoop(PlayerService playerService, GameService gameService, SimpMessagingTemplate msgTemp) {
        this.gameService = gameService;
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

    @Scheduled(fixedRate = 400)
    public void spawnTick() {
        FallingBlock block = gameService.spawnRandomBlock();
        msgTemp.convertAndSend("/pokemon/spawnblocks" , block);
    }

    @Scheduled(fixedRate = 20)
    public void fallTick() {
        gameService.updateGameTick();
        msgTemp.convertAndSend("/pokemon/activeblocks" , gameService.getActiveFallingBlocks());
    }
}
