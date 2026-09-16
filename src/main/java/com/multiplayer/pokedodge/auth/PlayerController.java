package com.multiplayer.pokedodge.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.multiplayer.pokedodge.game.GameService;

@RestController
@RequestMapping("/api/auth/player")
public class PlayerController {

    private PlayerService playerService;
    private GameService gameservice;

    public PlayerController(PlayerService playerService, GameService gameservice) {
        this.playerService = playerService;
        this.gameservice = gameservice;
    }

    @PostMapping("/login")
    public Player tryLogin(@RequestBody String playerName) {
        return playerService.loginPlayer(playerName.trim());
    }

}
