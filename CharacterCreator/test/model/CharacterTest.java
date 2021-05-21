/**
 * 
 */
package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jeddy Waldon
 *
 */
public class CharacterTest {
	
	private Character zilkern;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		zilkern = new Character("Zilkern");
	}

	/**
	 * Test method for {@link model.Character#Character()}.
	 */
	@Test
	public void testCharacter() {
		assertNotNull(zilkern);
	}

	/**
	 * Test method for {@link model.Character#setScore(model.Score, int)}.
	 */
	@Test
	public void testSetScore() {
		int[] result = zilkern.getArray();
		zilkern.setScore(Score.Int, result[0]);
		zilkern.setScore(Score.Cha, result[1]);
		zilkern.setScore(Score.Con, result[2]);
		zilkern.setScore(Score.Dex, result[3]);
		zilkern.setScore(Score.Wis, result[4]);
		zilkern.setScore(Score.Str, result[5]);
		assertEquals(8, zilkern.getScore(Score.Str));
		assertEquals(12, zilkern.getScore(Score.Dex));
		assertEquals(13, zilkern.getScore(Score.Con));
		assertEquals(15, zilkern.getScore(Score.Int));
		assertEquals(10, zilkern.getScore(Score.Wis));
		assertEquals(14, zilkern.getScore(Score.Cha));
		
		try {
			zilkern.setScore(null, 5);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("That ability score doesn't exist!", e.getMessage());
		}
		
		zilkern.setScore(Score.Int, 1);
		try {
			zilkern.setScore(Score.Int, 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("That ability score is invalid!", e.getMessage());
		}
		
		zilkern.setScore(Score.Int, 30);
		try {
			zilkern.setScore(Score.Int, 31);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("That ability score is invalid!", e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link model.Character#getScore(model.Score)}.
	 */
	@Test
	public void testGetScore() {
		int[] result = zilkern.getArray();
		zilkern.setScore(Score.Int, result[0]);
		zilkern.setScore(Score.Cha, result[1]);
		zilkern.setScore(Score.Con, result[2]);
		zilkern.setScore(Score.Dex, result[3]);
		zilkern.setScore(Score.Wis, result[4]);
		zilkern.setScore(Score.Str, result[5]);
		assertEquals(8, zilkern.getScore(Score.Str));
		assertEquals(12, zilkern.getScore(Score.Dex));
		assertEquals(13, zilkern.getScore(Score.Con));
		assertEquals(15, zilkern.getScore(Score.Int));
		assertEquals(10, zilkern.getScore(Score.Wis));
		assertEquals(14, zilkern.getScore(Score.Cha));
		
		try {
			zilkern.getScore(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("That ability score doesn't exist!", e.getMessage());
		}
	}

	/**
	 * Test method for {@link model.Character#levelUp()}.
	 */
	@Test
	public void testLevelUp() {
		assertEquals(0, zilkern.getLevel());
		
		for (int i = 1; i <= 20; i++) {
			zilkern.levelUp();
			assertEquals(i, zilkern.getLevel());
		}
		try {
			zilkern.levelUp();
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("You're at max level!", e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link model.Character#roll3d6()}.
	 */
	@Test
	public void testRoll3d6() {
		zilkern = new Character("Zilkern", true);
		zilkern.roll3d6();
		int[] result = zilkern.getArray();
		assertEquals(6, result[0]);
		assertEquals(14, result[1]);
		assertEquals(7, result[2]);
		assertEquals(11, result[3]);
		assertEquals(10, result[4]);
		assertEquals(8, result[5]);
	}
	
	/**
	 * Test method for {@link model.Character#roll4d6DropLowest()}.
	 */
	@Test
	public void testRoll4d6DropLowest() {
		zilkern = new Character("Zilkern", true);
		zilkern.roll4d6DropLowest();
		int[] result = zilkern.getArray();
		assertEquals(10, result[0]);
		assertEquals(12, result[1]);
		assertEquals(12, result[2]);
		assertEquals(12, result[3]);
		assertEquals(13, result[4]);
		assertEquals(11, result[5]);
	}
	
	/**
	 * Test method for dice rolling. Must achieve max and min values for rolling within 100 tries. 
	 */
	@Test
	public void testMaxMinRoll() {
		int min = 18;
		int max = 3;
		for (int i = 0; i < 1000; i++) {
			zilkern.roll3d6();
			int[] result = zilkern.getArray();
			for (int j = 0; j < 6; j++) {
				if (result[j] < min) {
					min = result[j];
				} 
				if (result[j] > max) {
					max = result[j];
				}
			}
		}
		assertEquals(3, min);
		assertEquals(18, max);
		
		min = 18;
		max = 3;
		for (int i = 0; i < 10000; i++) {
			zilkern.roll4d6DropLowest();
			int[] result = zilkern.getArray();
			for (int j = 0; j < 6; j++) {
				if (result[j] < min) {
					min = result[j];
				} 
				if (result[j] > max) {
					max = result[j];
				}
			}
		}
		assertEquals(3, min);
		assertEquals(18, max);
	}

	/**
	 * Test method for {@link model.Character#getMods()}.
	 */
	@Test
	public void testGetMods() {
		
		zilkern.setScore(Score.Str, 3);
		zilkern.setScore(Score.Dex, 4);
		zilkern.setScore(Score.Con, 5);
		zilkern.setScore(Score.Int, 6);
		zilkern.setScore(Score.Wis, 7);
		zilkern.setScore(Score.Cha, 8);
		
		int[] results = zilkern.getMods();
		
		assertEquals(-4, results[0]);
		assertEquals(-3, results[1]);
		assertEquals(-3, results[2]);
		assertEquals(-2, results[3]);
		assertEquals(-2, results[4]);
		assertEquals(-1, results[5]);
		
		zilkern.setScore(Score.Str, 9);
		zilkern.setScore(Score.Dex, 10);
		zilkern.setScore(Score.Con, 11);
		zilkern.setScore(Score.Int, 12);
		zilkern.setScore(Score.Wis, 13);
		zilkern.setScore(Score.Cha, 14);
		
		results = zilkern.getMods();
		
		assertEquals(-1, results[0]);
		assertEquals(0, results[1]);
		assertEquals(0, results[2]);
		assertEquals(1, results[3]);
		assertEquals(1, results[4]);
		assertEquals(2, results[5]);
		
		zilkern.setScore(Score.Str, 15);
		zilkern.setScore(Score.Dex, 16);
		zilkern.setScore(Score.Con, 17);
		zilkern.setScore(Score.Int, 18);
		zilkern.setScore(Score.Wis, 19);
		zilkern.setScore(Score.Cha, 20);
		
		results = zilkern.getMods();
		
		assertEquals(2, results[0]);
		assertEquals(3, results[1]);
		assertEquals(3, results[2]);
		assertEquals(4, results[3]);
		assertEquals(4, results[4]);
		assertEquals(5, results[5]);
	}
	
	/**
	 * Test method for {@link model.Character#getMod()}.
	 */
	@Test
	public void testGetMod() {
		zilkern.setScore(Score.Str, 8);
		zilkern.setScore(Score.Dex, 12);
		zilkern.setScore(Score.Con, 13);
		zilkern.setScore(Score.Int, 15);
		zilkern.setScore(Score.Wis, 10);
		zilkern.setScore(Score.Cha, 14);
		
		assertEquals(-1, zilkern.getMod(Score.Str));
		assertEquals(1, zilkern.getMod(Score.Dex));
		assertEquals(1, zilkern.getMod(Score.Con));
		assertEquals(2, zilkern.getMod(Score.Int));
		assertEquals(0, zilkern.getMod(Score.Wis));
		assertEquals(2, zilkern.getMod(Score.Cha));
		
		try {
			zilkern.getMod(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("That ability score doesn't exist!", e.getMessage());
		}
	}

	/**
	 * Test method for {@link model.Character#getLevel()}.
	 */
	@Test
	public void testGetLevel() {
		assertEquals(0, zilkern.getLevel());
		zilkern.levelUp();
		assertEquals(1, zilkern.getLevel());
	}

	/**
	 * Test method for {@link model.Character#getClasses()}.
	 */
	@Test
	public void testGetClasses() {
		assertNull(zilkern.getClasses());
	}

	/**
	 * Test method for {@link model.Character#getBackground()}.
	 */
	@Test
	public void testGetBackground() {
		assertNull(zilkern.getBackground());
	}

	/**
	 * Test method for {@link model.Character#abilityScoreIncrease(model.Score, model.Score)}.
	 */
	@Test
	public void testAbilityScoreIncrease() {
		zilkern.setScore(Score.Str, 8);
		zilkern.setScore(Score.Dex, 12);
		zilkern.setScore(Score.Con, 13);
		zilkern.setScore(Score.Int, 15);
		zilkern.setScore(Score.Wis, 10);
		zilkern.setScore(Score.Cha, 14);
		
		zilkern.abilityScoreIncrease(Score.Int, null);
		assertEquals(16, zilkern.getScore(Score.Int));
		
		zilkern.abilityScoreIncrease(Score.Cha, Score.Cha);
		assertEquals(16, zilkern.getScore(Score.Cha));
		
		try {
			zilkern.abilityScoreIncrease(null, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("That ability score doesn't exist!", e.getMessage());
		}
	}

	/**
	 * Test method for {@link model.Character#getSavingThrow(model.Score)}.
	 */
	@Test
	public void testGetSavingThrow() {
		zilkern.setScore(Score.Str, 8);
		zilkern.setScore(Score.Dex, 12);
		zilkern.setScore(Score.Con, 13);
		zilkern.setScore(Score.Int, 15);
		zilkern.setScore(Score.Wis, 10);
		zilkern.setScore(Score.Cha, 14);
		
		assertEquals(-1, zilkern.getSavingThrow(Score.Str));
		assertEquals(1, zilkern.getSavingThrow(Score.Dex));
		assertEquals(1, zilkern.getSavingThrow(Score.Con));
		assertEquals(2, zilkern.getSavingThrow(Score.Int));
		assertEquals(0, zilkern.getSavingThrow(Score.Wis));
		assertEquals(2, zilkern.getSavingThrow(Score.Cha));
		
		try {
			zilkern.getSavingThrow(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("That ability score doesn't exist!", e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link model.Character#toString()}.
	 */
	@Test
	public void testToString() {
		zilkern.setScore(Score.Str, 8);
		zilkern.setScore(Score.Dex, 12);
		zilkern.setScore(Score.Con, 13);
		zilkern.setScore(Score.Int, 15);
		zilkern.setScore(Score.Wis, 10);
		zilkern.setScore(Score.Cha, 14);
		
		String eResult = "Zilkern\n0\n";
		assertEquals(eResult, zilkern.toString());
	}
}
