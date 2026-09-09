package com.multiplayer.pokedodge.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth/player")
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/login")
    public Player tryLogin(@RequestBody String playerName) {
        return playerService.loginPlayer(playerName.trim());
    }

    @PostMapping("/logout")
    public void tryLogout(@RequestBody String playerSessionId) {
        playerService.removePlayer(playerSessionId);      
    }

}
