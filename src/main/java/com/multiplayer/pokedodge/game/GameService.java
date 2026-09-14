package com.multiplayer.pokedodge.game;

import com.multiplayer.pokedodge.auth.Player;
import com.multiplayer.pokedodge.auth.PlayerGameStatus;
import com.multiplayer.pokedodge.auth.PlayerRepository;
import com.multiplayer.pokedodge.auth.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private static final int PLAYER_SPAWN_X_PERCENT = 50; // 50% off entire width of the game area
    private static final int PLAYER_SPAWN_Y_POSITION = 0; // bottom of the game area

    private PlayerRepository playerRepository;

    public GameService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

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
    private static int MAX_X = 115;
    private static double DEFAULT_SPEED = 0.5;
    private static final double GROUND_Y = 100.0; // block falls, per tick

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
        activeFallingBlocks.removeIf(block -> block.getY() > GROUND_Y);
    }

    public List<FallingBlock> getActiveFallingBlocks() {
        return activeFallingBlocks;
    }

    public List<LeaderBoardItem> getLeaderBoard() {
        List<LeaderBoardItem> leaderboard = new ArrayList<LeaderBoardItem>();
        for (Player player : playerRepository.findTop4ByOrderByGamesWonDesc()) {
            leaderboard.add(new LeaderBoardItem(player.getPlayerName(), player.getGamesWon()));
        }
        return leaderboard;
    }

    public static record LeaderBoardItem(
            String player,
            int gamesWon) {
    }

}
