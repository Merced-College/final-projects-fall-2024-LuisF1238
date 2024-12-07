package APP;

//Exercise Log for given day
public class Exercise extends DailyLogEntry {
    private final int caloriesBurned;

    // Exercise (Name, Calories) Constructor
    public Exercise(String name, int caloriesBurned) {
        super(name);
        this.caloriesBurned = caloriesBurned;
    }

    // Subtracts Calories
    @Override
    public int getCaloriesImpact() {
        return -caloriesBurned;
    }

    //Generates String (Ex: (Apple 25 Kcal)
    @Override
    public String toString() {
        return getName() + " (-" + caloriesBurned + " kcal)";
    }
}
