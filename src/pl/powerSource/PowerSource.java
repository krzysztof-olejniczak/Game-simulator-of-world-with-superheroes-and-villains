package pl.powerSource;

import pl.powerSource.Capability.CapabilityType;

/**
 * *
 * Describes single power source
 *
 * @author Krzysztof Olejniczak
 */
public class PowerSource {

    private String name;
    private CapabilityType capabilityType;
    private double potential;

    /**
     * Power source's constructor
     *
     * @param name power source's name
     * @param capabilityType capability that is increased
     * @param potential power source potential
     */
    public PowerSource(String name, CapabilityType capabilityType, double potential) {
        this.name = name;
        this.capabilityType = capabilityType;
        this.potential = potential;
    }

    /**
     * Empty constructor needed for XML serialization
     */
    public PowerSource() {
    }

    /**
     * Increase potential by value
     *
     * @param value quantity of potential to increase
     */
    public void potentialIncrease(double value) {
        potential += value;
    }

    /**
     * Decrease potential by value, if lower than zero then it is set as zero
     *
     * @param value quantity of potential to decrease
     */
    public void potentialDecrease(double value) {
        potential -= value;
        if (potential < 0.0) {
            potential = 0.0;
        }
    }

    /**
     * Gets name of power source
     *
     * @return name of power source
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of power source
     *
     * @param name name of power source
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets capability of power source
     *
     * @return capability of power source
     */
    public CapabilityType getCapabilityType() {
        return capabilityType;
    }

    /**
     * Sets capability of power source
     *
     * @param capability capability of power source
     */
    public void setCapabilityType(CapabilityType capability) {
        this.capabilityType = capability;
    }

    /**
     * Gets potential of power source
     *
     * @return potential of power source
     */
    public double getPotential() {
        return potential;
    }

    /**
     * Sets potential of power source
     *
     * @param potential potential of power source
     */
    public void setPotential(double potential) {
        this.potential = potential;
    }

}
