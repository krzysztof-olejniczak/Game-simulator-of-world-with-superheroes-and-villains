package pl.population;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.Main.Main;
import pl.board.Board;
import pl.board.City;
import pl.board.District;
import pl.board.Place;
import pl.population.fieldGenerators.SuperheroFieldsGenerator;
import pl.population.fieldGenerators.VillainFieldsGenerator;
import pl.powerSource.Capability;
import pl.resources.Files;

/**
 * Extends character, makes it runnable
 *
 * @author Krzysztof Olejnicza
 */
public abstract class Role extends Character implements Runnable {

    private static final double MINIMUM_HIT_HEALTH_DECREASE = 1.0;
    private static final int TIME_FIGHT_INTERVAL = 3000;

    public Role(String name, BufferedImage image, Place location, City travelTo,
            State state, Board board, Population population) {
        super(name, image, location, travelTo, state, board, population);
    }

    /**
     * Method for role's thread
     */
    @Override
    public void run() {
        while (!this.isKilled()) {
            if (Main.isGameRunning()) {

                switch (this.getState()) {
                    case IN_CITY:
                        inCity();
                        break;
                    case TRAVELING:
                        whileTravelling();
                        if (this.getState() == State.TRAVELING) {
                            this.setLocation(travel());
                        }
                        break;
                    case FIGHT:
                        fight();
                        break;
                }

                if (this.getState() == State.IN_CITY) {
                    sleepInCity();
                }

                if (this.getState() == State.FIGHT) {
                    try {
                        Thread.sleep((long) (TIME_FIGHT_INTERVAL
                                / this.getPower(Capability.CapabilityType.SPEED)));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Character.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                try {
                    Thread.sleep(TIME_TO_SLEEP_ON_STOP);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Character.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }

        this.setImage(getDeadImage());

        try {
            Thread.sleep(TIME_TO_SLEEP_AFTER_BEING_KILLED);
        } catch (InterruptedException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
        }

        population.getSuperheroes().remove(this);
        population.getVillains().remove(this);
        this.getLocation().setEmpty(this);
    }

    /**
     * Method for spending time in inCity, different implementations for
     * sub-classes
     */
    protected abstract void inCity();

    /**
     * Method for whileTravelling, different implementations for sub-classes
     */
    protected abstract void whileTravelling();

    /**
     * Performs fight operations
     */
    protected void fight() {
        this.hit(this.getFightWith());
        
        if(this.getFightWith().getHealth() <= 0
                || this.getFightWith().getFightWith() != this) {
            if (this instanceof Villain) {
                this.setImage(VillainFieldsGenerator.generateImage());
            } else if (this instanceof Superhero) {
                this.setImage(SuperheroFieldsGenerator.generateImage());
            }

            if (this.getLocation() instanceof District) {
                this.setState(State.IN_CITY);
            } else {
                this.setState(State.TRAVELING);
            }
        }
        if (this.getFightWith().getHealth() <= 0) {
            this.getFightWith().killCharacter();
            this.setFightWith(null);
            if (this instanceof Superhero) {
                Main.increaseNumberOfVillainsKilled();
            }
        }
    }

    /**
     * Method for sleeping in inCity, different implementations for sub-classes
     */
    protected abstract void sleepInCity();

    /**
     * Method for getting image for dead character, different implementations
     * for sub-classes
     *
     * @return image of dead character
     */
    protected abstract BufferedImage getDeadImage();

    /**
     * Character tries to attack other character on inCity
     *
     * @param classOfcharacter class of character he is trying to attack
     * @return true if he attacked, false otherwise
     */
    protected boolean tryToAttackOnCity(Class<? extends Character> classOfcharacter) {
        Character character = (Character) ((District) this.getLocation()).getCity()
                .findNotFightingHumanInCity(classOfcharacter);
        if (character != null && character.getFightWith() == null) {
            this.attack(character);
            return true;
        }
        return false;
    }

    /**
     * Character tries to attack character from list
     *
     * @param humans list of characters to choose
     * @param classOfCharacter class of character he is trying to attack
     * @return true if he attacked, false otherwise
     */
    protected boolean tryToAttactFromList(List<Human> humans, Class<? extends Character> classOfCharacter) {
        for (Human human : humans) {
            if (classOfCharacter.isInstance(human) && ((Character) human).getFightWith() == null) {
                this.attack((Character) human);
                return true;
            }
        }
        return false;
    }

    /**
     * Character attacks other character
     *
     * @param character character to attack
     */
    public void attack(Character character) {
        this.setFightWith(character);
        this.setState(State.FIGHT);
        character.setFightWith(this);
        character.setState(State.FIGHT);

        if (this instanceof Superhero) {
            this.setImage(Files.getImage(Files.SUPERHERO_FIGHTING_IMAGE_FILE));
            character.setImage(Files.getImage(Files.VILLAIN_FIGHTING_IMAGE_FILE));
        } else if (this instanceof Villain) {
            character.setImage(Files.getImage(Files.SUPERHERO_FIGHTING_IMAGE_FILE));
            this.setImage(Files.getImage(Files.VILLAIN_FIGHTING_IMAGE_FILE));
        }
    }

    /**
     * Hit character
     *
     * @param character character to decrease health
     */
    public void hit(Character character) {
        final Capability.CapabilityType[] capabilitiesToRandom = new Capability.CapabilityType[]{
            Capability.CapabilityType.ENERGY, Capability.CapabilityType.FORCE,
            Capability.CapabilityType.INTELLIGENCE
        };
        int randomVal = (int) (Math.random() * capabilitiesToRandom.length % capabilitiesToRandom.length);
        double power = this.getPower(capabilitiesToRandom[randomVal]);

        if (this instanceof Superhero) {
            power += (board.sumOfPowerSources(capabilitiesToRandom[randomVal])
                    / (double) (population.getSuperheroes().size() + 1));
        }

        if (power * getPower(Capability.CapabilityType.FIGHTING_SKILLS)
                - character.getPower(Capability.CapabilityType.STRENGTH)
                > MINIMUM_HIT_HEALTH_DECREASE) {
            character.healthDecrease(
                    power * getPower(Capability.CapabilityType.FIGHTING_SKILLS)
                    - character.getPower(Capability.CapabilityType.STRENGTH)
            );
        } else {
            character.healthDecrease(MINIMUM_HIT_HEALTH_DECREASE);
        }
    }
}
