package model;

import java.util.Random;

import model.backgrounds.Background;
import model.classes.CharacterClass;

public class Character {
	
	private String name;
	
	private int[] mods;
	
	private int strength;
	
	private int dexterity;
	
	private int constitution;
	
	private int intelligence;
	
	private int wisdom;
	
	private int charisma;
	
	private int level;
	
	private static final int MAX_ABILITY_SCORE = 30;
	
	private static final int[] STANDARD_ARRAY = { 15, 14, 13, 12, 10, 8 };
	
	private int[] stat_array;
	
	private boolean testing;
	
	private boolean rolled;
	
	private int proficiencyBonus;
	
	private Skill[] savingThrows;
	
	private Skill[] skills;
	
	public Character(String name) {
		this(name, false);
	}
	
	public Character(String name, boolean testing) {
		this.name = name;
		this.testing = testing;
		mods = new int[6];
		stat_array = new int[6];
		level = 0;
		rolled = false;
		savingThrows = new Skill[6];
		skills = new Skill[18];
		proficiencyBonus = 2;
	}
	
	/**
	 * The setScore method is given a score type and a value for it to be set to. If the value is less than 1 or greater 
	 * than 30, the maximum possible ability score, then an IllegalArgumentException is thrown. If the score type 
	 * is invalid then an IllegalArgumentException is thrown.
	 * @param stat is the score type being set.
	 * @param val is the value the score is being set to.
	 * @throws IllegalArgumentException if the value is outside of its expected bounds or if the score type is invalid.
	 */
	public void setScore(Score stat, int val) {
		if (val < 1 || val > MAX_ABILITY_SCORE) {
			throw new IllegalArgumentException("That ability score is invalid!");
		}
		if (stat == Score.Str) {
			strength = val;
			if (val < 10) {
				val--;
			}
			mods[0] = (val - 10) / 2;
			skills[0] = new Skill(mods[0], ProfLevel.Normal, getProficiencyBonus(), "Athletics");
			savingThrows[0] = new Skill(mods[0], ProfLevel.Normal, getProficiencyBonus(), "Strength Saving Throw");
		} else if (stat == Score.Dex) {
			dexterity = val;
			if (val < 10) {
				val--;
			}
			mods[1] = (val - 10) / 2;
			skills[1] = new Skill(mods[1], ProfLevel.Normal, getProficiencyBonus(), "Acrobatics");
			skills[2] = new Skill(mods[1], ProfLevel.Normal, getProficiencyBonus(), "Sleight of Hand");
			skills[3] = new Skill(mods[1], ProfLevel.Normal, getProficiencyBonus(), "Stealth");
			savingThrows[1] = new Skill(mods[1], ProfLevel.Normal, getProficiencyBonus(), "Dexterity Saving Throw");
		} else if (stat == Score.Con) {
			constitution = val;
			if (val < 10) {
				val--;
			}
			mods[2] = (val - 10) / 2;
			savingThrows[2] = new Skill(mods[2], ProfLevel.Normal, getProficiencyBonus(), "Constitution Saving Throw");
		} else if (stat == Score.Int) {
			intelligence = val;
			if (val < 10) {
				val--;
			}
			mods[3] = (val - 10) / 2;
			skills[4] = new Skill(mods[3], ProfLevel.Normal, getProficiencyBonus(), "Arcana");
			skills[5] = new Skill(mods[3], ProfLevel.Normal, getProficiencyBonus(), "History");
			skills[6] = new Skill(mods[3], ProfLevel.Normal, getProficiencyBonus(), "Investigation");
			skills[7] = new Skill(mods[3], ProfLevel.Normal, getProficiencyBonus(), "Nature");
			skills[8] = new Skill(mods[3], ProfLevel.Normal, getProficiencyBonus(), "Religion");
			savingThrows[3] = new Skill(mods[3], ProfLevel.Normal, getProficiencyBonus(), "Intelligence Saving Throw");
		} else if (stat == Score.Wis) {
			wisdom = val;
			if (val < 10) {
				val--;
			}
			mods[4] = (val - 10) / 2;
			skills[9] = new Skill(mods[4], ProfLevel.Normal, getProficiencyBonus(), "Animal Handling");
			skills[10] = new Skill(mods[4], ProfLevel.Normal, getProficiencyBonus(), "Insight");
			skills[11] = new Skill(mods[4], ProfLevel.Normal, getProficiencyBonus(), "Medicine");
			skills[12] = new Skill(mods[4], ProfLevel.Normal, getProficiencyBonus(), "Perception");
			skills[13] = new Skill(mods[4], ProfLevel.Normal, getProficiencyBonus(), "Survival");
			savingThrows[4] = new Skill(mods[4], ProfLevel.Normal, getProficiencyBonus(), "Wisdom Saving Throw");
		} else if (stat == Score.Cha) {
			charisma = val;
			if (val < 10) {
				val--;
			}
			mods[5] = (val - 10) / 2;
			skills[14] = new Skill(mods[5], ProfLevel.Normal, getProficiencyBonus(), "Deception");
			skills[15] = new Skill(mods[5], ProfLevel.Normal, getProficiencyBonus(), "Intimidation");
			skills[16] = new Skill(mods[5], ProfLevel.Normal, getProficiencyBonus(), "Performance");
			skills[17] = new Skill(mods[5], ProfLevel.Normal, getProficiencyBonus(), "Persuasion");
			savingThrows[5] = new Skill(mods[5], ProfLevel.Normal, getProficiencyBonus(), "Charisma Saving Throw");
		} else {
			throw new IllegalArgumentException("That ability score doesn't exist!");
		}
	}
	
