package guitests.guihandles;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMultiset;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.divelog.model.dive.DiveSession;
//@@author arjo129
/**
 * Provides a handle to a person card in the person list panel.
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
        return getName().equals(dive.getLocation().getLocationName());
    }
}
