<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<!-- Oracle db Connection -->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Successfully Connected!</h1>
        <% Connection conn =null;
        String diverName = "oracle.jdbc.driver.OracleDriver";
        Class.forName(diverName);
        String serverID = "192.0.0.1";
        String serverPort = "1521";
        String sid = "orcl";
        String url = "jdbc:oracle:thin:@" + serverID + ":" + serverPort + ":" + sid;
        String id = "JspShoppingMall";
        String pw = "1234";
        conn = DriverManager.getConnection(url, id, pw);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from jspshop");
        while(rs.next()) {%>
       User ID : <%= rs.getInt(1)%><br/>
       User Type : <%=rs.getString(2)%><br/>
       User Name : <%=rs.getString(3)%><br/>
       Password : <%=rs.getString(4)%><br/>
       Birth Date : <%=rs.getDate(5)%><br/>
       Gender : <%=rs.getString(6)%><br/>
       Email : <%=rs.getString(7)%><br/>
       Contact : <%=rs.getString(8)%><br/>
       Address : <%=rs.getString(9)%><br/>
       <% }
         rs.close(); %>
    </body>
</html>
