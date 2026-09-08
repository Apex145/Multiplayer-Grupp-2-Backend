package com.multiplayer.pokedodge.config;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.multiplayer.pokedodge.auth.PlayerService;

@Component
public class WebSocketDisconnectListener implements ApplicationListener<SessionDisconnectEvent> {

    private final PlayerService playerService;
    private final SimpMessagingTemplate msgTemp;

    public WebSocketDisconnectListener(PlayerService playerService, SimpMessagingTemplate msgTemp) {
        this.playerService = playerService;
        this.msgTemp = msgTemp;
    }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        playerService.removePlayer(event.getSessionId());
        msgTemp.convertAndSend("/pokemon/players",
                playerService.getLoggedInPlayers());
    }

}