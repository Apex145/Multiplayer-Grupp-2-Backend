package com.multiplayer.pokedodge.game;

import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.multiplayer.pokedodge.auth.Player;
import com.multiplayer.pokedodge.auth.PlayerGameStatus;
import com.multiplayer.pokedodge.auth.PlayerService;

@Component
public class GameLoop {

    private final PlayerService playerService;
    private final SimpMessagingTemplate msgTemp;
    private final GameService gameService;
    private final CollisionService collisionService;

    public GameLoop(PlayerService playerService, GameService gameService, CollisionService collisionService,
            SimpMessagingTemplate msgTemp) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.collisionService = collisionService;
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
        if (playerService.getLoggedInPlayers().isEmpty()) {
            return;
        }
        FallingBlock block = gameService.spawnRandomBlock();
        msgTemp.convertAndSend("/pokemon/spawnblocks", block);
    }

    private void checkGameOver() {
        List<PlayerGameStatus> alivePlayers = playerService.getLoggedInPlayers()
                .stream()
                .filter(PlayerGameStatus::isAlive)
                .toList();

        if (alivePlayers.size() <= 1) {

            Player winner = gameService.endGame(playerService.getLoggedInPlayers());

            msgTemp.convertAndSend(
                    "/pokemon/gameover",
                    winner == null ? "DRAW" : winner.getPlayerName());

            playerService.resetPlayers();
        }
    }

    @Scheduled(fixedRate = 20)
    public void fallTick() {
        if (playerService.getLoggedInPlayers().isEmpty()) {
            return;
        }

        if (!gameService.isGameRunning()) {
            return;
        }
        gameService.updateGameTick();
        checkCollisions();
        checkGameOver();

        msgTemp.convertAndSend("/pokemon/activeblocks",
                gameService.getActiveFallingBlocks());
    }

    private void checkCollisions() {
        for (PlayerGameStatus player : playerService.getLoggedInPlayers()) {
            if (!player.isAlive())
                continue;

            boolean hit = gameService.getActiveFallingBlocks().stream()
                    .anyMatch(block -> collisionService.isColliding(player, block));

            if (hit) {
                player.setAlive(false);
            }
        }
    }
}
