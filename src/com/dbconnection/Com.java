package com.dbconnection;



	import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

	public class Com extends Db_connection {

		public boolean insert(String firstname,String lastname ,int phone, String email, String password) throws SQLException
		{
			PreparedStatement pt;
			 
				pt = super.conn
				.prepareStatement("insert into user(vchr_firstname, vchr_lastname,int_phone,vchr_email,vchr_password)values(?,?,?,?,?)");
				pt.setString(1,firstname);
				pt.setString(2,lastname);
				pt.setInt(3,phone);
				pt.setString(4,email);
				pt.setString(5,password);
				boolean y=pt.execute();

					System.out.println("inserted");
					return y;
			 
		
			
			
			
			}
		public ResultSet  store(String email, String password) throws SQLException
		{
		PreparedStatement pt2;
		pt2=super.conn.
		//prepareStatement("select email,password from tbl_user where email=?, password=?");
		prepareStatement("call test(?,?)");
		pt2.setString(1,email);
		pt2.setString(2,password);
		pt2.execute();
       
		    ResultSet rs = pt2.executeQuery();
		   
		    
		         
		          
		          return rs;

		}

		
		public int can( String uname, String pswd)
			{
			PreparedStatement pt1;
			try {
			pt1 = super.conn
			.prepareStatement(("call test1 (?,?)"));
			pt1.setString(1, uname);
			pt1.setString(2, pswd);
			pt1.execute();
			ResultSet rs = pt1.executeQuery();
			           while(rs.next())
			           {
			        	 String email=rs.getString("vchr_email");
			        	String password=rs.getString("vchr_password");
			            System.out.println("Email:"+ email);
			            System.out.println("Password:"+password);	
			                    
			           }
			          
			       

			System.out.println("selected");

			
						
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("not selected");
				}
				return 1;
			    
		}
		
		public static void main(String[] args)
		{
			Com db=new Com();
			System.out.println(db);;
			//db.insert("lisi","t",1234577,"lisina@gmail.com","abc123");
			db.can("abc@gmail.com","abc123");
		}
	
	
		}
	




