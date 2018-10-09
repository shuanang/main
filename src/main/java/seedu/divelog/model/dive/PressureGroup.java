package seedu.divelog.model.dive;
/**
 * Represents a pressure group
 */
public class PressureGroup {

    private final String pressureGroup;

    public PressureGroup(String pressureGroup) {
        this.pressureGroup = pressureGroup;
    }

    public String getPressureGroup() {
        return pressureGroup;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PressureGroup)) {
            return false;
        }
        PressureGroup pg = (PressureGroup) obj;
        return pg.getPressureGroup().equals(pressureGroup);
    }
}
