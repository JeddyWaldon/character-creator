package model.races;

abstract class Race {
	
	/**
	 * The getASB method returns an array of integer bonuses to ability scores.
	 * @return an array of integer bonuses to ability scores.
	 */
	public abstract int[] getASB();
	
	/**
	 * The getBaseSpeed method returns the base speed of the race.
	 * @return the base speed of the race.
	 */
	public abstract int getBaseSpeed();
	
	/**
	 * The getRacialLanguages method returns the racial languages as an array of strings.
	 * @return racial languages as an array of strings.
	 */
	public abstract String[] getRacialLanguages();
	
	/**
	 * The toString method returns a string representing the race.
	 * @return the string representing the race.
	 */
	@Override
	public abstract String toString();

}
