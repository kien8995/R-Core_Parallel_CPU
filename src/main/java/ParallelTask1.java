import java.util.Map;
import java.util.PriorityQueue;
import java.util.Vector;

import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;

public class ParallelTask1 extends Task {

	private Map<String, Integer> rCore;
	private Vector<String> listVertex;
	private Map<String, Integer> reachability;
	private Map<String, Vector<String>> reachableList;
	private PriorityQueue<Vertex> vertexQueue;

	public ParallelTask1(Map<String, Integer> rCore, Vector<String> listVertex, Map<String, Integer> reachability,
			Map<String, Vector<String>> reachableList, PriorityQueue<Vertex> vertexQueue) {
		this.rCore = rCore;
		this.listVertex = listVertex;
		this.reachability = reachability;
		this.reachableList = reachableList;
		this.vertexQueue = vertexQueue;
	}

	@Override
	public void main(String[] arg0) throws Exception {
		int length = listVertex.size();

		parallelFor(0, length - 1).exec(new Loop() {

			@Override
			public void run(int index) throws Exception {
				String vertex = listVertex.get(index);

				if (!rCore.containsKey(vertex) && reachability.get(vertex) != null) {
					reachability.put(vertex, reachability.get(vertex) - 1);

					for (Map.Entry<String, Vector<String>> entry : reachableList.entrySet()) {
						if (entry.getValue().contains(vertex)) {
							reachability.put(entry.getKey(), reachability.get(entry.getKey()) - 1);
						}

					}
					vertexQueue.add(new Vertex(vertex, reachability.get(vertex)));
				}

			}

		});
	}
}
