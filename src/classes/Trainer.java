package classes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedHashSet;

public class Trainer {

    private int trainerID;
    private String name;
    private Date age;
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
    public Date getAge() {
        return age;
    }
    public void setAge(Date age) {
        this.age = age;
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

	@Override
	public String toString() {
		return "Trainer [trainerID=" + trainerID + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", originCity=" + originCity + ", badges=" + badges + ", team=" + team + ", combatHistory="
				+ combatHistory + "]";
	}
    
    

}
