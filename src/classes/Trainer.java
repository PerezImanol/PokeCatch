package classes;

import java.sql.Date;
import java.util.LinkedHashSet;
/**
 * This class defines the object Trainer which has a set of Pokemon and Combats of the Pokemon that are from this trainer
 * and the commbats he/she took part in
 * @author DaniD
 * @version 3
 *
 */
public class Trainer {

    private int trainerID;
    private String name;
    private Date birthdate;
    private String gender;
    private String originCity;
    private int badges;
    private int pokeballs;
	private LinkedHashSet <Pokemon> team;
    private LinkedHashSet <Combat> combatHistory;

    public int getTrainerID() {
        return trainerID;
    }
    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date date) {
        this.birthdate = date;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getOriginCity() {
        return originCity;
    }
    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }
    public int getBadges() {
        return badges;
    }
    public void setBadges(int badges) {
        this.badges = badges;
    }
    public LinkedHashSet<Pokemon> getTeam() {
        return team;
    }
    public void setTeam(LinkedHashSet<Pokemon> team) {
        this.team = team;
    }
    public LinkedHashSet<Combat> getCombatHistory() {
        return combatHistory;
    }
    public void setCombatHistory(LinkedHashSet<Combat> combatHistory) {
        this.combatHistory = combatHistory;
    }
    public int getPokeballs() {
        return pokeballs;
    }
    public void setPokeballs(int pokeballs) {
        this.pokeballs = pokeballs;
    }

	public String getTrainerInfo() {
		String trainerInfo="------------------------Trainer Info-------------------------\n"
				+ "This trainers ID is " + trainerID + " and his/her name is " + name + " \n"
				+ " Was born on the  " + birthdate + " is a " + gender +" \n"
				+ " from " + originCity + " and has " + badges + " badges.";
			return trainerInfo;
	}
}
