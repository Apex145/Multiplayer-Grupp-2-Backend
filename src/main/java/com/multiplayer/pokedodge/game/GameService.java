package com.multiplayer.pokedodge.game;

import com.multiplayer.pokedodge.auth.Player;
import com.multiplayer.pokedodge.auth.PlayerGameStatus;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GameService {

    private static final int PLAYER_SPAWN_X_PERCENT = 50; // 50% off entire width of the game area
    private static final int PLAYER_SPAWN_Y_POSITION = 0; // bottom of the game area

    public PlayerGameStatus spawnPlayer(Player player, int slot) {
        return new PlayerGameStatus(
                player.getId(),
                player.getPlayerName(),
                slot,
                PLAYER_SPAWN_X_PERCENT,
                PLAYER_SPAWN_Y_POSITION,
                true);
    }

    // FALLING BLOCKS

    private List<FallingBlock> activeFallingBlocks = new ArrayList<>();
    private static int MIN_X = 0;
    private static int MAX_X = 90;
    private static int DEFAULT_SPEED = 2; // block falls, per tick

    // spawn fallingblock at random x position
    // MIGHT HAVE TO TAKE ANOTHER LOOK INTO//
    // THE RANDOMIZATION FUNCTION
    public FallingBlock spawnRandomBlock() {
        int randomX = (int) Math.floor(Math.random() * (MAX_X - MIN_X));
        FallingBlock block = new FallingBlock(randomX, DEFAULT_SPEED);
        activeFallingBlocks.add(block);
        return block;
    }

    public void updateGameTick() {
        for (FallingBlock block : activeFallingBlocks) {
            block.updateBlockPosition();
        }
        // Remove blocks when bottom is reached
        activeFallingBlocks.removeIf(block -> block.getY() < 0);
    }

    public List<FallingBlock> getActiveFallingBlocks() {
        return activeFallingBlocks;
    }
}
