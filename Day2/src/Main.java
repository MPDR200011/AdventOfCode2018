import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
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
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        //Part 1
        int twoCounter = 0;
        int threeCounter = 0;
        for (String l: lines) {
            boolean two = false;
            boolean three = false;

            int[] chars = new int[26];

            for (int i = 0; i < l.length(); i++) {
                chars[l.charAt(i)-97]++;
            }

            for (int i : chars) {
                if (two && three) {
                    break;
                }
                if (!two && i == 2) {
                    twoCounter++;
                    two = true;
                }
                if (!three && i == 3) {
                    threeCounter++;
                    three = true;
                }
            }
        }
        System.out.println(twoCounter*threeCounter);

        // Part 2
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.size(); j++) {
                if (i==j) {
                    continue;
                }
                int pos;
                if ((pos = compare(lines.get(i), lines.get(j)) ) != -1) {
                    String s = lines.get(i);
                    s = s.substring(0,pos) + s.substring(pos+1, s.length());
                    System.out.println(s);
                    break;
                }
            }
        }
    }

    private static int compare(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return -1;
        }
        int pos = -1;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (pos == -1) {
                    pos = i;
                } else {
                    return -1;
                }
            }
        }
        return pos;
    }
}
