package com.multiplayer.pokedodge.auth;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("players")
public class Player {

    @Id
    String id;

    @Indexed(unique=true)
    String playerName;



    int gamesWon;

    public String getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Player setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public Player setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
        return this;
    }

    
}
