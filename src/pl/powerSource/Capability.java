package pl.powerSource;

/**
 * Describes types of capabilities
 *
 * @author Krzysztof Olejniczak
 */
public abstract class Capability {

    /**
     * Types of capabilites for Power Sources and for Characters
     */
    public enum CapabilityType {
        INTELLIGENCE, FORCE, SPEED, STRENGTH, ENERGY, FIGHTING_SKILLS

    };

    /**
     * Translates type of capability to string to show name for user
     *
     * @param capability type of capability
     * @return name for user
     */
    public static String capabilityToString(CapabilityType capability) {
        switch (capability) {
            case INTELLIGENCE:
                return "Intelligence";
            case FORCE:
                return "Force";
            case SPEED:
                return "Speed";
            case STRENGTH:
                return "Strength";
            case ENERGY:
                return "Energy";
            case FIGHTING_SKILLS:
                return "Fighting skills";
            default:
                return "No capability";
        }
    }
}
