package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lexinsmart.model.Companyprivilege;
import com.lexinsmart.utils.DBCP;

public class CompanyPrivilegeDao {
	Connection connection = null;
	PreparedStatement ptmt = null;
	DBCP dbcp =  DBCP.getInstance();
	public CompanyPrivilegeDao(Connection connection) {
		this.connection = connection;
		
	}
	public void addCompanyPrivilege(Companyprivilege companyprivilege){
		try {
			String sql = "insert into company_privilege "+
					" (egid,cid) "+
					" values(?,?) ";
			
			ptmt = connection.prepareStatement(sql);
			ptmt.setInt(1, companyprivilege.getEgid());
			ptmt.setInt(2, companyprivilege.getCid());
			ptmt.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public int getIdIfExist(int cid,int egid){
		int id=-1;
		try {
			String sql = "select id from company_privilege where cid=? and egid=? ";
			ptmt = connection.prepareStatement(sql);
			ptmt.setInt(1, cid);
			ptmt.setInt(2, egid);
			
			ResultSet rs = ptmt.executeQuery();
			rs.last();
			if (rs.getRow()>0) {
				rs.beforeFirst();
				while(rs.next()){
					id = rs.getInt("id");
				}
			} else{
				id=0;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return id;
		
	}
	public void delete(int id){
		try {
			String sql ="delete from company_privilege where id="+id;
			ptmt=connection.prepareStatement(sql);
			ptmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
