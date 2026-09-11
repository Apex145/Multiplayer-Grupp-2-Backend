package com.multiplayer.pokedodge.game;

import java.util.UUID;

public class FallingBlock {
    private final String id;
    private int x;
    private double y;
    private double speed; // speed at which the block falls, per tick

    public FallingBlock(int x, double speed) {
        this.id = UUID.randomUUID().toString();
        this.x = x;
        this.y = 10; // start at the top
        this.speed = speed;
    }

    public void updateBlockPosition() {
        this.y += speed; // move the blocks downwards
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
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
