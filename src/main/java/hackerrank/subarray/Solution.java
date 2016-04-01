package hackerrank.subarray;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        // Each TC
        while (T-- > 0) {
            int n = in.nextInt();
            int i = n;
            List<Integer> originalList = new ArrayList<>();

            // Populating array
            while (i-- > 0) {
                originalList.add(in.nextInt());
            }

            // Edge case - all negative
            int max = Integer.MIN_VALUE;
            boolean allNegatives = true;
            for (i = 0; i < n && allNegatives; i++) {
                int curr = originalList.get(i);
                if (curr > 0) {
                    allNegatives = false;
                } else if (curr > max) {
                    max = curr;
                }
            }

            if (allNegatives) {
                System.out.printf("%d %d\n", max, max);
                continue;
            }

            // Contiguous
            long prevMax = Long.MIN_VALUE;
            long currentSum = 0;
            for (i = 0; i < n; i++) {
                if (currentSum < 0 && originalList.get(i) >= 0) {
                    currentSum = (long) originalList.get(i);
                } else if ((i + 1) < n && (originalList.get(i) + originalList.get(i + 1) > 0)) {
                    currentSum += originalList.get(i);
                } else if (originalList.get(i) > 0) {
                    currentSum += originalList.get(i);
                } else if (currentSum > prevMax) {
                    prevMax = currentSum;
                    currentSum = 0;
                } else {
                    currentSum = 0;
                }
            }

            if (currentSum > prevMax) {
                prevMax = currentSum;
            }

            System.out.printf("%d ", prevMax);

            // Non-contiguous
            currentSum = 0;
            for (int curr : originalList) {
                if (curr > 0) currentSum += curr;
            }
            System.out.printf("%d\n", currentSum);
        }
    }
}
