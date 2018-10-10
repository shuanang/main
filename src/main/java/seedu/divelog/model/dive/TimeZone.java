package seedu.divelog.model.dive;

/**
 * This is the timeZone class: assumes UTC +8 default
 * TODO: Implement parsing of time, locales etc.
 */
public class TimeZone {
    private int timeZoneDifference;

    public TimeZone(String timezone) {
        try {
            this.timeZoneDifference = Integer.parseInt(timezone);
        }catch(NumberFormatException ex){
            System.err.println("Illegal input");
        }
    }

    public int getTimeZoneString() {
        return timeZoneDifference;
    }

/*    public TimeZone (String timeZone) {
        int temporary;
        temporary = readinteger(timeZone);

        if (timeZone.startsWith("-")) {
            temporary *= (-1);
        }

        temporary -= 8;
        this.timeZoneDifference = temporary;
    }

    public int getTimeZoneString(){
        return timeZoneDifference;
    }
*/
    /**
     * reads a string ([+/-][int][int]) , decides if 2 digits or 3 digits(in hours), returns num hours
     */
    /*
    private int readinteger(String string){
        int hours = 8;

        if (string.length() == 2){
            hours = Character.getNumericValue(string.charAt(1));
        }
        else if (string.length() == 3){
            hours = (10 *Character.getNumericValue(string.charAt(1))
                    + Character.getNumericValue(string.charAt(2)));
        }
        return hours;
    }
    */

}
