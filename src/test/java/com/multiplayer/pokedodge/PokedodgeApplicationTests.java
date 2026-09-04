package com.multiplayer.pokedodge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat; 

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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
import com.multiplayer.pokedodge.game.FallingBlock;
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

	Player createTestPlayer(String playerName){
		return new Player().setPlayerName(playerName);
	}


	@Test
	void canLoginOrCreatePlayer() {
		assertEquals("TestUser", createTestPlayer("TestPlayer").getPlayerName());
		assertThrows(ResponseStatusException.class, () -> playerService.loginPlayer(""));
		assertThrows(ResponseStatusException.class, () -> playerService.loginPlayer(null));
	}

	@Test
	void playerSpawnsAtMiddle() {
		PlayerGameStatus gameStatus = gameService.spawnPlayer(createTestPlayer("TestPlayer"), 1);
		assertEquals(gameStatus.getX(), 50);
	}

	@Test 
	void playerCanNotMoveOutsideOfGameFrame() {
		PlayerGameStatus gameStatus = gameService.spawnPlayer(createTestPlayer("TestPlayer"), 1);
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

		List<Integer> blockPositions = new ArrayList<Integer>();

		while (blockPositions.size() < 10){
			FallingBlock fallingBlock = gameService.spawnRandomBlock();
			if (fallingBlock.getX() > 0 && fallingBlock.getX() < 90 && !blockPositions.contains(fallingBlock.getX())) {
				blockPositions.add(fallingBlock.getX());
			};
		}

		assertEquals(10, blockPositions.size());
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
