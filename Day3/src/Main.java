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
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Part 1
        FabricSheet sheet = new FabricSheet(1000);
        List<Claim> claims = new ArrayList<>();

        String regex = "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)";
        for (String claim: lines) {
            if (!claim.matches(regex)) {
                System.out.println(claim + " doesn't match.");
                continue;
            }

            int ID = Integer.parseInt(claim.replaceAll(regex,"$1"));
            System.out.println("ID = " + ID);

            int inchesToLeft = Integer.parseInt(claim.replaceAll(regex,"$2"));
            System.out.println("inchesToLeft = " + inchesToLeft);

            int inchesToTop = Integer.parseInt(claim.replaceAll(regex,"$3"));
            System.out.println("inchesToTop = " + inchesToTop);

            int width = Integer.parseInt(claim.replaceAll(regex,"$4"));
            System.out.println("width = " + width);

            int height = Integer.parseInt(claim.replaceAll(regex,"$5"));
            System.out.println("height = " + height);
            System.out.println();

            Claim c = new Claim(ID, inchesToLeft, inchesToTop, width, height);
            claims.add(c);
            sheet.addClaim(c);
        }
        System.out.println(sheet.getNumOfConflicts());

        // Part 2
        for (Claim claim : claims) {
            if (sheet.checkClaim(claim)) {
                System.out.println(claim.getID());
                break;
            }
        }
    }
}
