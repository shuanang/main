package seedu.divelog.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.divelog.commons.core.LogsCenter;
import seedu.divelog.commons.events.ui.DivePanelSelectionChangedEvent;
import seedu.divelog.commons.events.ui.JumpToListRequestEvent;
import seedu.divelog.model.dive.DiveSession;

/**
 * Panel containing the list of persons.
 */
public class DiveListPanel extends UiPart<Region> {
    private static final String FXML = "DiveListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DiveListPanel.class);

    @FXML
    private ListView<DiveSession> diveListView;

    public DiveListPanel(ObservableList<DiveSession> personList) {
        super(FXML);
        setConnections(personList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<DiveSession> personList) {
        diveListView.setItems(personList);
        diveListView.setCellFactory(listView -> new DiveListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        diveListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in person list panel changed to : '" + newValue + "'");
                        raise(new DivePanelSelectionChangedEvent(newValue));
                    }
                });
    }

    /**
     * Scrolls to the {@code DiveSessionCard} at the {@code index} and selects it.
     */
    private void scrollTo(int index) {
        Platform.runLater(() -> {
            diveListView.scrollTo(index);
            diveListView.getSelectionModel().clearAndSelect(index);
        });
    }

    @Subscribe
    private void handleJumpToListRequestEvent(JumpToListRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        scrollTo(event.targetIndex);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code DiveSessionCard}.
     */
    class DiveListViewCell extends ListCell<DiveSession> {
        @Override
        protected void updateItem(DiveSession dive, boolean empty) {
            super.updateItem(dive, empty);

            if (empty || dive == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DiveSessionCard(dive, getIndex() + 1).getRoot());
            }
        }
    }

}
