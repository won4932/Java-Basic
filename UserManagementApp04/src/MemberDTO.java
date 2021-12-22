import java.util.Formatter;

public class MemberDTO { 

	private String no;
	private String name;
	private String ssn;
	private String phoneNum;
	private String registdate;
	
	//기본생성자
	public MemberDTO() {
	
	}
	
	//생성자
	public MemberDTO(String name, String ssn, String phoneNum) {
		this.name = name;
		this.ssn = ssn;
		this.phoneNum = phoneNum;
	}
	
	
	//생성자
	public MemberDTO(String no, String name, String ssn, String phoneNum,
			String registdate) {
		super();
		this.no = no;
		this.name = name;
		this.ssn = ssn;
		this.phoneNum = phoneNum;
		this.registdate = registdate;
		
	}

	//getter, setter
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
		
	

	public String getRegistdate() {
		return registdate;
	}

	public void setRegistdate(String registdate) {
		/*SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			registdate = textFormat.format(textFormat.parse(registdate));
		} catch (ParseException e) {}*/
		this.registdate = registdate;
		
	}

	@Override
	public String toString() {
		Formatter fm = new Formatter();
		String meminfo = fm.format("%5s\t  %-7s\t%-16s\t%-14s\t%-14s", no, name,ssn, phoneNum,registdate).toString();
		return meminfo;
	}
	

	public String getInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n");
		sb.append("[ "+no+ " ] 회원님의 정보====\n");
		sb.append("이    름 : "+name+"\n");
		sb.append("주민번호 : "+ssn+"\n");
		sb.append("전화번호 : "+phoneNum+"\n");
		sb.append("등록일자 : "+registdate+"\n");
				
		return sb.toString();
	}

}
