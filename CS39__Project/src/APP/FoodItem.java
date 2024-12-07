package APP;

// Food item in  daily log
public class FoodItem extends DailyLogEntry {
    private final int calories;

    // Food item Constructor (name & calories)
    public FoodItem(String name, int calories) {
        super(name);
        this.calories = calories;
    }

    // Returns the calorie impact (Increases due to food consumption)
    @Override
    public int getCaloriesImpact() {
        return calories;
    }

    // String representation of the food item ("Apple" 50 "kcal")
    @Override
    public String toString() {
        return getName() + " (" + calories + " kcal)";
    }
}
