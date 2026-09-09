package com.multiplayer.pokedodge.game;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.multiplayer.pokedodge.auth.PlayerGameStatus;
import com.multiplayer.pokedodge.auth.PlayerService;
import com.multiplayer.pokedodge.game.GameService.LeaderBoardItem;

@Controller
public class GameWebSocketController {

    private PlayerService playerService;
    private GameService gameService;

    public GameWebSocketController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @MessageMapping("/game/players")
    @SendTo("/pokemon/players")
    public List<PlayerGameStatus> players() {
        return playerService.getLoggedInPlayers();
    }

    @MessageMapping("/game/move")
    @SendTo("/pokemon/game")
    public PlayerMoveMessage movePlayer(PlayerMoveMessage message) {
        return message;
    }

    @MessageMapping("/game/leaderboard")
    @SendTo("/pokemon/leaderboard")
    public List<LeaderBoardItem> leaderboard() {

        return gameService.getLeaderBoard();
    }

    @MessageMapping("/game/join")
    @SendTo("/pokemon/players")
    public List<PlayerGameStatus> join(String playerName, SimpMessageHeaderAccessor accessor) {
        playerService.joinLobby(accessor.getSessionId(), playerName.trim());
        return playerService.getLoggedInPlayers();
    }
    
    @MessageMapping("/game/start")
    @SendTo("/pokemon/start")
    public String startGame() {
        return "true";
    }
 
}