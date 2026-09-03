package com.multiplayer.pokedodge.game;

import java.util.UUID;

public class FallingBlock {
    private final String id;
    private double x;
    private double y;
    private double speed; // speed at which the block falls, per tick

    public FallingBlock(double x, double speed) {
        this.id = UUID.randomUUID().toString();
        this.x = x;
        this.y = 100.0; // start at the top
        this.speed = speed;
    }

    public void updateBlockPosition() {
        this.y -= speed; // move the blocks downwards
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}
