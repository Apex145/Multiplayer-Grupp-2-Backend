package com.multiplayer.pokedodge.auth;

import java.util.Optional;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {

    Optional<Player> findByPlayerName(String playerName);

    List<Player> findTop4ByOrderByGamesWonDesc();
}
