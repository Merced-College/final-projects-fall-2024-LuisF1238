
/***************************************************************************************
 *    Title: <FitnessApp source code>
 *    Author: <Luis Flores>
 *    Date: <Dec 5, 2024>
 *    Code version: 1.0
 ***************************************************************************************/


package APP;

import java.time.LocalDate;
import java.util.*;

public class FitnessApp {
    private static User user;
    // Daily Action logs Map by date
    private static final Map<LocalDate, DailyLog> ActionLogs = new HashMap<>();

    // Action Stack,(Used to undo last action)
    private static final Stack<Action> actionStack = new Stack<>();

    private static LocalDate currentDay = LocalDate.now(); // Track/Set the current day

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to MyFitnessApp!");
        createUser(scanner);

        //Intractable Menu
        //Loop that returns Menu untill exited
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1: Add Food");
            System.out.println("2: Add Exercise");
            System.out.println("3: View Today's Log");
            System.out.println("4: View Total Calories for All Days");
            System.out.println("5: Exit");
            System.out.println("6: Undo Last Action");
            System.out.println("7: View Longest Streak");
            System.out.println("8: End Day");
            System.out.print("Click a button: ");
            int Button = scanner.nextInt();

            // Perform Button choice
            switch (Button) {
                case 1 -> addFood(scanner);
                case 2 -> addExercise(scanner);
                case 3 -> DisplayLog();
                case 4 -> DisplayTotalCalories();
                case 5 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                case 6 -> undoLastAction();
                case 7 -> printLongestStreak();
                case 8 -> endDay();
                default -> System.out.println("Invalid selection, Please Try again.");
            }
        }
    }

    //User creation
    private static void createUser(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.next();
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        System.out.print("Enter your weight (lb): ");
        double weight = scanner.nextDouble();
        System.out.print("Enter your daily calorie goal: ");
        double dailyGoal = scanner.nextDouble();

        user = new User(name, age, weight, dailyGoal);
    }

    // Add Food item to current day's log
    private static void addFood(Scanner scanner) {
        System.out.print("Enter food name: ");
        scanner.nextLine(); // Consume leftover newline
        String foodName = scanner.nextLine();
        System.out.print("Enter calories: ");
        int calories = scanner.nextInt();

        FoodItem foodItem = new FoodItem(foodName, calories);
        DailyLog log = ActionLogs.computeIfAbsent(currentDay, k -> new DailyLog(currentDay));       //ChatGpt Recommend
        log.addEntry(foodItem);

        // Pushes action to stack
        actionStack.push(new Action("FOOD", foodItem));

        System.out.println("Food added!");
    }

    // Add Exercise activity to current day's log
    private static void addExercise(Scanner scanner) {
        System.out.print("Enter exercise name: ");
        scanner.nextLine(); // Consume leftover newline
        String exerciseName = scanner.nextLine();
        System.out.print("Enter calories burned: ");
        int caloriesBurned = scanner.nextInt();

        Exercise exercise = new Exercise(exerciseName, caloriesBurned);
        DailyLog log = ActionLogs.computeIfAbsent(currentDay, k -> new DailyLog(currentDay));        //ChatGpt Recommend
        log.addEntry(exercise);

        // Pushes action to stack
        actionStack.push(new Action("EXERCISE", exercise));
        System.out.println("Exercise added!");
    }

    // View current day's log entries
    private static void DisplayLog() {
        DailyLog log = ActionLogs.get(currentDay);
        if (log == null) {
            System.out.println("No Log entries found for today.");
        } else {
            System.out.println(log);
        }
    }

    // View total calories across All days
    private static void DisplayTotalCalories() {
        int totalCalories = CurrentTotalCalories_Sum(new ArrayList<>(ActionLogs.values()), 0);
        System.out.println("Total net calories across all days: " + totalCalories + " kcal");
    }

    // Recursive helper method that calculates total calories from list of logs
    private static int CurrentTotalCalories_Sum(List<DailyLog> logList, int index) {
        if (index >= logList.size()) return 0; // Base case: End of the list
        else
            return logList.get(index).calculateNetCalories() + CurrentTotalCalories_Sum(logList, index + 1);
    }

    // Undo last performed action (Food or Exercise).
    private static void undoLastAction() {
        //Base Case: (If Stack is empty)
        if (actionStack.isEmpty()) {
            System.out.println("No actions to undo.");
            return;
        }
        Action lastAction = actionStack.pop();
        DailyLog ActionLog = ActionLogs.get(currentDay);
        if (ActionLog == null) {
            System.out.println("Error: No log for today exists.");
            return;
        }

        // Check action type and remove  corresponding entry
        if ("FOOD".equals(lastAction.getType())) {
            ActionLog.removeEntry((FoodItem) lastAction.getItem());
            System.out.println("Undo successful: Removed last food item.");
        } else if ("EXERCISE".equals(lastAction.getType())) {
            ActionLog.removeEntry((Exercise) lastAction.getItem());
            System.out.println("Undo successful: Removed last exercise.");
        }
    }

    // Recursive method to find longest streak of achieving daily calorie goals
    private static int findLongestStreak(List<DailyLog> logs, int index, int currentStreak, int longestStreak) {
        if (index >= ActionLogs.size()) {
            return Math.max(currentStreak, longestStreak);      //Base case: Return maximum streak
        }
        DailyLog Streaklog = logs.get(index);

        // Increase streak if daily calorie goal is met; otherwise reset
        if (Streaklog.calculateNetCalories() <= user.getDailyCalorieGoal()) {
            return findLongestStreak(logs, index + 1, currentStreak + 1, longestStreak);
        }
        return findLongestStreak(logs, index + 1, 0, Math.max(currentStreak, longestStreak));
    }

    // Print  longest calorie streak
    private static void printLongestStreak() {
        List<DailyLog> logList = new ArrayList<>(ActionLogs.values());
        logList.sort(Comparator.comparing(DailyLog::getDate));
        int longestStreak = findLongestStreak(logList, 0, 0, 0);
        System.out.println("Longest streak of achieving calorie goals: " + longestStreak + " days");
    }

    // End current day and start new one.
    private static void endDay() {
        System.out.println("Day ending: " + currentDay);

        // Ensure log exists for the current day
//        ActionLogs.computeIfAbsent(currentDay, k -> new DailyLog(currentDay));        //ChatGPT recomended

        // Move to next day
        currentDay = currentDay.plusDays(1);
        System.out.println("New day started: " + currentDay);
    }
}