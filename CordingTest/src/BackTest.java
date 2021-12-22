import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BackTest {

	static int size, count = 0;
	static boolean[] origin, target, copy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		origin = new boolean[size + 2];
		target = new boolean[size + 2];

		char[] input = br.readLine().toCharArray();
		for (int i = 1; i <= size; i++) {
			if (input[i - 1] == '1') {
				origin[i] = true;
			}
		}
		input = br.readLine().toCharArray();
		for (int i = 1; i <= size; i++) {
			if (input[i - 1] == '1') {
				target[i] = true;
			}
		}

		simulation();

	}

	private static void simulation() {
		count = 0;
		if (isPossible()) { // ���� �״���϶�
			System.out.println(count);
			return;
		}
//		print();
		change(1, false);
		count = 1;
		if (isPossible()) { // 1�� ����ġ�� ����
			System.out.println(count);
			return;
		}
		System.out.println(-1);
//		print();
	}

	private static boolean isPossible() {
		copy = origin.clone();

		for (int i = 2; i < size; i++) {
			if (copy[i - 1] != target[i - 1]) {
				count++;
				change(i, true);
			}
		}
		if (copy[size - 1] != target[size - 1]) { // N��° ����ġ �����ߵǳ�?
			copy[size - 1] = target[size - 1];
			if (copy[size]) {
				copy[size] = false;
			} else {
				copy[size] = true;
			}
			count++;
		}

		for (int i = 1; i <= size; i++) {
			if (copy[i] != target[i]) {
				return false;
			}
		}
		return true;
	}

	private static void change(int index, boolean flag) {
		if (flag) {
			for (int i = 0; i < 3; i++) {
				if (copy[index - 1 + i]) {
					copy[index - 1 + i] = false;
				} else {
					copy[index - 1 + i] = true;
				}
			}
		} else {
			for (int i = 0; i < 3; i++) {
				if (origin[index - 1 + i]) {
					origin[index - 1 + i] = false;
				} else {
					origin[index - 1 + i] = true;
				}
			}
		}
	}

	private static void print() {
		for (int i = 1; i <= size; i++) {
			System.out.print(copy[i] ? '1' : '0');
		}
		System.out.println();
	}

}
