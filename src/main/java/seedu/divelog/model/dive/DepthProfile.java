package seedu.divelog.model.dive;

/**
 * Keeps track of the Depth of a dive.
 * Currently as of v1.1, only simple dives to a fixed depth are supported
 * TODO: Implement complex dives
 */
public class DepthProfile {
    private static final String DEPTH_VALIDATION_REGEX = "[+-]?([0-9]*[.])?[0-9]+";

    private final float depth;

    public DepthProfile(float depth) {
        this.depth = depth;
    }

    /**
     * Verifies the validity of a depth argument
     * @param depth The depth. Must be a decimal number
     * @return if the depth string is valid, return true
     */
    public static boolean isValidDepth(String depth) {
        return depth.matches(DEPTH_VALIDATION_REGEX);
    }

    public float getDepth() {
        return depth;
    }
}
