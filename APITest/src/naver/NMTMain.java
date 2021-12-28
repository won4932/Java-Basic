package naver;

import java.util.Scanner;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import naver.PapagoNMT;

/**
 * NMT(Neural Machine Translation) 인공신경망 기반 기계번역
 * 입력 문장을 문장벡터로 변환하는 신경망(encoder)과 문장벡터에서 번역하는 언어의 문장을 생성하는 신경망(decoder)를 
 * 대규모의 병렬 코퍼스부터 자동으로 학습합니다. 
 * 입력문장의 일부가 아니라 문장 전체 정보를 바탕으로 번역을 수행하기때문에 기존 SMT방식의 번역보다 
 * 더욱 정확하고 문장 맥락에 맞는 번역을 하는것이 특징
 **/
public class NMTMain {
//Limit - 10000word/1day
    public static void main(String[] args) {
		String text = "", source = PapagoNMT.KOREAN, target = PapagoNMT.ENGLISH;
		int selected;
		
    	Scanner scan = new Scanner(System.in);
    	System.out.println("< 번역할 한글을 입력하세요 >");
    	text = scan.nextLine();
    	
    	System.out.println("어떤 언어로 번역하시겠습니까?");
    	System.out.println("1. 영어");
    	System.out.println("2. 프랑스어");
    	System.out.println("3. 중국어");
    	System.out.println("4. 베트남어");
    	System.out.print("번호 입력: ");
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
        	System.out.println("입력한 번호가 유효하지 않습니다.");
        	return;
    	}
    	
    	String translated = PapagoNMT.translate(source, target, text);
    	
    	//파서
    	JsonParser parser = new JsonParser();
    	JsonElement element = parser.parse(translated);
    	if(element.getAsJsonObject().get("errorMessage") != null) {
    		System.out.println("번역 오류가 발생했습니다. " +
    				"[오류 코드: " + element.getAsJsonObject().get("errorCode").getAsString() + "]");
    	} else if(element.getAsJsonObject().get("message") != null) {
        	System.out.println("< 번역 결과 >");
        	System.out.println(element.getAsJsonObject().get("message").getAsJsonObject().get("result")
        			.getAsJsonObject().get("translatedText").getAsString());
    	}
    }
}
