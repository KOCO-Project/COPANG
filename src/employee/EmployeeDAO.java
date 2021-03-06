package employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




public class EmployeeDAO {
	private EmployeeDTO empDTO;
	private Connection conn;
	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private int cnt;
	private ArrayList<EmployeeDTO> empList;
	
	public EmployeeDAO() {
		empDTO = new EmployeeDTO();
		
		String dbURL = "jdbc:mysql://bbr123.cafe24.com:3306/bbr123?characterEncoding=utf8";
		String dbID = "bbr123";
		String dbPWD = "alstjr95!";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPWD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void empCloseAll() {
		try {
			conn.close();
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void empClose() {
		try {
			conn.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int employeeRegister(EmployeeDTO empDTO) throws SQLException {
		sql = "insert into EMPLOYEE(EMP_NO,EMP_NAME,EMP_TEL,EMP_ADDR,EMP_SECURITY,EMP_PW,DEPT_NO,EMP_RANK) values(?,?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, empDTO.getEmpNo());
		pstmt.setString(2, empDTO.getEmpName());
		pstmt.setString(3, empDTO.getEmpTel());
		pstmt.setString(4, empDTO.getEmpAddr());
		pstmt.setString(5, empDTO.getEmpSecurity());
		pstmt.setString(6, empDTO.getEmpPw());
		pstmt.setInt(7, empDTO.getDeptNo());
		pstmt.setInt(8, empDTO.getEmpRank());
		cnt = pstmt.executeUpdate();
		return cnt;
	}
	
	public ResultSet idDupleCheck(int noSearch) throws SQLException {
		sql = "select EMP_NO from EMPLOYEE where EMP_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, noSearch);
		rs = pstmt.executeQuery();
		return rs;
	}
	public ResultSet pwCheck(int empNo) throws SQLException {
		sql = "select EMP_PW from EMPLOYEE where EMP_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, empNo);
		rs =  pstmt.executeQuery();
		return rs;
	}

	public int empDelete(int empNo) throws SQLException {
		sql = "delete from EMPLOYEE where EMP_NO=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, empNo);
		pstmt.executeUpdate();
		return cnt;
	}
	
	public ArrayList<EmployeeDTO> empList() throws SQLException{
		sql = "select * from EMPLOYEE";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		empList = new ArrayList<EmployeeDTO>();
		while(rs.next()) {    
			empDTO = new EmployeeDTO();
			empDTO.setDeptNo(rs.getInt("DEPT_NO"));
			empDTO.setEmpAddr(rs.getString("EMP_ADDR"));
			empDTO.setEmpName(rs.getString("EMP_NAME"));
			empDTO.setEmpNo(rs.getInt("EMP_NO"));
			empDTO.setEmpPw(rs.getString("EMP_PW"));
			empDTO.setEmpRank(rs.getInt("EMP_RANK"));
			empDTO.setEmpSecurity(rs.getString("EMP_SECURITY"));
			empDTO.setEmpTel(rs.getString("EMP_TEL"));
			empList.add(empDTO);
		}
		return empList;
	}
	
	public int totalCount(){//페이징처리:전체레코드개수
		int count=0;
		try {
			sql = "SELECT COUNT(*) FROM EMPLOYEE";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public PageTo page(int curPage) {//페이지구현
	      PageTo pageTo = new PageTo();
	      int totalCount = totalCount();
	      empList = new ArrayList<EmployeeDTO>();
	      try {
	         sql = "SELECT * FROM EMPLOYEE ORDER BY DEPT_NO DESC, EMP_RANK DESC";
	         pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	         rs = pstmt.executeQuery();
	         int perPage = pageTo.getPerPage();//5
	         int skip = (curPage-1) * perPage;
	         if(skip>0){
	            rs.absolute(skip);
	         }
	         for(int i=0;i<perPage && rs.next();i++){
	            empDTO = new EmployeeDTO();
				empDTO.setDeptNo(rs.getInt("DEPT_NO"));
				empDTO.setEmpAddr(rs.getString("EMP_ADDR"));
				empDTO.setEmpName(rs.getString("EMP_NAME"));
				empDTO.setEmpNo(rs.getInt("EMP_NO"));
				empDTO.setEmpPw(rs.getString("EMP_PW"));
				empDTO.setEmpRank(rs.getInt("EMP_RANK"));
				empDTO.setEmpSecurity(rs.getString("EMP_SECURITY"));
				empDTO.setEmpTel(rs.getString("EMP_TEL"));
				empList.add(empDTO);
	         }
	         pageTo.setList(empList);//ArrayList 저장
	         pageTo.setTotalCount(totalCount);//전체레코드개수
	         pageTo.setCurPage(curPage);//현재페이지
	   } catch (SQLException e) {
	      e.printStackTrace();
	   }
	      return pageTo;        
	   }//페이지구현
	public ResultSet idFind(String c) throws SQLException {
		sql = "select EMP_NO from EMPLOYEE where EMP_SECURITY = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, c);
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet pwFind(int empNo) throws SQLException {
		sql = "select EMP_PW from EMPLOYEE where EMP_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, empNo);
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet mypage(int empNo) throws SQLException {
		sql = "select * from EMPLOYEE where EMP_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, empNo);
		rs = pstmt.executeQuery();
		return rs;
	}
	public ResultSet empView(int empNo) throws SQLException {
		sql = "SELECT * FROM EMPLOYEE WHERE EMP_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, empNo);
		rs = pstmt.executeQuery();
		
		return rs;
	}
	public ArrayList<EmployeeDTO> empSearch(String searchEmpName) throws SQLException{
		sql = "SELECT * FROM EMPLOYEE WHERE EMP_NAME LIKE ?";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "%"+searchEmpName+"%");
		rs = pstmt.executeQuery();
		empList = new ArrayList<EmployeeDTO>();

		while(rs.next()) {    
			empDTO = new EmployeeDTO();
			empDTO.setDeptNo(rs.getInt("DEPT_NO"));
			empDTO.setEmpAddr(rs.getString("EMP_ADDR"));
			empDTO.setEmpName(rs.getString("EMP_NAME"));
			empDTO.setEmpNo(rs.getInt("EMP_NO"));
			empDTO.setEmpPw(rs.getString("EMP_PW"));
			empDTO.setEmpRank(rs.getInt("EMP_RANK"));
			empDTO.setEmpSecurity(rs.getString("EMP_SECURITY"));
			empDTO.setEmpTel(rs.getString("EMP_TEL"));
			empList.add(empDTO);
		}
		return empList;
	}
	public void empUpdate(EmployeeDTO empDTO,int empNo) throws SQLException {
		sql = "update EMPLOYEE set EMP_NO=?,EMP_NAME=?,EMP_TEL=?,EMP_ADDR=?,EMP_SECURITY=?,EMP_PW=?,DEPT_NO=? where EMP_NO =?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, empDTO.getEmpNo());
		pstmt.setString(2, empDTO.getEmpName());
		pstmt.setString(3, empDTO.getEmpTel());
		pstmt.setString(4, empDTO.getEmpAddr());
		pstmt.setString(5, empDTO.getEmpSecurity());
		pstmt.setString(6, empDTO.getEmpPw());
		pstmt.setInt(7, empDTO.getDeptNo());
		pstmt.setInt(8, empNo);
		pstmt.executeUpdate();
	}
	public EmployeeDTO empLogin(int empNo, String empPw) throws SQLException {
		sql = "SELECT * FROM EMPLOYEE WHERE EMP_NO = ? AND EMP_PW = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, empNo);
		pstmt.setString(2, empPw);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			empDTO.setDeptNo(rs.getInt("DEPT_NO"));
			empDTO.setEmpAddr(rs.getString("EMP_ADDR"));
			empDTO.setEmpName(rs.getString("EMP_NAME"));
			empDTO.setEmpNo(rs.getInt("EMP_NO"));
			empDTO.setEmpPw(rs.getString("EMP_PW"));
			empDTO.setEmpRank(rs.getInt("EMP_RANK"));
			empDTO.setEmpSecurity(rs.getString("EMP_SECURITY"));
			empDTO.setEmpTel(rs.getString("EMP_TEL"));
		}
		return empDTO;
	}
	
}
