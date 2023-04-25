package classes;

public class Combat {

    private int trainer1;
    private int trainer2;
    private int winnerTrainerID;
    
    public int getTrainer1() {
        return trainer1;
    }
    public void setTrainer1(int trainer1) {
        this.trainer1 = trainer1;
    }
    public int getTrainer2() {
        return trainer2;
    }
    public void setTrainer2(int trainer2) {
        this.trainer2 = trainer2;
    }
	  public int getWinnerTrainerID() {
		return winnerTrainerID;
	  }
    public void setWinnerTrainerID(int winnerTrainerID) {
		this.winnerTrainerID = winnerTrainerID;
    }
    
	@Override
	public String toString() {
		return "Combat [trainer1=" + trainer1 + ", trainer2=" + trainer2 + ", winnerTrainerID=" + winnerTrainerID + "]";
	}
}
