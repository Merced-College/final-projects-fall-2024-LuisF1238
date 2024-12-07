package APP;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


// Daily Activity Log (Food/Exersice).
public class DailyLog {
    private final LocalDate date; //Local Date based on Consoles
    private final List<DailyLogEntry> entries; //List of all Entries

    //Daily Log Constructor
    public DailyLog(LocalDate date) {
        this.date = date;
        this.entries = new ArrayList<>();
    }

    //Date Getter
    public LocalDate getDate() {
        return date;
    }

    //Entry ++
    public void addEntry(DailyLogEntry entry) {
        entries.add(entry);
    }

    //Entry --
    public void removeEntry(DailyLogEntry entry) {
        entries.remove(entry);
    }

    // Sum of all Entries
    public int calculateNetCalories() {
        return entries.stream().mapToInt(DailyLogEntry::getCaloriesImpact).sum();
    }

    //String builder syntax when printing
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Log for " + date + ":\n");
        for (DailyLogEntry entry : entries) {
            sb.append(entry).append("\n");
        }
        sb.append("Net Calories: ").append(calculateNetCalories()).append(" kcal");
        return sb.toString();
    }
}
