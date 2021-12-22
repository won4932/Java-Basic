import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class MemberProc { 
	
	MemberDAO dao;
	
	//�⺻������
	public MemberProc() {
		dao = new MemberDAO();
			
		
	}
	
	/**
	* ȸ�� ���ó��
	*/
	public void insertMember(){			
		
		Scanner scn = new Scanner(System.in);
		
		System.out.println("ȸ�������� �Է����ּ���.");
		System.out.print("���̸� : ");
		//String name = scn.nextLine();
		String name = reInput(scn);
		System.out.print("���ֹι�ȣ : ");
		//String ssn = scn.nextLine();
		String ssn = reInput(scn);
		System.out.print("������ó : ");
		//String phoneNum = scn.nextLine();		
		String phoneNum = reInput(scn);		
		
		MemberDTO dto = new MemberDTO(name, ssn, phoneNum);		
		int r = dao.insertMember(dto); //�Է¹��� ������ �߰�
		
		if(r==200){
			System.out.println("ȸ������� ���������� �Ϸ�Ǿ����ϴ�.");
		}else if(r==100){
			System.out.println("�Է��Ͻ� �ֹε�Ϲ�ȣ�� ���� ȸ���� �����մϴ�.");
		}else if(r==500){
			System.out.println("ȸ������� ���������� �̷����� �ʾҽ��ϴ�.");
		}
		
	}	
	
		
	/**
	* ����� ȸ�� ��� ����
	*/
	public void showMemberList(){
	
		List<MemberDTO> list = dao.getMemberList();
		
		System.out.println("                             Member List");
		System.out.println("============================================================================");
		if(list!=null&&list.size()>0){			
			System.out.println("reg.No\t  �̸� \t\t�ֹι�ȣ\t\t����ó\t\t�����");
			System.out.println("============================================================================");
			
			for (MemberDTO dto : list){
				System.out.println(dto);
			}			
			
		}else{
			System.out.println("����� �����Ͱ� �����ϴ�. ");
		}
		System.out.println("====================================================================�� "+((list==null)?"0":list.size())+" ��=\n");
	}
	
	
	/**
	* ȸ�� ����.
	*/
	public void updateMember(){
		
		Scanner scn = new Scanner(System.in);
		System.out.println("������ ȸ���� ȸ����Ϲ�ȣ�� �Է����ּ���"); 
		System.out.print("��");
		String no = scn.nextLine();
		MemberDTO dto = dao.getMember(no);
		if (dto!=null) {
			
			System.out.println(dto.getInfo());
			
			
			System.out.println("�����۾��� ����Ͻðڽ��ϱ�?(Y/N)");
			String input = scn.nextLine();
			if(input.equalsIgnoreCase("y")){
				System.out.println("##�Է��� �Ͻ��������� ������ ������ �״�� �����˴ϴ�.");
				System.out.print("�������� �̸� : ");
				String name = scn.nextLine();
				if(name.trim().equals("")) name=dto.getName();
				System.out.print("�������� �ֹι�ȣ : ");
				String ssn = scn.nextLine();
				if(ssn.trim().equals("")) ssn=dto.getSsn();
				System.out.print("�������� ��ȭ��ȣ : ");
				String phoneNum = scn.nextLine();
				if(phoneNum.trim().equals("")) phoneNum=dto.getPhoneNum();
				
				dto =  new MemberDTO(no, name, ssn, phoneNum,dto.getRegistdate());
				
				boolean r = dao.updateMember(dto);
				
				if(r){
					
					System.out.println("ȸ���� ������ ������ ���� �����Ǿ����ϴ�.");
					System.out.println(dto.getInfo());
					
				}else{
					System.out.println("ȸ���� ������ ���������� ���� ���� �ʾҽ��ϴ�.");
				}
				
			}else{
				System.out.println("���� �۾��� ����Ͽ����ϴ�.");
			}
			
		}else{
			
			System.out.println("�Է��Ͻ� ȸ����Ϲ�ȣ�� �ش��ϴ� ȸ���� �������� �ʽ��ϴ�.");
			
		}
	}
	
	/**
	* ȸ�� ����
	*/
	public void deleteMember(){	
		
		Scanner scn = new Scanner(System.in);
		System.out.println("������ ȸ���� ȸ����Ϲ�ȣ�� �Է����ּ���");
		String no = scn.nextLine();
		MemberDTO dto = dao.getMember(no);
		if (dto!=null) {
			System.out.println(dto.getInfo());
			
			System.out.println("�� ȸ���� ������ ������ �����Ͻðڽ��ϱ�?(Y/N)");
			String input = scn.nextLine();
			if(input.equalsIgnoreCase("y")){
				boolean r = dao.deleteMember(no);
				
				if(r){
					System.out.println(no+"ȸ���� ������ ���������� �����Ǿ����ϴ�.");
				}else{
					System.out.println("ȸ���� ������ ���������� ���� ���� �ʾҽ��ϴ�.");
				}
			}else{
				System.out.println("���� �۾��� ����Ͽ����ϴ�.");
			}
		}else{
			
			System.out.println("�Է��Ͻ� ȸ����Ϲ�ȣ�� �ش��ϴ� ȸ���� �������� �ʽ��ϴ�.");
			
		}
	}
	
	
	/**
	* ȸ������Ʈ  �ܺη� ��� (������ ���, jxl���̺귯�� ���)
	*/
	public void exportMemberList(){
	
		List<MemberDTO> list = dao.getMemberList();
		useJxlWrite(list);
		
	}
	
	
	/**
	 * ȸ������Ʈ �ܺηκ��� �Է� (������ ������ �Է�, jxl���̺귯�� ���) 
	 */
	/*public void importMemberList(){
		try {
			Workbook workbook = Workbook.getWorkbook(new File("data.xls"));
			Sheet sheet = workbook.getSheet(0);
			
			
			int row = 1;
			int end = sheet.getRows();
			int updateCount = 0;
			while(row < end ){
				MemberDTO vo = new MemberDTO();
				String id = sheet.getCell(0, row).getContents();
				if (containKey(id)) updateCount++;				
				vo.setId(id);
				vo.setName(sheet.getCell(1, row).getContents());
				vo.setSsn(sheet.getCell(2, row).getContents());
				vo.setPhoneNum(sheet.getCell(3, row).getContents());
				
				regMem(vo);
				row++;
			}
			workbook.close();
			System.out.println("�������Ϸκ��� "+(row-1)+"���� �����͸� �о����ϴ�.");
			System.out.println("=> "+(end-updateCount-1)+" ���� �����Ͱ� �߰�, "+updateCount+"���� �����Ͱ� ���ŵǾ����ϴ�.\n");
		} catch (Exception e) {
			System.out.println("���ܹ߻�: "+e.getMessage());
		}
		
		
	}*/
	
	
	public void useJxlWrite(List<MemberDTO> list){
		WritableWorkbook workbook=null;      
        try {
        	
        	workbook = Workbook.createWorkbook(new File("data.xls"));//��ũ�� ����
            WritableSheet sheet = workbook.createSheet("Member List",  0); //��Ʈ����          
            
             
            
            //�� �Ӹ� �� ���� 
            WritableCellFormat ColumNameFormat = new WritableCellFormat(); // ���Ӹ� �� ���� ����
            ColumNameFormat.setAlignment(Alignment.CENTRE); // �� ��� ����
        	ColumNameFormat.setBackground(Colour.GOLD); // �� ���� ����.
        	WritableFont arial10fontBold = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD); 
        	//��Ʈ���İ��ð�ü ����.  new WritableFont(��Ʈ�̸�, ��Ʈũ��,��Ʈ��������)
        	ColumNameFormat.setFont(arial10fontBold); //������ ��Ʈ������ �����˿� ����.
        
        	
        	WritableCellFormat titleFormat = new WritableCellFormat(); // ���Ӹ� �� ���� ����
        	titleFormat.setAlignment(Alignment.CENTRE); // �� ��������:  ��� ����
        	titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE); // �� ��������:  ��� ����
        	titleFormat.setFont( new WritableFont(WritableFont.TAHOMA, 16,WritableFont.BOLD,false,UnderlineStyle.DOUBLE,Colour.SEA_GREEN));
        	
        	  
        	//mergeCells(int col1, int row1, int col2, int row2) 
            //Merges the specified cells.
            sheet.mergeCells(0, 0, 4, 0);          
            sheet.setRowView(0, 480); 
            sheet.addCell(new Label(0, 0, "Member List",titleFormat));
        	
        	// Sheet�� �÷� ���� ���� , setCloumnView(���° �÷�, ����)
            sheet.setColumnView(0, 20); // sheet�� 0��° �÷��� ���� ����. 
            sheet.setColumnView(1, 15); // sheet��  1��° �÷��� ���� ����
            sheet.setColumnView(2, 20); // sheet�� 2��° �÷��� ���� ����
            sheet.setColumnView(3, 20); // sheet�� 3��° �÷��� ���� ����
            sheet.setColumnView(4, 20); // sheet�� 3��° �÷��� ���� ����
            
            // ���Ӹ� Cell������ sheet �߰�
            sheet.addCell(new Label(0, 1, "reg.No",ColumNameFormat)); //��(��,��,"����",����)
			sheet.addCell(new Label(1, 1, "�̸�",ColumNameFormat));
			sheet.addCell(new Label(2, 1, "�ֹι�ȣ",ColumNameFormat));
			sheet.addCell(new Label(3, 1, "����ó",ColumNameFormat));		
			sheet.addCell(new Label(4, 1, "�����",ColumNameFormat));	
	   
		    
	        int rowNum = 2;  
	        for(MemberDTO vo : list){
	        	 try {            		 
		            	
		                //jxl.write.Label.Label(int c, int r, String cont) : //��, ��, ���� 
		                Label lblNo = new Label(0,rowNum, vo.getNo() ); 
		                Label lblName = new Label(1,rowNum,vo.getName() ); 
		                Label lblSsn = new Label(2,rowNum, vo.getSsn()); 
		                Label lblPhoneNum = new Label(3,rowNum,vo.getPhoneNum() ); 	                
		                Label lblRegistDate = new Label(4,rowNum,vo.getRegistdate() );
	               
	                	//���� �� �߰� 
	                    sheet.addCell(lblNo);
	     				sheet.addCell(lblName);
	     				sheet.addCell(lblSsn);
	     				sheet.addCell(lblPhoneNum);
	     				sheet.addCell(lblRegistDate);
	     				
	    			} catch (Exception e) {
	    				System.out.println("���ܹ߻�: "+e.getMessage());
	    			} 
	        	 
	             rowNum++;  
	        }
	        
	     	workbook.write(); //�غ�� ������ ���� ���信 �°� �ۼ� ��, ���� ���Ϸ� ���� 
			System.out.println("ȸ������� ������ ����Ǿ����ϴ�.");
		} catch (Exception e) {
			System.out.println("���ܹ߻�: "+e.getMessage());
		} finally{
			try {
				if(workbook!=null) workbook.close();//�ݱ� , ó�� �� �޸𸮿��� ���� ó��
			} catch (Exception e) {
				System.out.println("���ܹ߻�: "+e.getMessage());
			}
		}//catch ----------------
       
	}//writeJxl()----------------
	

	/**
	 * �����Է½� ���Է�, �׽�Ʈ �޼ҵ�...
	 */
	public String reInput(Scanner scn){
		
		String str="";
		while(true){
			str = scn.nextLine();
			if(!(str==null || str.trim().equals(""))){
				break;
			}else{
				System.out.println("������ �Է��ϽǼ������ϴ�. �ùٸ����� �Է����ּ���!");
				System.out.print("��");
			}
		}
		
		return str;
	}

}
