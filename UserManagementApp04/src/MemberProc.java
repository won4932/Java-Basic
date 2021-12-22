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
	
	//기본생성자
	public MemberProc() {
		dao = new MemberDAO();
			
		
	}
	
	/**
	* 회원 등록처리
	*/
	public void insertMember(){			
		
		Scanner scn = new Scanner(System.in);
		
		System.out.println("회원정보를 입력해주세요.");
		System.out.print("▶이름 : ");
		//String name = scn.nextLine();
		String name = reInput(scn);
		System.out.print("▶주민번호 : ");
		//String ssn = scn.nextLine();
		String ssn = reInput(scn);
		System.out.print("▶연락처 : ");
		//String phoneNum = scn.nextLine();		
		String phoneNum = reInput(scn);		
		
		MemberDTO dto = new MemberDTO(name, ssn, phoneNum);		
		int r = dao.insertMember(dto); //입력받은 데이터 추가
		
		if(r==200){
			System.out.println("회원등록이 정상적으로 완료되었습니다.");
		}else if(r==100){
			System.out.println("입력하신 주민등록번호를 가진 회원이 존재합니다.");
		}else if(r==500){
			System.out.println("회원등록이 정상적으로 이루지지 않았습니다.");
		}
		
	}	
	
		
	/**
	* 저장된 회원 목록 보기
	*/
	public void showMemberList(){
	
		List<MemberDTO> list = dao.getMemberList();
		
		System.out.println("                             Member List");
		System.out.println("============================================================================");
		if(list!=null&&list.size()>0){			
			System.out.println("reg.No\t  이름 \t\t주민번호\t\t연락처\t\t등록일");
			System.out.println("============================================================================");
			
			for (MemberDTO dto : list){
				System.out.println(dto);
			}			
			
		}else{
			System.out.println("저장된 데이터가 없습니다. ");
		}
		System.out.println("====================================================================총 "+((list==null)?"0":list.size())+" 명=\n");
	}
	
	
	/**
	* 회원 수정.
	*/
	public void updateMember(){
		
		Scanner scn = new Scanner(System.in);
		System.out.println("수정할 회원의 회원등록번호를 입력해주세요"); 
		System.out.print("▶");
		String no = scn.nextLine();
		MemberDTO dto = dao.getMember(no);
		if (dto!=null) {
			
			System.out.println(dto.getInfo());
			
			
			System.out.println("수정작업을 계속하시겠습니까?(Y/N)");
			String input = scn.nextLine();
			if(input.equalsIgnoreCase("y")){
				System.out.println("##입력을 하시지않으면 기존의 정보가 그대로 유지됩니다.");
				System.out.print("▶수정할 이름 : ");
				String name = scn.nextLine();
				if(name.trim().equals("")) name=dto.getName();
				System.out.print("▶수정할 주민번호 : ");
				String ssn = scn.nextLine();
				if(ssn.trim().equals("")) ssn=dto.getSsn();
				System.out.print("▶수정할 전화번호 : ");
				String phoneNum = scn.nextLine();
				if(phoneNum.trim().equals("")) phoneNum=dto.getPhoneNum();
				
				dto =  new MemberDTO(no, name, ssn, phoneNum,dto.getRegistdate());
				
				boolean r = dao.updateMember(dto);
				
				if(r){
					
					System.out.println("회원의 정보가 다음과 같이 수정되었습니다.");
					System.out.println(dto.getInfo());
					
				}else{
					System.out.println("회원의 정보가 정상적으로 수정 되지 않았습니다.");
				}
				
			}else{
				System.out.println("수정 작업을 취소하였습니다.");
			}
			
		}else{
			
			System.out.println("입력하신 회원등록번호에 해당하는 회원이 존재하지 않습니다.");
			
		}
	}
	
	/**
	* 회원 삭제
	*/
	public void deleteMember(){	
		
		Scanner scn = new Scanner(System.in);
		System.out.println("삭제할 회원의 회원등록번호를 입력해주세요");
		String no = scn.nextLine();
		MemberDTO dto = dao.getMember(no);
		if (dto!=null) {
			System.out.println(dto.getInfo());
			
			System.out.println("위 회원의 정보를 정말로 삭제하시겠습니까?(Y/N)");
			String input = scn.nextLine();
			if(input.equalsIgnoreCase("y")){
				boolean r = dao.deleteMember(no);
				
				if(r){
					System.out.println(no+"회원의 정보가 정상적으로 삭제되었습니다.");
				}else{
					System.out.println("회원의 정보가 정상적으로 삭제 되지 않았습니다.");
				}
			}else{
				System.out.println("삭제 작업을 취소하였습니다.");
			}
		}else{
			
			System.out.println("입력하신 회원등록번호에 해당하는 회원이 존재하지 않습니다.");
			
		}
	}
	
	
	/**
	* 회원리스트  외부로 출력 (엑셀로 출력, jxl라이브러리 사용)
	*/
	public void exportMemberList(){
	
		List<MemberDTO> list = dao.getMemberList();
		useJxlWrite(list);
		
	}
	
	
	/**
	 * 회원리스트 외부로부터 입력 (엑셀로 데이터 입력, jxl라이브러리 사용) 
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
			System.out.println("엑셀파일로부터 "+(row-1)+"명의 데이터를 읽었습니다.");
			System.out.println("=> "+(end-updateCount-1)+" 명의 데이터가 추가, "+updateCount+"명의 데이터가 갱신되었습니다.\n");
		} catch (Exception e) {
			System.out.println("예외발생: "+e.getMessage());
		}
		
		
	}*/
	
	
	public void useJxlWrite(List<MemberDTO> list){
		WritableWorkbook workbook=null;      
        try {
        	
        	workbook = Workbook.createWorkbook(new File("data.xls"));//워크북 생성
            WritableSheet sheet = workbook.createSheet("Member List",  0); //시트생성          
            
             
            
            //열 머리 셀 포맷 
            WritableCellFormat ColumNameFormat = new WritableCellFormat(); // 열머리 셀 포멧 생성
            ColumNameFormat.setAlignment(Alignment.CENTRE); // 셀 가운데 정렬
        	ColumNameFormat.setBackground(Colour.GOLD); // 셀 배경색 설정.
        	WritableFont arial10fontBold = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD); 
        	//폰트서식관련객체 생성.  new WritableFont(폰트이름, 폰트크기,폰트굵기지정)
        	ColumNameFormat.setFont(arial10fontBold); //설정한 폰트서식을 셀포맷에 설정.
        
        	
        	WritableCellFormat titleFormat = new WritableCellFormat(); // 열머리 셀 포멧 생성
        	titleFormat.setAlignment(Alignment.CENTRE); // 셀 가로정렬:  가운데 정렬
        	titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE); // 셀 세로정렬:  가운데 정렬
        	titleFormat.setFont( new WritableFont(WritableFont.TAHOMA, 16,WritableFont.BOLD,false,UnderlineStyle.DOUBLE,Colour.SEA_GREEN));
        	
        	  
        	//mergeCells(int col1, int row1, int col2, int row2) 
            //Merges the specified cells.
            sheet.mergeCells(0, 0, 4, 0);          
            sheet.setRowView(0, 480); 
            sheet.addCell(new Label(0, 0, "Member List",titleFormat));
        	
        	// Sheet의 컬럼 넓이 설정 , setCloumnView(몇번째 컬럼, 넓이)
            sheet.setColumnView(0, 20); // sheet의 0번째 컬럼의 넓이 설정. 
            sheet.setColumnView(1, 15); // sheet의  1번째 컬럼의 넓이 설정
            sheet.setColumnView(2, 20); // sheet의 2번째 컬럼의 넓이 설정
            sheet.setColumnView(3, 20); // sheet의 3번째 컬럼의 넓이 설정
            sheet.setColumnView(4, 20); // sheet의 3번째 컬럼의 넓이 설정
            
            // 열머리 Cell생성후 sheet 추가
            sheet.addCell(new Label(0, 1, "reg.No",ColumNameFormat)); //라벨(열,행,"문장",포멧)
			sheet.addCell(new Label(1, 1, "이름",ColumNameFormat));
			sheet.addCell(new Label(2, 1, "주민번호",ColumNameFormat));
			sheet.addCell(new Label(3, 1, "연락처",ColumNameFormat));		
			sheet.addCell(new Label(4, 1, "등록일",ColumNameFormat));	
	   
		    
	        int rowNum = 2;  
	        for(MemberDTO vo : list){
	        	 try {            		 
		            	
		                //jxl.write.Label.Label(int c, int r, String cont) : //열, 행, 내용 
		                Label lblNo = new Label(0,rowNum, vo.getNo() ); 
		                Label lblName = new Label(1,rowNum,vo.getName() ); 
		                Label lblSsn = new Label(2,rowNum, vo.getSsn()); 
		                Label lblPhoneNum = new Label(3,rowNum,vo.getPhoneNum() ); 	                
		                Label lblRegistDate = new Label(4,rowNum,vo.getRegistdate() );
	               
	                	//셀에 라벨 추가 
	                    sheet.addCell(lblNo);
	     				sheet.addCell(lblName);
	     				sheet.addCell(lblSsn);
	     				sheet.addCell(lblPhoneNum);
	     				sheet.addCell(lblRegistDate);
	     				
	    			} catch (Exception e) {
	    				System.out.println("예외발생: "+e.getMessage());
	    			} 
	        	 
	             rowNum++;  
	        }
	        
	     	workbook.write(); //준비된 정보를 엑셀 포멧에 맞게 작성 즉, 엑셀 파일로 쓰기 
			System.out.println("회원목록이 엑셀로 저장되었습니다.");
		} catch (Exception e) {
			System.out.println("예외발생: "+e.getMessage());
		} finally{
			try {
				if(workbook!=null) workbook.close();//닫기 , 처리 후 메모리에서 해제 처리
			} catch (Exception e) {
				System.out.println("예외발생: "+e.getMessage());
			}
		}//catch ----------------
       
	}//writeJxl()----------------
	

	/**
	 * 공백입력시 재입력, 테스트 메소드...
	 */
	public String reInput(Scanner scn){
		
		String str="";
		while(true){
			str = scn.nextLine();
			if(!(str==null || str.trim().equals(""))){
				break;
			}else{
				System.out.println("공백은 입력하실수없습니다. 올바른값을 입력해주세요!");
				System.out.print("▶");
			}
		}
		
		return str;
	}

}
