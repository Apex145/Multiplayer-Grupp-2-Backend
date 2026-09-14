package com.multiplayer.pokedodge.game;

import org.springframework.stereotype.Service;

import com.multiplayer.pokedodge.auth.PlayerGameStatus;
import static com.multiplayer.pokedodge.game.GameDimensions.*;

@Service 
public class CollisionService {

    public boolean isColliding(PlayerGameStatus player, FallingBlock block) {
        double playerCenterX = player.getX() + (PLAYER_VISUAL_WIDTH / 2.0);
        double blockCenterX = block.getX() + (BLOCK_WIDTH / 2.0);

        boolean sameHeight = Math.abs(block.getY() - player.getY()) < HITBOX;
        boolean overlapX = Math.abs(blockCenterX - playerCenterX) < (PLAYER_HITBOX_WIDTH + BLOCK_WIDTH) / 2.0;

        return sameHeight && overlapX;
    }
    
}