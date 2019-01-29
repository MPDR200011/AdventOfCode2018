import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
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
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Part 1
        String reduced = reducePolymer(lines.get(0));

        System.out.println(reduced.length());

        //Part 2
        String line = lines.get(0);
        String inputLower = line.toLowerCase();
        Set<Character> inputCharSet = new TreeSet<>();
        for (int i = 0; i < inputLower.length(); i++) {
            inputCharSet.add(inputLower.charAt(i));
        }

        int min = reduced.length();
        for (Character c: inputCharSet) {
            String newString = line.replaceAll(c.toString(),"");
            newString = newString.replaceAll(c.toString().toUpperCase(), "");
            int length = reducePolymer(newString).length();
            if (length < min) {
                min = length;
            }
        }

        System.out.println(min);
    }

    public static String reducePolymer(String polymer) {
        StringBuilder builder = new StringBuilder(polymer);
        boolean changed = false;
        do {
            changed = false;
            for (int i = 0; i < builder.length()-1; ) {
                if (Math.abs(builder.charAt(i)-builder.charAt(i+1)) == 32) {
                    changed = true;
                    builder.deleteCharAt(i);
                    builder.deleteCharAt(i);
                } else {
                    i++;
                }
            }
        } while (changed);
        return builder.toString();
    }
}
