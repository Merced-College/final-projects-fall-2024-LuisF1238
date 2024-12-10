package APP;

public class User {
    private String name; // User's name
    private int age; // User's age
    private double weight; // User's weight
    private double dailyCalorieGoal; // User's Daily Calorie goal

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

    // User Name Setter
    public void setName(String name) {
        this.name = name;
    }

    // User Age Setter
    public void setAge(int age) {
        this.age = age;

    }

    // User Weight Setter
    public void setWeight(double weight) {
        this.weight = weight;

    }

    // User CalorieGoal Setter
    public void setDailyCalorieGoal(double dailyCalorieGoal) {
        this.dailyCalorieGoal = dailyCalorieGoal;

    }
}