	/**
	 * The getScore method returns the ability score associated with the score type given to it. If the score type 
	 * is invalid then an IllegalArgumentException is thrown.
	 * @param stat is the ability score type being returned,
	 * @return the value of the indicated ability score.
	 * @throws IllegalArgumentException if the score type is invalid.
	 */
	public int getScore(Score stat) {
		if (stat == Score.Str) {
			return strength;
		} else if (stat == Score.Dex) {
			return dexterity;
		} else if (stat == Score.Con) {
			return constitution;
		} else if (stat == Score.Int) {
			return intelligence;
		} else if (stat == Score.Wis) {
			return wisdom;
		} else if (stat == Score.Cha) {
			return charisma;
		} else {
			throw new IllegalArgumentException("That ability score doesn't exist!");
		}
	}
	
	/**
	 * The levelUp method increases the charcater's level and throws an IllegalArgumentException if the 
	 * character's level is already 20.
	 * @throws IllegalArgumentException if the character's level is already 20.
	 */
	public void levelUp() {
		if (level == 20) {
			throw new IllegalArgumentException("You're at max level!");
		}
		level++;
		if (level < 5) {
			proficiencyBonus = 2;
		} else if (level < 9) {
			proficiencyBonus = 3;
		} else if (level < 13) {
			proficiencyBonus = 4;
		} else if (level < 17) {
			proficiencyBonus = 5;
		} else {
			proficiencyBonus = 6;
		}
	}
	
	/**
	 * The getMods method returns the list of mods for the character's ability scores.
	 * @return the array of mods for the character.
	 */
	public int[] getMods() {
		return mods;
	}
	
	/**
	 * The getMod method returns the modifier of a particular ability score.
	 * @param stat is the given ability score.
	 * @return the modifier for the given ability score.
	 * @throws IllegalArgumentException if the ability score does not exist.
	 */
	public int getMod(Score stat) {
		if (stat == Score.Str) {
			return mods[0];
		} else if (stat == Score.Dex) {
			return mods[1];
		} else if (stat == Score.Con) {
			return mods[2];
		} else if (stat == Score.Int) {
			return mods[3];
		} else if (stat == Score.Wis) {
			return mods[4];
		} else if (stat == Score.Cha) {
			return mods[5];
		} else {
			throw new IllegalArgumentException("That ability score doesn't exist!");
		}
	}
	
