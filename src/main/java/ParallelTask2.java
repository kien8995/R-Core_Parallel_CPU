import java.util.Map;
import java.util.PriorityQueue;
import java.util.Vector;

import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;

public class ParallelTask2 extends Task {

	private String currentVertex;
	private Vector<Map.Entry<String, Vector<String>>> reachableList;
	private Map<String, Integer> reachability;
	private PriorityQueue<Vertex> vertexQueue;

	public ParallelTask2(String currentVertex, Vector<Map.Entry<String, Vector<String>>> reachableList,
			Map<String, Integer> reachability, PriorityQueue<Vertex> vertexQueue) {
		this.currentVertex = currentVertex;
		this.reachableList = reachableList;
		this.reachability = reachability;
		this.vertexQueue = vertexQueue;
	}

	@Override
	public void main(String[] arg0) throws Exception {
		int length = reachableList.size();

		parallelFor(0, length - 1).exec(new Loop() {

			@Override
			public void run(int index) throws Exception {
				Map.Entry<String, Vector<String>> entry = reachableList.get(index);

				if (entry.getValue().contains(currentVertex)) {
					reachability.put(entry.getKey(), reachability.get(entry.getKey()) - 1);
					vertexQueue.add(new Vertex(entry.getKey(), reachability.get(entry.getKey())));
				}
			}

		});
	}
}
