import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FabricSheet {
    private ArrayList<ArrayList<Integer>> squareInches;

    public FabricSheet(int minSize) {
        this.squareInches = new ArrayList<>();
        for (int i = 0; i < minSize; i++) {
            this.squareInches.add(new ArrayList<>(Collections.nCopies(minSize, -1)));
        }
    }

    public void print() {
        for (ArrayList<Integer> row : squareInches) {
            for (Integer col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    public void addClaim(Claim claim) {
        for (int i = claim.getInchesFromTop(); i < claim.getHeight()+claim.getInchesFromTop(); i++) {
            for (int j = claim.getInchesFromLeft(); j < claim.getWidth()+claim.getInchesFromLeft(); j++) {
                if (this.squareInches.get(i).get(j) == -1) {
                    this.squareInches.get(i).set(j, claim.getID());
                } else {
                    this.squareInches.get(i).set(j, -2);
                }
            }
        }
    }

    public int getNumOfConflicts() {
        int counter = 0;
        for (ArrayList<Integer> row : this.squareInches) {
            for (Integer i : row) {
                if (i == -2) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public boolean checkClaim(Claim claim) {
        for (int i = claim.getInchesFromTop(); i < claim.getHeight()+claim.getInchesFromTop(); i++) {
            for (int j = claim.getInchesFromLeft(); j < claim.getWidth()+claim.getInchesFromLeft(); j++) {
                if (this.squareInches.get(i).get(j) == -2) {
                    return false;
                }
            }
        }
        return true;
    }
}
