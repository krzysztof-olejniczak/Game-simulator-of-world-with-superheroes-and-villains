package pl.population;

import java.util.LinkedList;
import java.util.List;
import pl.board.Board;
import pl.board.City;
import pl.population.fieldGenerators.CivilianFieldsGenerator;

/**
 * Stores all information about all humans existing in game
 *
 * @author Krzysztof Olejniczak
 */
public class Population {

    private final List<Civilian> civilians = new LinkedList<>();
    private final List<Character> superheroes = new LinkedList<>();
    private final List<Character> villains = new LinkedList<>();

    /**
     * Adds given number of random civilians to population. Initial location of
     * every civilian is random city. If there is not enough place in cities
     * then function creates less civilians
     *
     * @param number number of civilians to create
     * @param board board on which civilians will appear
     */
    public void addRandomCivilians(int number, Board board) {
        List<City> citiesWithEmptyNeigbourhood = board.findCitiesWithEmptyNeigbourhood();
        for (; number > 0; number--) {
            City city = citiesWithEmptyNeigbourhood.get(
                    (int) (Math.random() * citiesWithEmptyNeigbourhood.size())
                    % citiesWithEmptyNeigbourhood.size()
            );

            Civilian civilian = new Civilian(
                    CivilianFieldsGenerator.generateName(),
                    CivilianFieldsGenerator.generateSurname(),
                    CivilianFieldsGenerator.generateImage(),
                    city.findEmptyNeighbourhood(),
                    board, this
            );
            civilians.add(civilian);

            Thread thread = new Thread(civilian);
            thread.start();

            if (city.findEmptyNeighbourhood() == null) {
                citiesWithEmptyNeigbourhood.remove(city);
            }
            if (citiesWithEmptyNeigbourhood.isEmpty()) {
                break;
            }
        }
    }

    /**
     * Get list of all civilians, superheroes and villains
     *
     * @return list of whole population
     */
    public List<Human> getWholePopulation() {
        List<Human> population = new LinkedList<>();
        population.addAll(civilians);
        population.addAll(superheroes);
        population.addAll(villains);
        return population;
    }

    /**
     * Gets list of all civilians
     *
     * @return list of all civilians
     */
    public List<Civilian> getCivilians() {
        return civilians;
    }

    /**
     * Gets list of all superheroes
     *
     * @return list of all superheroes
     */
    public List<Character> getSuperheroes() {
        return superheroes;
    }

    /**
     * Gets list of all villains
     *
     * @return list of all villains
     */
    public List<Character> getVillains() {
        return villains;
    }

}