	/**
	 * The getSavingThrow method returns the bonus of the savingThrow to the caller.
	 * @param stat is the stat whose saving throw bonus is being retrieved.
	 * @return the saving throw bonus for the given stat.
	 * @throws IllegalArgumentException if the given stat does not exist.
	 */
	public int getSavingThrow(Score stat) {
		if (stat == Score.Str) {
			return savingThrows[0].getCheckBonus();
		} else if (stat == Score.Dex) {
			return savingThrows[1].getCheckBonus();
		} else if (stat == Score.Con) {
			return savingThrows[2].getCheckBonus();
		} else if (stat == Score.Int) {
			return savingThrows[3].getCheckBonus();
		} else if (stat == Score.Wis) {
			return savingThrows[4].getCheckBonus();
		} else if (stat == Score.Cha) {
			return savingThrows[5].getCheckBonus();
		} else {
			throw new IllegalArgumentException("That ability score doesn't exist!");
		}
	}
	
	/**
	 * The getLevel method returns the level of the character.
	 * @return the level of the character.
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * The getProficiencyBonus method returns the character's proficiency bonus.
	 * @return the character's proficiency bonus based on the level of the character.
	 */
	public int getProficiencyBonus() {
		return proficiencyBonus;
	}
	
	/**
	 * The getClasses method returns the list of classes that the character has.
	 * @return the list of classes that the character has.
	 */
	public CharacterClass[] getClasses() {
		return null;
	}
	
	/**
	 * The getBackground method returns the background of the character.
	 * @return the background of the character.
	 */
	public Background getBackground() {
		return null;
	}
	
	/**
	 * The abilityScoreIncrease method takes two scores that the caller wants to be improved by one each. If both 
	 * are the same then the score will be given a double improvement. The method will throw an IllegalArgumentException
	 * if the scores given are invalid.
	 * @param s1 is the first score being improved.
	 * @param s2 is the second score being improved.
	 * @throws IllegalArgumentException if the ability score has reached its maximum value or if the score type is 
	 * invalid.
	 */
	public void abilityScoreIncrease(Score s1, Score s2) {
		
		try {
			setScore(s1, getScore(s1) + 1);
			if (s2 != null) {
				setScore(s2, getScore(s2) + 1);
			}
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	/**
	 * The roll3d6 method simulates rolling 3 six sided dice and adds up the result to give the score rolled.
	 * These scores are stored in the character stat array.
	 */
	public void roll3d6() {
		Random rand = new Random();
		if (testing) {
			rand.setSeed(17265402);
		}
		for (int i = 0; i < 6; i++) {
			int p1 = rand.nextInt(6) + 1;
			int p2 = rand.nextInt(6) + 1;
			int p3 = rand.nextInt(6) + 1;
			int score = p1 + p2 + p3;
			stat_array[i] = score;
		}
		rolled = true;
	}
	
	/**
	 * The roll4d6DropLowest method simulates rolling 4 six sided dice and dropping the lowest die result in 
	 * score calculation. 
	 */
	public void roll4d6DropLowest() {
		Random rand = new Random();
		if (testing) {
			rand.setSeed(17265402);
		}
		for (int i = 0; i < 6; i++) {
			int p1 = rand.nextInt(6) + 1;
			int p2 = rand.nextInt(6) + 1;
			int p3 = rand.nextInt(6) + 1;
			int p4 = rand.nextInt(6) + 1;
			int score = p1 + p2 + p3 + p4 - Math.min( Math.min(p1, p2), Math.min(p3, p4) );
			stat_array[i] = score;
		}
		System.out.println();
		int totalMod = 0;
		for (int j = 0; j < 6; j++) {
			if(stat_array[j] < 10) {
				totalMod += (stat_array[j] - 11) / 2;
			} else {
				totalMod += (stat_array[j] - 10) / 2;
			}
		}
		if (totalMod < 3) {
			roll4d6DropLowest();
		}
		rolled = true;
	}
	
	/**
	 * The getArray method returns the array rolled or the standard array if nothing has been rolled.
	 * @return the stat array for the character.
	 */
	public int[] getArray() {
		if (!rolled) {
			for (int i = 0; i < 6; i++) {
				stat_array[i] = STANDARD_ARRAY[i];
			}
		}
		return stat_array;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("" + name + "\n");
		sb.append("" + level + "\n");
		sb.append("");
		return sb.toString();
	}

}
