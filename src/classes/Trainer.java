package classes;

import java.sql.Date;
<<<<<<< HEAD
=======
import java.time.LocalDate;
>>>>>>> 49836a401731a93e39efacaa0e3f12fdc019dccd
import java.util.LinkedHashSet;

public class Trainer {

    private int trainerID;
    private String name;
    private Date age;
    private String gender;
    private String originCity;
    private int badges;
    private int pokeball;
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
<<<<<<< HEAD
    public void setAge(Date date) {
        this.age = date;
=======
    public void setAge(Date age) {
        this.age = age;
>>>>>>> 49836a401731a93e39efacaa0e3f12fdc019dccd
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
    public int getPokeball() {
  		return pokeball;
  	}
  	public void setPokeball(int pokeball) {
  		this.pokeball = pokeball;
  	}
	@Override
	public String toString() {
		return "Trainer [trainerID=" + trainerID + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", originCity=" + originCity + ", badges=" + badges + ", team=" + team + ", combatHistory="
				+ combatHistory + "]";
	}
    
    

}
