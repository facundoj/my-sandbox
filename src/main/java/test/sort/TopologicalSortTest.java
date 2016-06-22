package test.sort;

import org.junit.Assert;
import org.junit.Test;
import sort.TopologicalGraph;

import java.util.HashSet;
import java.util.Set;

public class TopologicalSortTest {

    @Test
    public void testSuccess() {
        TopologicalGraph<Character> g = new TopologicalGraph<Character>();
        g.addDependency('F', 'E');
        g.addDependency('F', 'D');
        g.addDependency('E', 'B');
        g.addDependency('D', 'B');
        g.addDependency('D', 'A');
        g.addDependency('D', 'C');
        g.addDependency('C', 'A');

        Set<Character> loaded = new HashSet<Character>();
        for (char m : g.getOrdered()) {
            switch (m) {
                case 'C':
                    Assert.assertTrue(loaded.contains('A'));
                    break;
                case 'D':
                    Assert.assertTrue(loaded.contains('A'));
                    Assert.assertTrue(loaded.contains('C'));
                    Assert.assertTrue(loaded.contains('B'));
                    break;
                case 'E':
                    Assert.assertTrue(loaded.contains('B'));
                    break;
                case 'F':
                    Assert.assertTrue(loaded.contains('D'));
                    Assert.assertTrue(loaded.contains('E'));
                    break;
            }
            loaded.add(m);
        }
    }
}
