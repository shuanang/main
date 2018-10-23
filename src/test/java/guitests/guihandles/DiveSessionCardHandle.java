package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.divelog.model.dive.DiveSession;
import seedu.divelog.ui.DiveSessionCard;
//@@author arjo129
/**
 * Provides a handle to a dive card in the dive list panel.
 */
public class DiveSessionCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String NAME_FIELD_ID = "#name";
    private static final String DEPTH_FIELD_ID = "#depth";

    private final Label idLabel;
    private final Label nameLabel;
    private final Label depth;

    public DiveSessionCardHandle(Node cardNode) {
        super(cardNode);
        idLabel = getChildNode(ID_FIELD_ID);
        nameLabel = getChildNode(NAME_FIELD_ID);
        depth = getChildNode(DEPTH_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getName() {
        return nameLabel.getText();
    }

    public String getDepth() { return depth.getText(); }

    /**
     * Returns true if this handle contains {@code dive}.
     * TODO: Check depth also!!
     */
    public boolean equals(DiveSession dive) {
        return getName().equals(DiveSessionCard.DIVE_PREFIX + dive.getLocation().getLocationName());
    }
}
