package classes;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class Trainer {

    private int trainerID;
    private String name;
    private LocalDate age;
    private String gender;
    private String originCity;
    private int badges;
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
    public LocalDate getAge() {
        return age;
    }
    public void setAge(LocalDate age) {
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

}
