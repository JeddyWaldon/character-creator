package model;

public class Skill {
	private int mod;
	private ProfLevel prof;
	private int bonus;
	private String name;
	
	public Skill(int mod, ProfLevel prof, int bonus, String name) {
		setMod(mod);
		setProfLevel(prof);
		setBonus(bonus);
		this.name = name;
	}
	
	public void setMod(int mod) {
		if (mod > -6 && mod < 11) {
			this.mod = mod;
		} else {
			throw new IllegalArgumentException("Illegal modifier.");
		}
	}
	
	public void setProfLevel(ProfLevel prof) {
		if (prof != ProfLevel.Normal && prof != ProfLevel.Proficient && prof != ProfLevel.Expert) {
			throw new IllegalArgumentException("This proficiency level does not exist.");
		}
		this.prof = prof;
	}
	
	public void setBonus(int bonus) {
		if (bonus > 6 || bonus < 2) {
			throw new IllegalArgumentException("Illegal proficiency bonus.");
		} 
		this.bonus = bonus;
	}
	
	public int getCheckBonus() {
		int check;
		if (prof == ProfLevel.Normal) {
			check = mod;
		} else if (prof == ProfLevel.Proficient) {
			check = mod + bonus;
		} else {
			check = mod + 2 * bonus;
		}
		return check;
	}
	
	public ProfLevel getProficiencyLevel() {
		return prof;
	}
	
	@Override
	public String toString() {
		int check = getCheckBonus();
		String result = "";
		if (prof == ProfLevel.Proficient) {
			result += "* ";
		} else if (prof == ProfLevel.Expert) {
			result += "! ";
		}
		if (check >= 0) {
			result += "+" + check + " " + name + "\n";
		} else {
			result += "" + check + " " + name + "\n";
		}
		return result;
	}
}
