package com.multiplayer.pokedodge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PokedodgeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void canCreateNewPlayer(){}

	@Test 
	void canLoginPlayer(){}

	@Test
	void playerCannotMovePastRightWall(){}

	@Test
	void playerCannotMovePastLeftWall(){}

	@Test
	void playerSpawnsAtMiddle(){}

	@Test
	void lastPlayerAliveWins(){}

	@Test
	void simultaneousEliminationEndsInDraw(){}

	@Test
	void leftArrowDecrementsXByN(){} // N represents whatever interval we want to use to increment/decrement player movement per tick

	@Test
	void rightArrowRightIncrementsXByN(){} 

	@Test
	void eliminatedPlayerCannotMove(){}

	////////////////////

	@Test
	void pokeBallsDropAtRandomPositions(){} 

	@Test
	void pokeBallCollidesWithPlayer(){}

	@Test
	void pokeBallIsRemovedWhenReachingZeroY(){}

	@Test
	void sameBallCanEliminateMultiplePlayers(){}

}
