package DFS;

import java.util.*;

class Main {
	private static int[] X = {-1,0,1,0}; 
	private static int[] Y = {0,1,0,-1}; 
	private static int arr[][];
	private static int count =1;
  private static void solution(int sizeOfMatrix, int[][] matrix) {
		arr = new int[sizeOfMatrix][sizeOfMatrix];
		for(int i =0; i<sizeOfMatrix; i++) {
			for(int j = 0; j<sizeOfMatrix; j++) {
				arr[i][j] = matrix[i][j];
			}
		}
		ArrayList<Integer> answer = new ArrayList<Integer>();
		for(int i =0; i<sizeOfMatrix; i++) {
			for(int j = 0; j<sizeOfMatrix; j++) {
				if(arr[i][j]==1) {
					dfs(i, j);
					answer.add(count);
					count = 1;
				}
			}
		}
		Collections.sort(answer);
		System.out.println(answer.size());
		for(int i = 0; i<answer.size(); i++) {
			System.out.print(answer.get(i) + " ");
		}
  }
	
	private static void dfs(int x, int y) {
		 arr[x][y] = 0;
		 
		 for(int i=0; i<4; i++) {
			 int nextX = x + X[i];
			 int nextY = y + Y[i];
			if(nextX >=0 && nextY>=0 && nextX<arr.length && nextY<arr.length) {
				if(arr[nextX][nextY] == 1) {
					dfs(nextX,nextY);
					count++;
				}
			}
		}
	}
  
  private static class InputData {
    int sizeOfMatrix;
    int[][] matrix;
  }

  private static InputData processStdin() {
    InputData inputData = new InputData();

    try (Scanner scanner = new Scanner(System.in)) {
      inputData.sizeOfMatrix = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));      
      
      inputData.matrix = new int[inputData.sizeOfMatrix][inputData.sizeOfMatrix];
      for (int i = 0; i < inputData.sizeOfMatrix; i++) {
        String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
        for (int j = 0; j < inputData.sizeOfMatrix; j++) {
          inputData.matrix[i][j] = Integer.parseInt(buf[j]);
        }
      }
    } catch (Exception e) {
      throw e;
    }

    return inputData;
  }

  public static void main(String[] args) throws Exception {
    InputData inputData = processStdin();

    solution(inputData.sizeOfMatrix, inputData.matrix);
  }
}