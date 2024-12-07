package APP;

// Current Day Summary
public abstract class DailyLogEntry {
    private final String name;

    //Name Constructor
    public DailyLogEntry(String name) {
        this.name = name;
    }

    //Name Getter
    public String getName() {
        return name;
    }

    // Abstract method of calorie impact
    public abstract int getCaloriesImpact();
}