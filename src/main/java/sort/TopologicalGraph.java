package sort;

import java.util.*;

public class TopologicalGraph {
    private Map<String, TopologicalNode> nodes;

    public TopologicalGraph() {
        this.nodes = new HashMap<String, TopologicalNode>();
    }

    public void addDependency(String node, String dependency) {
        TopologicalNode nodeTN = nodes.get(node);
        if (nodeTN == null) {
            nodeTN = new TopologicalNode(node);
            nodes.put(node, nodeTN);
        }
        TopologicalNode depTN = nodes.get(dependency);
        if (depTN == null) {
            depTN = new TopologicalNode(dependency);
            nodes.put(dependency, depTN);
        }
        nodeTN.addDependency(depTN);
        depTN.addDependent(nodeTN);
    }

    public List<String> getOrdered() {
        List<String> out = new ArrayList<String>(nodes.size());
        // Works as topological sort's S, which contains all those nodes ready to be loaded (dependencies fulfilled)
        LinkedList<TopologicalNode> independents = getIndependents();
        while (!independents.isEmpty()) {
            // Curr will be added to the output queue
            TopologicalNode curr = independents.poll();
            TopologicalNode dependant = curr.getAnyDependant();
            while (dependant != null) {
                // Removing edges coming from curr
                dependant.removeDependency(curr);
                curr.removeDependant(dependant);
                if (!dependant.hasDependencies()) independents.add(dependant);
                dependant = curr.getAnyDependant();
            }
            out.add(curr.getName());
        }
        return out;
    }

    private LinkedList<TopologicalNode> getIndependents() {
        LinkedList<TopologicalNode> indeps = new LinkedList<TopologicalNode>();
        for (String node : nodes.keySet()) {
            TopologicalNode topoNode = nodes.get(node);
            if (!topoNode.hasDependencies()) {
                indeps.add(topoNode);
            }
        }
        return indeps;
    }

    private static class TopologicalNode implements Comparable {
        private Set<TopologicalNode> dependencies;
        private Set<TopologicalNode> dependants;
        private String name;

        public TopologicalNode(String name) {
            this.name = name;
            dependants = new TreeSet<TopologicalNode>();
            dependencies = new TreeSet<TopologicalNode>();
        }

        public String getName() {
            return name;
        }

        public void addDependency(TopologicalNode dependency) {
            dependencies.add(dependency);
        }

        public void addDependent(TopologicalNode dependent) {
            dependants.add(dependent);
        }

        public TopologicalNode getAnyDependant() {
            for (TopologicalNode n : dependants) {
                return n;
            }
            return null;
        }

        public void removeDependency(TopologicalNode dependency) {
            dependencies.remove(dependency);
        }

        public void removeDependant(TopologicalNode dependant) {
            dependants.remove(dependant);
        }

        public boolean hasDependencies() {
            return dependencies.size() > 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TopologicalNode that = (TopologicalNode) o;

            return name != null ? name.equals(that.name) : that.name == null;

        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }

        public int compareTo(Object o) {
            TopologicalNode other = (TopologicalNode) o;
            return getName().compareTo(other.getName());
        }
    }
}
