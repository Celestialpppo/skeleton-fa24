public class UnionFind {
    // TODO: Instance variables
    private int size;
    private int[] parent;
    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = -1; // -1 表示自己是根且集合大小为1
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        return -parent[find(v)];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        return parent[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if (v < 0 || v >= parent.length){
            throw new IllegalArgumentException("Invalid Argument:" + v);
        }
        if (parent[v] < 0){
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        int v1root = find(v1);
        int v2root = find(v2);

        if (v1 == v2 || v1root == v2root){
            return;
        }

        int v1Size = sizeOf(v1);
        int v2Size = sizeOf(v2);

        if (v1Size == v2Size || v1Size < v2Size){
            parent[v2root] += parent[v1root];
            parent[v1root] = v2root;
        } else {
            parent[v1root] += parent[v2root];
            parent[v2root] = v1root;
        }
    }

}
