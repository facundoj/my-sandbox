package test.hr;

import java.util.*;

public class JourneyToTheMoon {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int I = in.nextInt();

        Set<Integer>[] nacPerAstronaut = new HashSet[N];
        while (I-- > 0) {
            int x = in.nextInt();
            int y = in.nextInt();

            Set<Integer> currentNac;
            if (nacPerAstronaut[x] != null && nacPerAstronaut[y] != null) {
                nacPerAstronaut[x].addAll(nacPerAstronaut[y]);
                currentNac = nacPerAstronaut[x];
            } else if (nacPerAstronaut[x] != null) {
                currentNac = nacPerAstronaut[x];
            } else if (nacPerAstronaut[y] != null) {
                currentNac = nacPerAstronaut[y];
            } else {
                currentNac = new HashSet<Integer>();
            }

            currentNac.add(x);
            currentNac.add(y);

            nacPerAstronaut[x] = currentNac;
            nacPerAstronaut[y] = currentNac;
        }

        Set<Set<Integer>> validNacsSet = new HashSet<Set<Integer>>();
        for (Set<Integer> c : nacPerAstronaut)
            validNacsSet.add(c);

        List<Set<Integer>> validNacs = new ArrayList<Set<Integer>>();
        for (Set<Integer> c : validNacsSet)
            validNacs.add(c);

        int len = validNacs.size();
        int sum = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                sum += validNacs.get(i).size() * validNacs.get(j).size();
            }
        }

        System.out.println(sum);

    }
}
