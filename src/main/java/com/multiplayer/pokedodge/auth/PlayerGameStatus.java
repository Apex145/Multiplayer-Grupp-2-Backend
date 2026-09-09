package com.multiplayer.pokedodge.auth;

public class PlayerGameStatus {

    private String playerId;
    private String playerName;
    private int slot;
    private double x; // startposition x
    private double y; // fixed startposition value y
    private boolean alive;

    public PlayerGameStatus(String playerId, String playerName, int slot, double x, double y, boolean alive) {
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

    public double getX() {
        return x;
    }

    public PlayerGameStatus setX(double x) {
        // Ensure player does not go out of 
        // return this;the map
        if (x < 0.0) {
            this.x = 0.0;
        } else if (x > 100.0) {
            this.x = 100.0;
        } else {
            this.x = x;
        }
        return this;
    }

    public double getY() {
        return y;
    }

    public boolean isAlive() {
        return alive;
    }

    public PlayerGameStatus setAlive(boolean alive) {
        this.alive = alive;
        return this;
    }

}