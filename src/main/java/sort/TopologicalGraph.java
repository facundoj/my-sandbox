package sort;

import java.util.*;

public class TopologicalGraph<T extends Comparable> {
    // value --> Node
    private Map<T, TopologicalNode<T>> nodes;

    public TopologicalGraph() {
        this.nodes = new HashMap<T, TopologicalNode<T>>();
    }

    public void removeDependency(T node, T dependency) {
        TopologicalNode<T> nodeTN = nodes.get(node);
        TopologicalNode<T> depTN = nodes.get(dependency);
        removeDependency(nodeTN, depTN);
    }

    public void removeDependency(TopologicalNode<T> node, TopologicalNode<T> dependency) {
        node.removeDependency(dependency);
        dependency.removeDependant(node);
    }

    public void addDependency(T node, T dependency) {
        TopologicalNode<T> nodeTN = nodes.get(node);
        if (nodeTN == null) {
            nodeTN = new TopologicalNode<T>(node);
            nodes.put(node, nodeTN);
        }
        TopologicalNode<T> depTN = nodes.get(dependency);
        if (depTN == null) {
            depTN = new TopologicalNode<T>(dependency);
            nodes.put(dependency, depTN);
        }
        nodeTN.addDependency(depTN);
        depTN.addDependent(nodeTN);
    }

    public List<T> getOrdered() {
        List<T> out = new ArrayList<T>(nodes.size());
        // Works as topological sort's S, which contains all those nodes ready to be loaded (dependencies fulfilled)
        LinkedList<TopologicalNode<T>> independents = getIndependents();
        while (!independents.isEmpty()) {
            // curr will be added to the output queue
            TopologicalNode<T> curr = independents.poll();
            TopologicalNode<T> dependant;
            for (dependant = curr.getAnyDependant(); dependant != null; dependant = curr.getAnyDependant()) {
                // Removing edges coming from curr
                removeDependency(dependant, curr);
                // If no dependencies left, it's ready to be loaded
                if (!dependant.hasDependencies()) independents.add(dependant);
            }
            out.add(curr.getValue());
        }
        return out;
    }

    private LinkedList<TopologicalNode<T>> getIndependents() {
        LinkedList<TopologicalNode<T>> independents = new LinkedList<TopologicalNode<T>>();
        for (T node : nodes.keySet()) {
            TopologicalNode<T> topologicalNode = nodes.get(node);
            if (!topologicalNode.hasDependencies()) {
                independents.add(topologicalNode);
            }
        }
        return independents;
    }

    private static class TopologicalNode<K> {
        private Set<TopologicalNode<K>> dependencies;
        private Set<TopologicalNode<K>> dependants;
        private K value;

        TopologicalNode(K value) {
            this.value = value;
            dependants = new HashSet<TopologicalNode<K>>();
            dependencies = new HashSet<TopologicalNode<K>>();
        }

        K getValue() {
            return value;
        }

        void addDependency(TopologicalNode<K> dependency) {
            dependencies.add(dependency);
        }

        void addDependent(TopologicalNode<K> dependent) {
            dependants.add(dependent);
        }

        TopologicalNode<K> getAnyDependant() {
            Iterator<TopologicalNode<K>> it = dependants.iterator();
            return it.hasNext() ? it.next() : null;
        }

        void removeDependency(TopologicalNode dependency) {
            dependencies.remove(dependency);
        }

        void removeDependant(TopologicalNode dependant) {
            dependants.remove(dependant);
        }

        boolean hasDependencies() {
            return dependencies.size() > 0;
        }
    }
}
