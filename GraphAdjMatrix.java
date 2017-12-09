

public class GraphAdjMatrix implements Graph {
	private int[][] edges;
	private int size;
	
	public GraphAdjMatrix(int size) {
		this.size = size;
		edges = new int[size][size];
	}
	@Override
	public void addEdge(int v1, int v2) {
		// done is passignment8
		
	}

	@Override
	public void topologicalSort() {
		// done in passignment8
	}

	@Override
	public void addEdge(int v1, int v2, int weight) {
		// Adds an undirected weighted edge between two vertices
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
	}

	@Override
	public int getEdge(int v1, int v2) {
		// returns an edge weight between two vertices
		return edges[v1][v2];
	}

	@Override
	public int createSpanningTree() {
		// creates a minimum spanning tree of the graph
		// removes any edges that are not a part of this tree
		// returns the total weight of the min tree
		//keep track of 3 things: known v unknown vertices, path, and cost (create three empty arrays)
		int[] cost = prim();
		int totalCost = 0;
		for(int i = 0; i < cost.length; i++) {
			totalCost+=cost[i];
		}
		return totalCost;
	}
	
	private int[] prim() {
	    int path[] = new int[size];
	    int cost[] = new int [size];
	    boolean known[] = new boolean[size];
	    // Initialize all keys as INFINITE
	    for (int i = 0; i < size; i++) {
	    	cost[i] = Integer.MAX_VALUE;
	    	known[i] = false;
	    }
	    //include the first vertex
	    cost[0] = 0;
	    path[0] = -1;
	    for (int i = 0; i < size-1; i++) {
	    // Pick the minimum cost from the set of vertices not yet included and change to known
	    	int u = minVertex(known, cost);
	    	known[u] = true;
	    // Update cost and path of the adjacent vertices of the picked vertex (only unknowns)
	    for (int v = 0; v < size; v++)
	    // Update the key only if edges[u][v] is smaller than cost[v]
	    	if (edges[u][v]!=0 && known[v] == false && edges[u][v] < cost[v]) {
	    		path[v]  = u;
	            cost[v] = edges[u][v];
	        }
	     }
	    return cost;
	 
	}

	private int minVertex(boolean[] known, int[] cost) {
		// Initialize min value
		int min = Integer.MAX_VALUE;
		int min_index=-1;
		for (int v = 0; v < known.length; v++) {
			if (known[v] == false && cost[v] < min) {
				min = cost[v];
				min_index = v;
            }
		}  
        return min_index;
	}

}
