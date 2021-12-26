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
	
	
	//�⺻������
	public MemberDAO() {
	
	}
		
    private void getConnection() throws ClassNotFoundException, SQLException{
        if(conn == null){ //dbConn�� null�̸� Connection ��ü ������..
            //��������
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            String user = "scott";
            String pw = "scott";
           
            //JDBC����̹� �ε�
            Class.forName("oracle.jdbc.driver.OracleDriver");
           
            //����Ŭ(DBMS)�� �����Ͽ� Connection ��ü ���.
            conn = DriverManager.getConnection(url,user,pw);
                               
        }
    }
   
    	
	/**
	* ȸ�� ����ϱ�
	*/
	public int insertMember(MemberDTO dto){		
		
		int result=500; //���� ���� 
		
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
			System.out.println("���ܹ߻�:insertMember()=> "+e.getMessage());
		}finally{			
			dbClose();
		}		
		return result;
	}		
	
	/**
	* ȸ����ȣ�� �ش��ϴ� ȸ������ ����
	*/
	public MemberDTO getMember(String no){
		MemberDTO dto =null;
		try {
			getConnection();
			
			String sql = "{call sp_member_getMember(:no,:rtn_row)}";
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, no);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR); //Ŀ��Ÿ������ ���Ϲ���.
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
			System.out.println("���ܹ߻�:deleteMember()=> "+e.getMessage());
		}finally{			
			dbClose();
		}		
		
		return dto;
	}

	/**
	* ����� ȸ�� ��� ����
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
			System.out.println("���ܹ߻�:getMemberList()=> "+e.getMessage());
		}finally{			
			dbClose();
		}	
		
		return list;
	}
	

	/**
	* ȸ�� ����
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
			System.out.println("���ܹ߻�:updateMember()=> "+e.getMessage());
			e.printStackTrace();
		}finally{			
			dbClose();
		}		
		return result;
	}
	
	
	/**
	* ȸ�� ����
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
			System.out.println("���ܹ߻�:deleteMember()=> "+e.getMessage());
		}finally{			
			dbClose();
		}		
		return result;
	}//deleteMember()--------------
	
	
	/**DB���� ����(�ݱ�)*/
    public void dbClose(){       
     
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("����:ResultSet��ü close():" + e.getMessage());
			}
		}
         
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("����:PreparedStatement��ü close():" + e.getMessage());
			}
		} 
		
		if (cstmt != null) {
			try {
				cstmt.close();
			} catch (SQLException e) {
				System.out.println("����:CallableStatement��ü close():" + e.getMessage());
			}
		}       
        
		   
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("����:Connection��ü close():" + e.getMessage());
			}
		}    
		  
		conn = null;        
    }//dbClose()---------
	
}
