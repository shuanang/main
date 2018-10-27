package seedu.divelog.commons.util;

public class UnitsUtil {
    private static float CONVERSION_RATIO_FEET_TO_METERS = 0.3048f;

    public static float feetToMeters(float feet) {
        return CONVERSION_RATIO_FEET_TO_METERS * feet;
    }

    public static float metersToFeet(float meters) {
        return meters / CONVERSION_RATIO_FEET_TO_METERS;
    }
}
