package APP;

// Actions performed (Ex: adding food/exercise).
public class Action {
    private final String type;
    private final DailyLogEntry item;

    // Action Constructor
    public Action(String type, DailyLogEntry item) {
        this.type = type;
        this.item = item;
    }

    // String Getter
    public String getType() {
        return type;
    }
    // Item Getter
    public DailyLogEntry getItem() {
        return item;
    }
}
