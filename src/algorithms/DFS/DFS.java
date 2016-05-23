package algorithms.DFS;

import vars.Task;

public class DFS {
	public static float calculateLongestPath(Task root) {
		float result = root.getDuration();
		for (int i = 0; i < root.getSuccessors().size(); i++) {
			float temp = calculateLongestPath(root.getSuccessors().get(i)) + root.getDuration();
			if(temp > result)
				result = temp;
		}
		
		return result;
	}
}
