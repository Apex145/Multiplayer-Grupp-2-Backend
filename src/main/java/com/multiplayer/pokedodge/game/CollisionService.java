package com.multiplayer.pokedodge.game;

import org.springframework.stereotype.Service;

import com.multiplayer.pokedodge.auth.PlayerGameStatus;
import static com.multiplayer.pokedodge.game.GameDimensions.*;

@Service 
public class CollisionService {

    public boolean isColliding(PlayerGameStatus player, FallingBlock block) {
        boolean sameHeight = Math.abs(block.getY() - player.getY()) < HITBOX;
        boolean overlapX = Math.abs(block.getX() - player.getX()) < (PLAYER_WIDTH + BLOCK_WIDTH) / 2.0;

        return sameHeight && overlapX;
    }
    
}


// @Service 
// public class CollisionService {

//     public boolean isColliding(PlayerGameStatus player, FallingBlock block) {
//         // Skriv ut koordinaterna i konsolen första gången för att verifiera skalan
//         // System.out.println("Block Y: " + block.getY() + " | Player Y: " + player.getY());

//         // 1. Överlapp i höjdled (Y-led)
//         boolean sameHeight = Math.abs(block.getY() - player.getY()) < HITBOX;
        
//         // 2. Överlapp i sidled (X-led)
//         boolean overlapX = Math.abs(block.getX() - player.getX()) < (PLAYER_WIDTH + BLOCK_WIDTH) / 2.0;

//         return sameHeight && overlapX;
//     }
// }