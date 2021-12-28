package naver;

import java.util.Scanner;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import naver.PapagoNMT;

/**
 * NMT(Neural Machine Translation) �ΰ��Ű�� ��� ������
 * �Է� ������ ���庤�ͷ� ��ȯ�ϴ� �Ű��(encoder)�� ���庤�Ϳ��� �����ϴ� ����� ������ �����ϴ� �Ű��(decoder)�� 
 * ��Ը��� ���� ���۽����� �ڵ����� �н��մϴ�. 
 * �Է¹����� �Ϻΰ� �ƴ϶� ���� ��ü ������ �������� ������ �����ϱ⶧���� ���� SMT����� �������� 
 * ���� ��Ȯ�ϰ� ���� �ƶ��� �´� ������ �ϴ°��� Ư¡
 **/
public class NMTMain {
//Limit - 10000word/1day
    public static void main(String[] args) {
		String text = "", source = PapagoNMT.KOREAN, target = PapagoNMT.ENGLISH;
		int selected;
		
    	Scanner scan = new Scanner(System.in);
    	System.out.println("< ������ �ѱ��� �Է��ϼ��� >");
    	text = scan.nextLine();
    	
    	System.out.println("� ���� �����Ͻðڽ��ϱ�?");
    	System.out.println("1. ����");
    	System.out.println("2. ��������");
    	System.out.println("3. �߱���");
    	System.out.println("4. ��Ʈ����");
    	System.out.print("��ȣ �Է�: ");
    	selected = scan.nextInt();
    	
    	if(selected == 1) {
    		target = PapagoNMT.ENGLISH;
    	} else if(selected == 2) {
    		target = PapagoNMT.FRENCH;
    	} else if(selected == 3) {
    		target = PapagoNMT.CHINESE;
    	} else if(selected == 4) {
    		target = PapagoNMT.VIETNAMESE;
    	} else {
        	System.out.println("�Է��� ��ȣ�� ��ȿ���� �ʽ��ϴ�.");
        	return;
    	}
    	
    	String translated = PapagoNMT.translate(source, target, text);
    	
    	//�ļ�
    	JsonParser parser = new JsonParser();
    	JsonElement element = parser.parse(translated);
    	if(element.getAsJsonObject().get("errorMessage") != null) {
    		System.out.println("���� ������ �߻��߽��ϴ�. " +
    				"[���� �ڵ�: " + element.getAsJsonObject().get("errorCode").getAsString() + "]");
    	} else if(element.getAsJsonObject().get("message") != null) {
        	System.out.println("< ���� ��� >");
        	System.out.println(element.getAsJsonObject().get("message").getAsJsonObject().get("result")
        			.getAsJsonObject().get("translatedText").getAsString());
    	}
    }
}
