import java.util.Arrays;

public class UnionFind {

    private int[] parent;

    /* Initially, all vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        Arrays.fill(parent, -1);
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= parent.length || vertex < 0) {
            throw new IndexOutOfBoundsException("The given vertex is not valid");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        return -1 * parent(find(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int r1 = find(v1);
        int r2 = find(v2);
        if (!connected(v1, v2)) {
            if (sizeOf(v1) >= sizeOf(v2)) {
                parent[r1] += parent[r2];
                parent[r2] = r1;
            } else {
                parent[r2] += parent[r1];
                parent[r1] = r2;
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int root = vertex;
        while (parent[root] >= 0) {
            root = parent[root];
        }
        // path compression
        int currRoot;
        while (vertex != root) {
            currRoot = parent(vertex);
            parent[vertex] = root;
            vertex = currRoot;
        }
        return root;
    }

}
