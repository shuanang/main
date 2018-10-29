package seedu.divelog.ui;

//import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

import seedu.divelog.commons.core.LogsCenter;
import seedu.divelog.commons.events.ui.DivePanelSelectionChangedEvent;
import seedu.divelog.model.dive.DiveSession;

/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {

    public static final String FORMAT_DIVE_LOCATION = "Dive @ %s";

    public static final String FORMAT_DIVE_DEPTH = "You dove to %.1fm";

    public static final String FORMAT_START_TIME = "Started at: %s";

    public static final String FORMAT_END_TIME = "Ended at: %s";

    public static final String FORMAT_SAFETY_STOP = "Safety stop at: %s";

    public static final String FORMAT_TIME_NOW = "Date: %s";

    private static final String FXML = "BrowserPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private Label diveLocation;
    @FXML
    private Label diveDepth;
    @FXML
    private Label pgStart;
    @FXML
    private Label pgEnd;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label safetyStop;
    @FXML
    private Label dateTime;

    public BrowserPanel() {
        super(FXML);
        loadMyTimeNow();
        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);
        registerAsAnEventHandler(this);
    }

    /**
     * Renders the dive specific information
     * @param dive
     */
    private void loadDivePage(DiveSession dive) {
        diveLocation.setText(String.format(FORMAT_DIVE_LOCATION, dive.getLocation().getLocationName()));
        diveDepth.setText(String.format(FORMAT_DIVE_DEPTH, dive.getDepthProfile().getDepth()));
        pgStart.setText(dive.getPressureGroupAtBeginning().getPressureGroup());
        pgEnd.setText(dive.getPressureGroupAtEnd().getPressureGroup());
        //pgEnd.setTextFill(Color.web("#0076a3"));
        //pgEnd.setForeground(Color.web("#0076a3"));
        //pgEnd.setTextFill(Color.GREEN);
        //pgEnd.setStyle("-fx-background-color: #0076a3;");
        startTime.setText(String.format(FORMAT_START_TIME, dive.getStart().getTimeString()));
        endTime.setText(String.format(FORMAT_END_TIME, dive.getEnd().getTimeString()));
        safetyStop.setText(String.format(FORMAT_SAFETY_STOP, dive.getSafetyStop().getTimeString()));
        dateTime.setText(String.format(FORMAT_TIME_NOW, dive.getDateStart().getOurDateString()));
        checkPressureGrp(dive.getPressureGroupAtBeginning().getPressureGroup());
    }

    public void freeResources(){

    }
    private void loadMyTimeNow() {
        dateTime.setText(String.format(FORMAT_TIME_NOW, dateTimeSend()));
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(DivePanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadDivePage(event.getNewSelection());
    }

    /**
     * Retrieves the current date time as a string
     * @return retrieves date as string
     */
    private String dateTimeSend() {
        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY hh:mmaa");
        String dateTimeNow = dateFormat.format(d);
        return dateTimeNow;
    }
    /**
     * Receives the pressure group that needs to be parsed
     * sets the attribute on the screen depending on the Pressure group.
     */
    private void checkPressureGrp(String pgGrp) {
        switch(pgGrp) {
        case "A":
            //to return it as #008000 or GREEN or rgb(0,128,0)
            // break;
        case "B":
            break;
        case "C":
            break;
        case "D":
            break;
        case "E":
            break;
        case "F":
            break;
        case "G":
            break;
        case "H":
            break;
        case "I":
            break;
        case "J":
            break;
        case "L":
            break;
        case "M":
            break;
        case "N":
            break;
        case "O":
            break;
        case "P":
            //return #ff6600 or ORANGE or rgb(255, 102, 0)
            break;
        case "Q":
            break;
        case "R":
            break;
        case "S":
            break;
        case "T":
            break;
        case "U":
            break;
        case "V":
            break;
        case "W":
            break;
        case "X":
            break;
        case "Y":
            break;
        case "Z":
            //to return as RED or #ff0000 or (255,0,0)
            break;
        default:
            //return black
            break;
        }
    }
}
