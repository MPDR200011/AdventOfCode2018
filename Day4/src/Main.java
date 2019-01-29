import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        File input = new File("input.txt");
        if (!input.exists()) {
            System.err.println("Input file doesn't exist.");
            return;
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        List<String> lines = new ArrayList<>();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Part 1
        Set<Log> logs = new TreeSet<>();
        String lineRegex = "\\[(1518-(1[012]|0[1-9])-(0[1-9]|[12]\\d|3[01]) ([01]\\d|2[0-3]):[0-5]\\d)\\] (.*)";
        String beginShiftRegex = "Guard #(\\d+) begins shift";
        for (String line: lines) {
            if (!line.matches(lineRegex)) {
                System.out.println(line + " doesn't match.");
                return;
            } else {
                String timeString = line.replaceAll(lineRegex, "$1");
                String message = line.replaceAll(lineRegex, "$5");

                logs.add(new Log(message, timeString));
            }
        }
        /*
        for (Log l: logs) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            System.out.println(format.format(l.getTime()) + ": " + l.getMessage());
        }*/

        Map<Integer, Guard> guards = new HashMap<>();
        Guard currentGuard = null;
        int lastSleepMinute = -1;
        for (Log l: logs) {
            String message = l.getMessage();
            if (message.matches(beginShiftRegex)) {
                if (lastSleepMinute != -1) {
                    currentGuard.setAsleepRange(lastSleepMinute, 60);
                }
                int ID = Integer.parseInt(message.replaceAll(beginShiftRegex, "$1"));
                Guard newCurrent = guards.get(ID);
                if (newCurrent == null) {
                    Guard newGuard = new Guard(ID);
                    currentGuard = newGuard;
                    guards.put(ID, newGuard);
                } else {
                    currentGuard = newCurrent;
                }
                lastSleepMinute = -1;
            } else {
                Calendar logTime = Calendar.getInstance();
                logTime.setTime(l.getTime());
                if (message.equals("falls asleep")){
                    lastSleepMinute = logTime.get(Calendar.MINUTE);
                } else if (message.equals("wakes up")) {
                    if (lastSleepMinute != -1) {
                        currentGuard.setAsleepRange(lastSleepMinute, logTime.get(Calendar.MINUTE));
                        lastSleepMinute = -1;
                    }
                } else {
                    System.out.println("Invalid message: \"" + message + "\".");
                }
            }
            currentGuard.addLog(l);
        }

        Guard laziest = new Guard(-1);
        for (Integer key: guards.keySet()) {
            if (laziest.compareTo(guards.get(key)) < 0) {
                laziest = guards.get(key);
            }
        }
        System.out.println("Guard #" + laziest.getID() + " slept a total of " + laziest.getTotalAsleepMinutes() + " minutes.");
        System.out.printf("%d * %d = %d\n", laziest.getID(), laziest.getMostAsleepMinute(), laziest.getID()*laziest.getMostAsleepMinute());

        //Part 2
        laziest = new Guard(-1);
        for (Integer key: guards.keySet()) {
            if (guards.get(key).getMostAsleepMinuteValue() > laziest.getMostAsleepMinuteValue()) {
                laziest = guards.get(key);
            }
        }

        System.out.printf("Guard #%d slept the most in minute %d.\n", laziest.getID(), laziest.getMostAsleepMinute());
        System.out.printf("%d * %d = %d\n", laziest.getID(), laziest.getMostAsleepMinute(), laziest.getID()* laziest.getMostAsleepMinute());
    }
}
