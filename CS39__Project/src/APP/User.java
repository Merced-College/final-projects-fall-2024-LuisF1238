package APP;

public class User {
    private final String name; // User's name
    private final int age; // User's age
    private final double weight; // User's weight
    private final double dailyCalorieGoal; // User's Daily Calorie goal

    //User Profile Constructor
    public User(String name, int age, double weight, double dailyCalorieGoal) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.dailyCalorieGoal = dailyCalorieGoal;
    }

    //User Name Getter
    public String getName() {
        return name;
    }

    // User CalorieGoal Getter
    public double getDailyCalorieGoal() {
        return dailyCalorieGoal;
    }
}
