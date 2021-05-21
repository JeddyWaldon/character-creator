/**
 * 
 */
package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jeddywaldon
 *
 */
public class SkillTest {
	
	private Skill arcana;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		arcana = new Skill(3, ProfLevel.Normal, 2, "Arcana");
	}

	/**
	 * Test method for {@link model.Skill#Skill(int, model.ProfLevel, int, java.lang.String)}.
	 */
	@Test
	public void testSkill() {
		assertNotNull(arcana);
	}

	/**
	 * Test method for {@link model.Skill#setMod(int)}.
	 */
	@Test
	public void testSetMod() {
		arcana.setMod(4);
		assertEquals(4, arcana.getCheckBonus());
		
		arcana.setMod(-5);
		
		try {
			arcana.setMod(-6);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Illegal modifier.", e.getMessage());
		}
		
		arcana.setMod(10);
		
		try {
			arcana.setMod(11);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Illegal modifier.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link model.Skill#setProfLevel(model.ProfLevel)}.
	 */
	@Test
	public void testSetProfLevel() {
		arcana.setProfLevel(ProfLevel.Proficient);
		assertEquals(5, arcana.getCheckBonus());
		try {
			arcana.setProfLevel(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("This proficiency level does not exist.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link model.Skill#setBonus(int)}.
	 */
	@Test
	public void testSetBonus() {
		arcana.setBonus(3);
		assertEquals(3, arcana.getCheckBonus());
		arcana.setProfLevel(ProfLevel.Proficient);
		assertEquals(6, arcana.getCheckBonus());
		
		try {
			arcana.setBonus(1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Illegal proficiency bonus.", e.getMessage());
		}
		
		arcana.setBonus(6);
		
		try {
			arcana.setBonus(7);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Illegal proficiency bonus.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link model.Skill#getCheckBonus()}.
	 */
	@Test
	public void testGetCheckBonus() {
		arcana.setProfLevel(ProfLevel.Expert);
		assertEquals(7, arcana.getCheckBonus());
	}

	/**
	 * Test method for {@link model.Skill#getProficiencyLevel()}.
	 */
	@Test
	public void testGetProficiencyLevel() {
		assertEquals(ProfLevel.Normal, arcana.getProficiencyLevel());
		arcana.setProfLevel(ProfLevel.Expert);
		assertEquals(ProfLevel.Expert, arcana.getProficiencyLevel());
	}

	/**
	 * Test method for {@link model.Skill#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("+3 Arcana\n", arcana.toString());
		arcana.setProfLevel(ProfLevel.Proficient);
		assertEquals("* +5 Arcana\n", arcana.toString());
		arcana.setProfLevel(ProfLevel.Expert);
		assertEquals("! +7 Arcana\n", arcana.toString());
		arcana.setMod(-5);
		assertEquals("! -1 Arcana\n", arcana.toString());
	}

}
