import java.util.Random;


public class TodayWoonse {

	private static final String[] WOONSE = {"������ ����", "�ɷ��� �����ϱ� ������", "���ѿ���� ��Ʈ������ Ǯ���",
		"������ �ޱ⺸�ٴ� ������ �ִ³�", "���� �Ϸ� ������� �ູ�ϴ�", "���� �ٻ۳�", "���ڴ� �ݹ�", "�ڸ� ���ƺ���", "������ ���϶�",
		"������ �������� ������ Ŀ����","��ٸ��� ���� ���� ������ �����϶�"
	};
	
	public static String selWoonse(){
		return WOONSE[new Random().nextInt(WOONSE.length)];
	}

}
