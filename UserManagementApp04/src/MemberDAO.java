import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

public class MemberDAO { 
	
	private static Connection conn;
	
	private PreparedStatement pstmt;
	private CallableStatement cstmt;
	private ResultSet rs;
	
	
	//기본생성자
	public MemberDAO() {
	
	}
		
    private void getConnection() throws ClassNotFoundException, SQLException{
        if(conn == null){ //dbConn이 null이면 Connection 객체 얻어오기..
            //접속정보
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            String user = "scott";
            String pw = "scott";
           
            //JDBC드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");
           
            //오라클(DBMS)에 연결하여 Connection 객체 얻기.
            conn = DriverManager.getConnection(url,user,pw);
                               
        }
    }
   
    	
	/**
	* 회원 등록하기
	*/
	public int insertMember(MemberDTO dto){		
		
		int result=500; //내부 오류 
		
		try {
			getConnection();
			
			String sql = "{call SP_MEMBER_INSERT(:name,:ssn,:phoneNum,:rtn_code)}";
			cstmt = conn.prepareCall(sql);
			
			cstmt.setString(1,dto.getName());
			cstmt.setString(2,dto.getSsn());
			cstmt.setString(3,dto.getPhoneNum());
			cstmt.registerOutParameter(4, java.sql.Types.NUMERIC);	
			
			int r = cstmt.executeUpdate();
			int code = cstmt.getInt(4);			
			result = code;
			
		} catch (Exception e) {			
			System.out.println("예외발생:insertMember()=> "+e.getMessage());
		}finally{			
			dbClose();
		}		
		return result;
	}		
	
	/**
	* 회원번호에 해당하는 회원정보 보기
	*/
	public MemberDTO getMember(String no){
		MemberDTO dto =null;
		try {
			getConnection();
			
			String sql = "{call sp_member_getMember(:no,:rtn_row)}";
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, no);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR); //커서타입으로 리턴받음.
			cstmt.execute();
			ResultSet r =  (ResultSet) cstmt.getObject(2);
			
			if(r.next()) {
				String m_no = r.getString("m_no");
				String m_name = r.getString("m_name");
				String m_ssn = r.getString("m_ssn");
				String m_phoneNum = r.getString("m_phoneNum");
				String m_registdate = r.getDate("m_registdate").toString();
				dto = new MemberDTO(m_no, m_name, m_ssn, m_phoneNum, m_registdate);
			}
			
		} catch (Exception e) {
			System.out.println("예외발생:deleteMember()=> "+e.getMessage());
		}finally{			
			dbClose();
		}		
		
		return dto;
	}

	/**
	* 저장된 회원 목록 보기
	*/
	public List<MemberDTO> getMemberList(){
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try {
			getConnection();
			
			String sql = "{call SP_MEMBER_list(:rtn_list)}";
			
			cstmt = conn.prepareCall(sql);
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.execute();
			//ResultSet r = ((OracleCallableStatement) pstmt).getCursor(2);
			ResultSet r = (ResultSet) cstmt.getObject(1);
			
			while(r.next()) {
				String m_no = r.getString("m_no");
				String m_name = r.getString("m_name");
				String m_ssn = r.getString("m_ssn");
				String m_phoneNum = r.getString("m_phoneNum");
				String m_registdate = r.getDate("m_registdate").toString();
				list.add(new MemberDTO(m_no, m_name, m_ssn, m_phoneNum, m_registdate));
			}
			
		} catch (Exception e) {
			System.out.println("예외발생:getMemberList()=> "+e.getMessage());
		}finally{			
			dbClose();
		}	
		
		return list;
	}
	

	/**
	* 회원 수정
	*/
	public boolean updateMember(MemberDTO dto){
		
		boolean result = false;				
		try {
			getConnection();
			
			String sql = "{call SP_MEMBER_UPDATE(:no, :name, :ssn,:phoneNum, :rtn_code)}";
			cstmt = conn.prepareCall(sql);			
			
			cstmt.setString(1,dto.getNo());
			cstmt.setString(2,dto.getName());
			cstmt.setString(3,dto.getSsn());
			cstmt.setString(4,dto.getPhoneNum());
			cstmt.registerOutParameter(5, OracleTypes.NUMBER);			
			
			cstmt.execute();
			int r = cstmt.getInt(5);
			//System.out.println(r);
			if(r==200) result = true;
			
			
		} catch (Exception e) {
			System.out.println("예외발생:updateMember()=> "+e.getMessage());
			e.printStackTrace();
		}finally{			
			dbClose();
		}		
		return result;
	}
	
	
	/**
	* 회원 삭제
	*/
	public boolean deleteMember(String id){			
		boolean result = false;				
		try {
			getConnection();
			
			String sql = "{call SP_MEMBER_DELETE(:no, :rtn_cod)}";
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, OracleTypes.NUMBER);
			cstmt.execute();
			int r = cstmt.getInt(2);
			if(r==200) result = true;
			
		} catch (Exception e) {
			System.out.println("예외발생:deleteMember()=> "+e.getMessage());
		}finally{			
			dbClose();
		}		
		return result;
	}//deleteMember()--------------
	
	
	/**DB연결 해제(닫기)*/
    public void dbClose(){       
     
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("예외:ResultSet객체 close():" + e.getMessage());
			}
		}
         
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("예외:PreparedStatement객체 close():" + e.getMessage());
			}
		} 
		
		if (cstmt != null) {
			try {
				cstmt.close();
			} catch (SQLException e) {
				System.out.println("예외:CallableStatement객체 close():" + e.getMessage());
			}
		}       
        
		   
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("예외:Connection객체 close():" + e.getMessage());
			}
		}    
		  
		conn = null;        
    }//dbClose()---------
	
}
