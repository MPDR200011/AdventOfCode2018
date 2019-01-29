import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        File input = new File("input.txt");
        if (!input.exists()) {
            System.err.println("Input file non existent.");
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
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        //Part 1
        long freq = 0;
        for (String l: lines) {
            freq += Long.parseLong(l);
        }

        System.out.println("Part 1 - " + freq);



        //Part 2
        freq = 0;
        Set<Long> frequencies = new TreeSet<>();
        frequencies.add(freq);
        for (int i = 0; i < lines.size(); i = ++i%lines.size()) {
            freq += Long.parseLong(lines.get(i));
            if (!frequencies.add(freq)) {
                System.out.println("Part 2 - "+freq);
                break;
            }
        }
        reader.close();
    }
}
