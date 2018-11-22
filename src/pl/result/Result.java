package pl.result;

/**
 * *
 * Single result of game
 *
 * @author Krzysztof Olejniczak
 */
public class Result {

    private String playerName;
    private int killedVillains;
    private int time;

    /**
     * Result constructor
     *
     * @param playerName name of player
     * @param killedVillains number of killed villains
     * @param time time of game
     */
    public Result(String playerName, int killedVillains, int time) {
        this.playerName = playerName;
        this.killedVillains = killedVillains;
        this.time = time;
    }

    /**
     * Empty constructor needed for XML serialization
     */
    public Result() {
    }

    /**
     * Checks if result is better than another result. Result is better if has
     * more villains killed or time of game is longer
     *
     * @param anotherResult result to compare
     * @return true if this result is better than another result, false overwise
     */
    public boolean isBetterThan(Result anotherResult) {
        return (this.getKilledVillains() > anotherResult.getKilledVillains()
                || (this.getKilledVillains() == anotherResult.getKilledVillains()
                && this.getTime() > anotherResult.getTime()));
    }

    /**
     * Gets name of player
     *
     * @return name of player
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets name of player
     *
     * @param playerName name of player
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Gets number of killed villains
     *
     * @return number of killed villains
     */
    public int getKilledVillains() {
        return killedVillains;
    }

    /**
     * Sets number of killed villains
     *
     * @param killedVillains number of killed villains
     */
    public void setKilledVillains(int killedVillains) {
        this.killedVillains = killedVillains;
    }

    /**
     * Gets time of game in seconds
     *
     * @return time of game in seconds
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets time of game in seconds
     *
     * @param time time of game in seconds
     */
    public void setTime(int time) {
        this.time = time;
    }
}
