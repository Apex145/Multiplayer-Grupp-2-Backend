package com.multiplayer.pokedodge.auth;

public class PlayerGameStatus {

    private String playerId;
    private String sessionId;
    private String playerName;
    private int slot;
    private int x; // startposition x
    private int y; // fixed startposition value y
    private boolean alive;

    public PlayerGameStatus(String playerId, String playerName, int slot, int x, int y, boolean alive) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.slot = slot;
        this.x = x;
        this.y = y;
        this.alive = alive;
    }

    public PlayerGameStatus(){}

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    

    public int getSlot() {
        return slot;
    }

    public PlayerGameStatus setSlot(int slot) {
        this.slot = slot;
        return this;
    }

    public int getX() {
        return x;
    }

    public PlayerGameStatus setX(int x) {
        // Ensure player does not go out of 
        // return this;the map
        if (x < 0.0) {
            this.x = 0;
        } else if (x > 100) {
            this.x = 100;
        } else {
            this.x = x;
        }
        return this;
    }

    public int getY() {
        return y;
    }

    public boolean isAlive() {
        return alive;
    }

    public PlayerGameStatus setAlive(boolean alive) {
        this.alive = alive;
        return this;
    }

    public PlayerGameStatus setPlayerId(String playerId) {
        this.playerId = playerId;
        return this;
    }

    public PlayerGameStatus setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public PlayerGameStatus setY(int y) {
        this.y = y;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public PlayerGameStatus setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }


}