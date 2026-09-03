package com.multiplayer.pokedodge.auth;

public class PlayerGameStatus {

    private final String playerId;
    private final String playerName;
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

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        // Ensure player does not go out of the map
        if (x < 0.0) {
            this.x = 0.0;
        } else if (x > 100.0) {
            this.x = 100.0;
        } else {
            this.x = x;
        }
    }

    public double getY() {
        return y;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}