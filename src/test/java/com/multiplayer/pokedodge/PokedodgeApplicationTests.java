package com.multiplayer.pokedodge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat; 

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.mongodb.MongoException;
import com.multiplayer.pokedodge.auth.Player;
import com.multiplayer.pokedodge.auth.PlayerGameStatus;
import com.multiplayer.pokedodge.auth.PlayerRepository;
import com.multiplayer.pokedodge.auth.PlayerService;
import com.multiplayer.pokedodge.game.GameService;

@SpringBootTest
class PokedodgeApplicationTests {

	@Autowired
	PlayerService playerService;

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	GameService gameService;

	@Test
	void contextLoads() {
	}

/* 	@Test
	void canCreateNewPlayer() {
		Player player = playerService.createNewPlayer("TestNewPlayer");
		assertEquals("TestNewPlayer", player.getPlayerName());
	}

	@Test
	void playerAlreadyExists() {

		assertThrows(
				MongoException.class,
				() -> playerService.createNewPlayer("TestNewPlayer"));
	} */


	Player createTestPlayer(String playerName){
		return new Player().setPlayerName(playerName);
	}


	@Test
	void canLoginOrCreatePlayer() {
		Player player = playerService.loginPlayer("TestUser");
		assertEquals("TestUser", player.getPlayerName());
		assertThrows(ResponseStatusException.class, () -> playerService.loginPlayer(""));
		assertThrows(ResponseStatusException.class, () -> playerService.loginPlayer(null));
	}

	@Test
	void playerCannotMovePastRightWall() {

	}

	@Test
	void playerCannotMovePastLeftWall() {
	}

	@Test
	void playerSpawnsAtMiddle() {
		Player testPlayer = createTestPlayer("TestPlayer");
		PlayerGameStatus gameStatus = gameService.spawnPlayer(testPlayer, 1);
		assertEquals(gameStatus.getX(), 50);
	}

	@Test 
	void playerCanNotMoveOutsideOfGameFrame() {
		Player testPlayer = createTestPlayer("TestPlayer");
		PlayerGameStatus gameStatus = gameService.spawnPlayer(testPlayer, 1);
		gameStatus.setX(-1);
		assertEquals(0, gameStatus.getX());
		gameStatus.setX(101);
		assertEquals(100, gameStatus.getX());
	}

	@Test
	void lastPlayerAliveWins() {
	}

	@Test
	void simultaneousEliminationEndsInDraw() {
	}

	@Test
	void leftArrowDecrementsXByN() {
	} // N represents whatever interval we want to use to increment/decrement player
		// movement per tick

	@Test
	void rightArrowRightIncrementsXByN() {
	}

	@Test
	void eliminatedPlayerCannotMove() {
	}

	////////////////////

	@Test
	void pokeBallsDropAtRandomPositions() {
	}

	@Test
	void pokeBallCollidesWithPlayer() {
	}

	@Test
	void pokeBallIsRemovedWhenReachingZeroY() {
	}

	@Test
	void sameBallCanEliminateMultiplePlayers() {
	}

}
