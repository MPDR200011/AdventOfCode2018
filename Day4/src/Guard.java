import java.util.Set;
import java.util.TreeSet;

public class Guard implements Comparable<Guard> {
    private int ID;
    private int asleepTable[];
    private Set<Log> logs = new TreeSet<>();

    public Guard(int ID) {
        this.ID = ID;
        this.asleepTable = new int[60];
    }

    public int getID() {
        return ID;
    }

    public void setAsleepRange(int startMinute, int endMinute) {
        //increments the respective indexes in the asleepTable
        if (startMinute > 59 || endMinute > 60 || startMinute >= endMinute) {
            return;
        }
        for (int i = startMinute; i < endMinute; i++) {
            asleepTable[i]++;
        }
    }

    public void addLog(Log l) {
        logs.add(l);
    }

    public int getMostAsleepMinute() {
        int max = 0;
        for (int i = 1; i < 60; i++) {
            if (asleepTable[i] >= asleepTable[max]) {
                max = i;
            }
        }
        return max;
    }

    public int getMostAsleepMinuteValue() {
        return asleepTable[getMostAsleepMinute()];
    }

    public Integer getTotalAsleepMinutes() {
        int sum = 0;
        for (int i = 0; i < 60; i++) {
            sum += asleepTable[i];
        }
        return sum;
    }

    @Override
    public int compareTo(Guard guard) {
        return getTotalAsleepMinutes().compareTo(guard.getTotalAsleepMinutes());
    }
}
