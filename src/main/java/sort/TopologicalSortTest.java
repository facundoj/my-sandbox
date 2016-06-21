package sort;

public class TopologicalSortTest {
    public static void main(String[] args) {
        TopologicalGraph g = new TopologicalGraph();
        g.addDependency("F", "E");
        g.addDependency("F", "D");
        g.addDependency("E", "B");
        g.addDependency("D", "B");
        g.addDependency("D", "A");
        g.addDependency("D", "C");
        g.addDependency("C", "A");

        for (String c : g.getOrdered()) {
            System.out.println(c);
        }
    }
}
