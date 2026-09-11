package com.multiplayer.pokedodge.auth;


public class PlayerGameStatus {

    private String playerId;
    private String sessionId;
    private String playerName;
    private int slot;
    private volatile double x; // volatile for trheading safety
    private int y; // fixed startposition value y
    private boolean alive;
    private volatile int direction; // -1 left, 0 still, 1 right. Server internal, never sent to clients.

    public PlayerGameStatus(String playerId, String playerName, int slot, double x, int y, boolean alive) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.slot = slot;
        this.x = x;
        this.y = y;
        this.alive = alive;
    }

    public PlayerGameStatus() {}

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

    public double getX() {
        return x;
    }

    public PlayerGameStatus setX(double x) {
        // Ensure player does not go out of the map
        if (x < 0.0) {
            this.x = 0.0;
        } else if (x > 100.0) {
            this.x = 100.0;
        } else {
            this.x = x;
        }
        return this;
    }

    // -1 left, 0 still, 1 right
    public PlayerGameStatus setDirection(int direction) {
        if (direction < -1) {
            this.direction = -1;
        } else if (direction > 1) {
            this.direction = 1;
        } else {
            this.direction = direction;
        }
        return this;
    }

    // called every tick of the gameloop
    public void advance(double step) {
        setX(this.x + this.direction * step);
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
