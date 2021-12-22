import java.util.InputMismatchException;
import java.util.Scanner;


public class MemberManagement {	
	
	public static void main(String[] args) {
		
		MemberProc mm = new MemberProc(); //MemberProc��ü ����
			
		while (true) {
			System.out.println();
			System.out.println("============== ��� ���� ���α׷� ==============");
			System.out.println("1. ȸ�����");			
			System.out.println("2. ȸ�����   3. ȸ������   4. ȸ������ ����");
			System.out.println("5. ��������");
			System.out.println("6. ����");
			System.out.println("============== aaaaaaaaaaaaaaaaaa ==============");
			System.out.print("�޴��� �Է��ϼ��� : ");
			
			Scanner scn = new Scanner(System.in);
			int num=0;
			try {
				num = scn.nextInt();
				if(!(num>0 && num<7)){ //1~6���� ���ڰ� �ԷµǸ� ���� ���� �߻�
					throw new InputMismatchException();
				}
			} catch (InputMismatchException e) {
				System.out.println("�Էµ� ���� �߸��Ǿ����ϴ�. [1-6] �޴��� �������ּ���!");
			}
			
			switch (num) {
			case 1:
				mm.showMemberList();//ȸ�����			
				break;
			case 2:
				mm.insertMember(); //ȸ�� ���
				break;
			case 3:
				mm.deleteMember(); //ȸ�� ����				
				break;
			case 4:
				mm.updateMember(); //ȸ�� ����
				break;
			case 5:
				mm.exportMemberList(); //��������
				
				break;
			case 6:
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0); //���α׷� ����
					
			}//end switch()---------------
		}//while---------------------		
		
	}//main()--------------	
	
}//class MemberManagement
