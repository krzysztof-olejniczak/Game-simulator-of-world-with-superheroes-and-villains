package pl.population;

import java.awt.image.BufferedImage;
import pl.board.City;
import pl.board.Place;
import pl.population.Human.State;
import java.util.HashMap;
import java.util.Map;
import pl.board.Board;
import pl.powerSource.Capability.CapabilityType;

/**
 * Class extends human into character
 *
 * @author Krzysztof Olejniczak
 */
public abstract class Character extends Human {

    private static final double POWERS_MULTIPLIER = 3.0;
    private static final double POWERS_MIN_VALUE = 1.0;
    private static final double STRENGTH_MULTIPLIER = 9.0;
    private static final double INITIAL_HEALTH_VALUE = 100.0;

    private Character fightWith;
    private double health;
    private final Map<CapabilityType, Double> powers = new HashMap<>();

    /**
     * Sets the fields of character
     *
     * @param name character's name
     * @param image character's image displayed on screen
     * @param state character's initial state
     * @param location character's initial location
     * @param travelTo character's initial location
     * @param board reference to game's board
     * @param population reference to game's whole population object
     */
    public Character(String name, BufferedImage image, Place location,
            City travelTo, State state, Board board, Population population) {
        super(name, image, state, location, travelTo, board, population);
        this.fightWith = null;
        this.health = INITIAL_HEALTH_VALUE;
        this.setPowers(
                Math.random() * POWERS_MULTIPLIER + POWERS_MIN_VALUE,
                Math.random() * POWERS_MULTIPLIER + POWERS_MIN_VALUE,
                Math.random() * STRENGTH_MULTIPLIER + POWERS_MIN_VALUE,
                Math.random() * POWERS_MULTIPLIER + POWERS_MIN_VALUE,
                Math.random() * POWERS_MULTIPLIER + POWERS_MIN_VALUE,
                Math.random() * POWERS_MULTIPLIER + POWERS_MIN_VALUE);
    }

    /**
     * kill this character
     */
    public void killCharacter() {
        this.health = 0.0;
        this.kill();
    }

    /**
     * Gets character with whom there is a fight
     *
     * @return character with whom there is a fight
     */
    public Character getFightWith() {
        return this.fightWith;
    }

    /**
     * Sets character with whom there is a fight
     *
     * @param character character with whom there is a new fight
     */
    public void setFightWith(Character character) {
        this.fightWith = character;
    }

    /**
     * Gets character's health level
     *
     * @return character's health level
     */
    public Double getHealth() {
        return this.health;
    }

    /**
     * Decreases character's health level by value
     *
     * @param value value to decrease character's health
     */
    public void healthDecrease(double value) {
        this.health = this.health - value;
    }

    /**
     * Gets power value given by a parameter
     *
     * @param power power name to return
     * @return quantity of power
     */
    public Double getPower(CapabilityType power) {
        return this.powers.get(power);
    }

    /**
     * Increases value of given power by given value
     *
     * @param power power to increase
     * @param value value of increase
     */
    public void powerIncrease(CapabilityType power, double value) {
        this.powers.put(power, this.powers.get(power) + value);
    }

    /**
     * Sets powers for characters
     *
     * @param speed value of speed
     * @param fightingSkills value of fighting skills
     * @param strength value of strength
     * @param intelligence value of intelligence
     * @param energy value of energy
     * @param force value of force
     */
    private void setPowers(double speed, double fightingSkills,
            double strength, double intelligence, double energy, double force) {
        this.powers.put(CapabilityType.SPEED, speed);
        this.powers.put(CapabilityType.FIGHTING_SKILLS, fightingSkills);
        this.powers.put(CapabilityType.STRENGTH, strength);
        this.powers.put(CapabilityType.INTELLIGENCE, intelligence);
        this.powers.put(CapabilityType.ENERGY, energy);
        this.powers.put(CapabilityType.FORCE, force);
    }

}
