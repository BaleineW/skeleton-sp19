package bearmaps.hw4;
/* @JJ*/

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private LinkedList<Vertex> solution = new LinkedList<>();
    private double solutionWeight;
    private int numStatesExplored;
    private double explorationTime;
    private final double INF = Double.POSITIVE_INFINITY;

    public AStarSolver(AStarGraph<Vertex> input, Vertex S, Vertex T, double timeOut) {
        ArrayHeapMinPQ<Vertex> PQ = new ArrayHeapMinPQ<>();
        HashMap<Vertex, Double> distToS = new HashMap<>();
        HashMap<Vertex, Double> distToT = new HashMap<>();
        HashMap<Vertex, Vertex> edgeTo = new HashMap<>();

        Stopwatch sw = new Stopwatch();
        distToS.put(S, 0.0);
        PQ.add(S, 0.0);

        while (PQ.size() != 0) {
            // reach the destination Vertex  T
            if (PQ.getSmallest().equals(T)) {
                outcome = SolverOutcome.SOLVED;
                solutionWeight = distToS.get(T);
                solution.addFirst(T);
                Vertex curr = PQ.getSmallest();
                while (!curr.equals(S)) {
                    curr = edgeTo.get(curr);
                    solution.addFirst(curr);
                }
                explorationTime = sw.elapsedTime();
                return;
            }
            // keep exploring
            List<WeightedEdge<Vertex>> neighbors = input.neighbors(PQ.removeSmallest());
            numStatesExplored += 1;
            explorationTime = sw.elapsedTime();
            if (explorationTime > timeOut) {
                outcome = SolverOutcome.TIMEOUT;
                solution = new LinkedList<>();
                solutionWeight = INF;
                return;
            }
            for (WeightedEdge<Vertex> edge : neighbors) {
                Vertex src = edge.from();
                Vertex dest = edge.to();
                Double w = edge.weight();
                if (!distToS.containsKey(dest)) {
                    distToS.put(dest, INF);
                }
                if (!distToT.containsKey(dest)) {
                    distToT.put(dest, input.estimatedDistanceToGoal(dest, T));
                }
                if (distToS.get(src) +  w < distToS.get(dest)) {
                    distToS.put(dest, distToS.get(src) +  w);
                    edgeTo.put(dest, src);
                    if (PQ.contains(dest)) {
                        PQ.changePriority(dest, distToS.get(dest) + distToT.get(dest));
                    } else {
                        PQ.add(dest, distToS.get(dest) + distToT.get(dest));
                    }
                }
            }
        }
        outcome = SolverOutcome.UNSOLVABLE;
        solution = new LinkedList<>();
        solutionWeight = 0;
        explorationTime = sw.elapsedTime();
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }
    @Override
    public List<Vertex> solution() {
        return solution;
    }
    @Override
    public double solutionWeight() {
        return solutionWeight;
    }
    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }
    @Override
    public double explorationTime() {
        return explorationTime;
    }

}
