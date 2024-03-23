package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Machines;
import model.Member;
import model.Trainer;
@WebServlet(urlPatterns = {"/home","/login",
		"/tList","/mList","/mmList",
		"/addTra","/tReg","/tcheck","/tModify","/tup","/tdel","/tdelete",
		"/addMem","/mReg","/memCheck","/getUpForm","/memdel","/delMem",
		"/addMach","/machReg","/mechModify","/mechUp","/mechDel","/mechDelete",
		"/blist","/mbook","/mbooking","/mbookingDone",
		"/cbook","/cbooking",
		"/seeReserv","/seeRes",
		"/logout"})
public class HomeController extends HttpServlet{
	Connection con=null;
	@Override
	public void init() throws ServletException {
		 try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gym","root","shiv@123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=req.getServletPath();
		if (url.equals("/home")) {
			req.setAttribute("msgReg", "Welcome To Home Page..");
			  req.setAttribute("back", null);
			  RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			  rd.include(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		
		String url=req.getServletPath();
		HttpSession hs=req.getSession();
		String uname=(String) req.getParameter("uname");
		String pass=(String) req.getParameter("pass");
		
		PreparedStatement ps=null;
		Statement st=null;
		ResultSet rs=null;
		
		String uRole="";
		boolean urole=false;
		if (url.equals("/login")) {
			String getRole="select urole from user_manage where uname=? AND pass=?";
			try {
				ps=con.prepareStatement(getRole);
				ps.setString(1, uname);
				ps.setString(2, pass);
				rs=ps.executeQuery();
				while (rs.next()) {
					uRole=rs.getString(1);
					urole=true;
				}
				
				if (!urole) {
					req.setAttribute("msg","u&p");
					RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
					rd.include(req, resp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (uRole.equals("admin")) {
			hs.setAttribute("op","{");
			hs.setAttribute("uname", uname);
			hs.setAttribute("cl","}");
			hs.setAttribute("urole", uRole);
			req.setAttribute("back",null);
			
			RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			rd.include(req, resp);
		}else if (uRole.equals("user")) {
			hs.setAttribute("op","{");
			hs.setAttribute("uname", uname);
			hs.setAttribute("cl","}");
			hs.setAttribute("urole", uRole);
			req.setAttribute("back",null);
			RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			rd.include(req, resp);
		}
		
		ArrayList<Trainer> tlist=new ArrayList();
		ArrayList<Member> mlist=new ArrayList();
		ArrayList<Machines> mmlist=new ArrayList();
		ArrayList<Machines> blist=new ArrayList();
		if (url.equals("/tList") && hs.getAttribute("urole").equals("admin")) {
		
			String getList="select id,tname,tcontact,tgender,tprofile from trainer";
			Trainer t;
			 try {
				ps=con.prepareStatement(getList);
				rs=ps.executeQuery();
				while (rs.next()) {
					t=new Trainer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
					tlist.add(t);
				}
				req.setAttribute("tlist", tlist);
				req.setAttribute("back", "show");
				RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
				rd.include(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (url.equals("/mList") && hs.getAttribute("urole").equals("admin")) {
			String getList="select * from member";
			Member member;
			 try {
				ps=con.prepareStatement(getList);
				rs=ps.executeQuery();
				while (rs.next()) {
					member=new Member(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
					mlist.add(member);
				}
				req.setAttribute("mlist", mlist);
				req.setAttribute("back", "show");
				RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
				rd.include(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}else if (url.equals("/mmList") && hs.getAttribute("urole").equals("admin")) {
			String getList="select * from machines";
			Machines machines;
			 try {
				ps=con.prepareStatement(getList);
				rs=ps.executeQuery();
				while (rs.next()) {
					machines=new Machines(rs.getInt(1),rs.getString(2),(rs.getInt(3)));
					mmlist.add(machines);
				}
				req.setAttribute("mmlist", mmlist);
				req.setAttribute("back", "show");
				RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
				rd.include(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		} else if (url.equals("/tList") &&  hs.getAttribute("urole").equals("user")) {

		} else if(url.equals("/addTra") && hs.getAttribute("urole").equals("admin")) {
			req.setAttribute("addTra", "addTra");
			req.setAttribute("back", "show");
			RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			rd.include(req, resp);
		}else if (url.equals("/tReg") &&  hs.getAttribute("urole").equals("admin")) {
			
			Part file=req.getPart("img");
			System.out.println("file Part : "+file);
			
			String imgFileName=file.getSubmittedFileName();
			System.out.println("img name : "+imgFileName);
			
			String uploadFileName="C:/Users/shiva/Desktop/GYM/Gym_Management/src/main/webapp/img/tProfile/"+imgFileName;
			System.out.println("upload file name : "+uploadFileName);
			
			try {
				
				FileOutputStream fos=new FileOutputStream(uploadFileName);
				InputStream is=file.getInputStream();
				
				byte[] data=new byte[is.available()];
				is.read(data);
				fos.write(data);
				fos.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
			String tins="insert into trainer(tname,tcontact,tgender,tprofile) values(?,?,?,?)";
			
			try {
				ps=con.prepareStatement(tins);
				ps.setString(1,req.getParameter("tname"));
				ps.setString(2,req.getParameter("tcon"));
				ps.setString(3,req.getParameter("tgen"));
				ps.setString(4, imgFileName);
				int n=ps.executeUpdate();
				if (n==1) {
					req.setAttribute("msgReg", ".Registration Successful.");
					  req.setAttribute("back", null);
					  RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
					  rd.include(req, resp);
				}else {
					req.setAttribute("msgReg", ".Registration Not Successful.");
					  req.setAttribute("back", null); 
					  RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
					  rd.include(req, resp);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			 
		}else if(url.equals("/tModify") && hs.getAttribute("urole").equals("admin")) {

			req.setAttribute("tmodify", "modify");
			req.setAttribute("back", "show");
			RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			rd.include(req, resp);
		
	}else if(url.equals("/tcheck") && hs.getAttribute("urole").equals("admin")) {
	String tnnam =(String) req.getParameter("tnames");
	String tcon = (String) req.getParameter("tcont");
	String tnnamDB="";
	String tconDB="";
	int id=0;
		String tcheck="select id,tname,tcontact from trainer where tname=? AND tcontact=?";
	boolean tch=false;
		 try {
			ps=con.prepareStatement(tcheck);
			ps.setString(1,tnnam);
			ps.setString(2,tcon);
			rs=ps.executeQuery();
			while (rs.next()) {
				id=rs.getInt(1);
				tnnamDB=rs.getString(2);
				tconDB=rs.getString(3);
				
			}
			if (tnnam.equals(tnnamDB) && tcon.equals(tconDB)) {
				tch=true;
				hs.setAttribute("tid",id);
				req.setAttribute("tFound", "found");
				req.setAttribute("back", "show");
				req.setAttribute("msgReg", "Data Available You Can Update New Data..");
				RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
				rd.include(req, resp);
			} if(!tch) {
				req.setAttribute("tFound",null);
				req.setAttribute("back", null);
				req.setAttribute("msgReg", "Trainer Data Not Found.");
				RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
				rd.include(req, resp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}else if(url.equals("/tup") && hs.getAttribute("urole").equals("admin")) {
	String tnnam =(String) req.getParameter("tname");
	String tcon = (String) req.getParameter("tcon");
	String tgen = (String) req.getParameter("tgen");
	hs=req.getSession(false);
	int id=Integer.parseInt(String.valueOf(hs.getAttribute("tid")));
		String tcheck="UPDATE trainer SET tname=?,tcontact=?,tgender=? WHERE id=?";
	boolean tch=false;
		 try {
			ps=con.prepareStatement(tcheck);
			ps.setString(1,tnnam);
			ps.setString(2,tcon);
			ps.setString(3,tgen);
			ps.setInt(4, id);
			int n=ps.executeUpdate();
			
			if (n==1) {
			
				req.setAttribute("back", null);
				req.setAttribute("msgReg", "Trainer data updated successfully you can check in trainer list..");
				RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
				rd.include(req, resp);
			} else {
		
				req.setAttribute("back", null);
				req.setAttribute("msgReg", "Oops, Data Not Updated.");
				RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
				rd.include(req, resp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}else if (url.equals("/tdel") &&  hs.getAttribute("urole").equals("admin")) {
		req.setAttribute("tdel", "tdel");
		req.setAttribute("back", "show");
		RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
		rd.include(req, resp);
		 
	}else if (url.equals("/tdelete") &&  hs.getAttribute("urole").equals("admin")) {
		String tnam=req.getParameter("tname");
			String tdell="DELETE FROM trainer WHERE tname=?";
			 try {
				ps=con.prepareStatement(tdell);
				ps.setString(1, tnam);
				int n=ps.executeUpdate();
				
				if (n==1) {
					req.setAttribute("back", null);
					req.setAttribute("msgReg", "Trainer data Delete successfully you can check in trainer list..");
					RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
					rd.include(req, resp);
				} if(n!=1) {
					req.setAttribute("tdel", "tdel");
					req.setAttribute("back", "show");
					req.setAttribute("msgReg", "Oops, Data Not Found Try Again.");
					RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
					rd.include(req, resp);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}else if (url.equals("/addMem") &&  hs.getAttribute("urole").equals("admin")) {
			req.setAttribute("addMem", "addTra");
			req.setAttribute("back", "show");
			RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			rd.include(req, resp);
			 
		}else if (url.equals("/mReg") &&  hs.getAttribute("urole").equals("admin")) {
			String tname="";
			String	membership="";
				tname=req.getParameter("tname");
				membership=req.getParameter("membership");
		if(tname.length()==0 && membership.length()==0)
		{
			 tname="NA";
			 membership="NA";
		}
		
		String fnam="";
		String lnam="";
		String fcon="";
		boolean zzz=false;
			try {
				String checkmem="SELECT mfistname,mlastname,mcontact FROM member WHERE mfistname=? AND mlastname=? AND mcontact=?";
				ps=con.prepareStatement(checkmem);
				ps.setString(1,req.getParameter("fname"));
				ps.setString(2,req.getParameter("lname"));
				ps.setString(3,req.getParameter("con"));
				rs=ps.executeQuery();
				while (rs.next()) {
					fnam=rs.getString(1);
					lnam=rs.getString(2);
					fcon=rs.getString(3);
					if (fnam.equals(req.getParameter("fname"))&& lnam.equals(req.getParameter("lname")) && fcon.equals(req.getParameter("con"))) {
						  zzz=true;
						  req.setAttribute("msgReg", ".You Allready Registered Please check in Member List.");
						  req.setAttribute("back", null);
						  RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
						  rd.include(req, resp); 
					}
				}
				
				if (!zzz) {
				String mins="INSERT into member(mfistname,mlastname,mgender,mcontact,mweight,mheight,mprogram,mtrainer,msession,membership) values(?,?,?,?,?,?,?,?,?,?)";
				ps=con.prepareStatement(mins);
				ps.setString(1,req.getParameter("fname"));
				ps.setString(2,req.getParameter("lname"));
				ps.setString(3,req.getParameter("mgen"));
				ps.setString(4,req.getParameter("con"));
				ps.setDouble(5,Double.parseDouble(String.valueOf(req.getParameter("w"))));
				ps.setDouble(6,Double.parseDouble(String.valueOf(req.getParameter("h"))));
				ps.setString(7,req.getParameter("program"));
				ps.setString(8,tname);
				ps.setString(9,req.getParameter("session"));
				ps.setString(10,membership);
		
				int n=ps.executeUpdate();
				if (n==1) {
					req.setAttribute("msgReg", ".Registration Successful.");
					  req.setAttribute("back", null);
					  RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
					  rd.include(req, resp);
				}else {
					req.setAttribute("msgReg", ".Registration Not Successful.");
					  req.setAttribute("back", null); 
					  RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
					  rd.include(req, resp);
				}
		      }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			 
		}else if (url.equals("/memCheck") &&  hs.getAttribute("urole").equals("admin")) {
			req.setAttribute("memCheck", "memCheck");
			req.setAttribute("back", "show");
			RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			rd.include(req, resp);
		
		}else if (url.equals("/getUpForm") &&  hs.getAttribute("urole").equals("admin")) {
			req.setAttribute("msgReg", "Welcome Back .");
			req.setAttribute("back", null);
			RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			rd.include(req, resp);
		
		}else if (url.equals("/memdel") &&  hs.getAttribute("urole").equals("admin")) {
			req.setAttribute("memdel", "memdel");
			req.setAttribute("back", "show");
			RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			rd.include(req, resp);
		
		}else if (url.equals("/delMem") &&  hs.getAttribute("urole").equals("admin")) {
			
			String delete="DELETE FROM member WHERE mfistname=? AND mlastname=? AND mcontact=?";
			
			try {
				ps=con.prepareStatement(delete);
				ps.setString(1,req.getParameter("fname"));
				ps.setString(2,req.getParameter("lname"));
				ps.setString(3,req.getParameter("con"));
			int no=	ps.executeUpdate();
			if (no==1) {
				req.setAttribute("msgReg", "Record Deleted Successful..");
				req.setAttribute("back", null);
				RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
				rd.include(req, resp);
			}else {
				req.setAttribute("msgReg", "Record Not Deleted Something Wrong..");
				req.setAttribute("back", null);
				RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
				rd.include(req, resp);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}else if (url.equals("/addMach") &&  hs.getAttribute("urole").equals("admin")) {
			req.setAttribute("addMach", "addMachine");
			req.setAttribute("back", "show");
			RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			rd.include(req, resp);
			 
		}else if (url.equals("/machReg") &&  hs.getAttribute("urole").equals("admin")) {
			
		String machName=req.getParameter("machname");
				String checkName="SELECT mname FROM machines WHERE mname=?";
				
			boolean da=false;
			try {
				ps=con.prepareStatement(checkName);
				ps.setString(1,machName);
				rs=ps.executeQuery();
				while (rs.next()) {
					if (machName.equals(rs.getString(1))) {
						req.setAttribute("msgReg", ".Ooops Data is Availables.");
						req.setAttribute("addMach", "addMachine");
						req.setAttribute("back", "show");
						RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
						rd.include(req, resp);
						da=true;
					}
				}
				if (!da) {
				String mmins="insert into machines(mname,book) values(?,?)";
				ps=con.prepareStatement(mmins);
				ps.setString(1,machName);
				ps.setInt(2, 4);
				int n=ps.executeUpdate();
				if (n==1) {
					req.setAttribute("msgReg", ".Machine Data Added Successful You Can Check in List.");
					  req.setAttribute("back", null);
					  RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
					  rd.include(req, resp);
				}else {
					req.setAttribute("msgReg", ".Ooops Data Not Added Please Try Again.");
					  req.setAttribute("back", null); 
					  RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
					  rd.include(req, resp);
				}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			 
		}else if (url.equals("/mechModify") &&  hs.getAttribute("urole").equals("admin")) {
			req.setAttribute("mechModify", "mechModify");
			req.setAttribute("back", "show");
			RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			rd.include(req, resp);
			 
		}else if (url.equals("/mechUp") &&  hs.getAttribute("urole").equals("admin")) {
			
		String machName=req.getParameter("machname");
				String checkName="SELECT id,mname FROM machines WHERE mname=?";
				
			boolean da=false;
			try {
				ps=con.prepareStatement(checkName);
				ps.setString(1,machName);
				rs=ps.executeQuery();
				while (rs.next()) {
					hs.setAttribute("mechId",rs.getInt(1));
					if (machName.equals(rs.getString(2))) {
						req.setAttribute("msgReg", "Data is Availables You Can Update.");
						req.setAttribute("mechModify", "mechModify");
						req.setAttribute("back", "show");
						RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
						rd.include(req, resp);
						da=true;
					}
				}
				if (!da) {
					hs=req.getSession(false);
					int mechid=Integer.parseInt(String.valueOf(hs.getAttribute("mechId")));
				String mmup="UPDATE machines SET mname=? WHERE id=?";
				ps=con.prepareStatement(mmup);
				ps.setString(1,machName);
				ps.setInt(2,mechid);
				int n=ps.executeUpdate();
				if (n==1) {
					req.setAttribute("msgReg", ".Machine Data Updated Successful You Can Check in List.");
					  req.setAttribute("back", null);
					  RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
					  rd.include(req, resp);
				}else {
					req.setAttribute("msgReg", ".Ooops Data Not Available Please Try Again.");
					req.setAttribute("mechModify", "mechModify");
					req.setAttribute("back", "show");
					  RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
					  rd.include(req, resp);
				}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			 
		}else if (url.equals("/mechDel") &&  hs.getAttribute("urole").equals("admin")) {
			req.setAttribute("mechDel", "mechDel");
			req.setAttribute("back", "show");
			RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			rd.include(req, resp);
			 
		}else if (url.equals("/mechDelete") &&  hs.getAttribute("urole").equals("admin")) {
		int mid=Integer.parseInt(String.valueOf(req.getParameter("machId")));
			String mdell="DELETE FROM machines WHERE id=?";
			 try {
				ps=con.prepareStatement(mdell);
				ps.setInt(1, mid);
				int n=ps.executeUpdate();
				if (n==1) {
					req.setAttribute("back", null);
					req.setAttribute("msgReg", "Machine data Delete successfully you can check in Machine list..");
					RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
					rd.include(req, resp);
				} if(n!=1) {
					req.setAttribute("mechDel", "mechDel");
					req.setAttribute("back", "show");
					req.setAttribute("msgReg", "Oops, Invalid ID, Data Not Found Try Again.");
					RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
					rd.include(req, resp);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}else if (url.equals("/blist") && hs.getAttribute("urole").equals("admin")) {
		String getbList="select * from machine_booking";
		Machines machines;
		 try {
			ps=con.prepareStatement(getbList);
			rs=ps.executeQuery();
			while (rs.next()) {
				machines=new Machines(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				blist.add(machines);
			}
			req.setAttribute("blist", blist);
			req.setAttribute("back", "show");
			RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			rd.include(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	} else if(url.equals("/mbook") && hs.getAttribute("urole").equals("admin")) {
		req.setAttribute("mbook", "mbook");
		req.setAttribute("back", "show");
		RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
		rd.include(req, resp);
	}else if(url.equals("/mbooking") && hs.getAttribute("urole").equals("admin")|| hs.getAttribute("urole").equals("user")) {
		String mmna=req.getParameter("mname");
		String checkName="SELECT id,mname,book FROM machines WHERE mname=?";
		
	boolean da=false;
	try {
		ps=con.prepareStatement(checkName);
		ps.setString(1, req.getParameter("mname"));
		rs=ps.executeQuery();
		while (rs.next()) {
			if (mmna.equals(rs.getString(2))) {
				if (rs.getInt(3)>0 && rs.getInt(3)<=4) {
					hs.setAttribute("machName",rs.getString(2));
					hs.setAttribute("mechBook", rs.getInt(3));
					req.setAttribute("msgReg", " Data is Availables.");
					req.setAttribute("mbookingForm", "mbookingForm");
					req.setAttribute("back", "show");
					RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
					rd.include(req, resp);
					da=true;
					break;
				}else {
					req.setAttribute("msgReg", ".Ooops Machine Booking`s Not Available, Please Try Again.");
					req.setAttribute("mbook", "mbook");
					req.setAttribute("back", "show"); 
					  RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
					  rd.include(req, resp);
					  da=true;
				}
			}
		}
		if (!da) {
		
			req.setAttribute("msgReg", ".Ooops Machine Data Not Found Please Try Again.");
			req.setAttribute("mbook", "mbook");
			req.setAttribute("back", "show"); 
			  RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			  rd.include(req, resp);
		
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	 
		
	}else if(url.equals("/mbookingDone") && hs.getAttribute("urole").equals("admin")) {
		
		hs=req.getSession(false);
		String session=req.getParameter("session");
		String mname=(String)hs.getAttribute("machName");
		String name=(String)hs.getAttribute("uname");
		int avbook=Integer.parseInt(String.valueOf(hs.getAttribute("mechBook")));
		boolean nFound=false;
		String checkuser="SELECT membername,machinename,session from machine_booking WHERE session=? AND membername=? AND machinename=?";
		
		String memNameDB="";
		String mecNameDB="";
		String sesDB="";
		
		try {
		
			ps=con.prepareStatement(checkuser);
			ps.setString(1, session);
			ps.setString(2, name);
			ps.setString(3, mname);
			rs=ps.executeQuery();
			while (rs.next()) {
						memNameDB=rs.getString(1);
						mecNameDB=rs.getString(2);
						sesDB=rs.getString(3);
						if (name.equals(memNameDB)&& mname.equals(mecNameDB)&& session.equals(sesDB)) 
						{
							req.setAttribute("msgReg", "You are allready Booking`s.");
							req.setAttribute("back", null);
							RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
							rd.include(req, resp);
							nFound=true;
						}
					}
			
			if (!nFound) {
				String book="INSERT INTO machine_booking(machinename,membername,session) VALUES(?,?,?)";
				try {
					ps=con.prepareStatement(book);
					
					ps.setString(1, mname);
					ps.setString(2, name);
					ps.setString(3, session);
					int nn=ps.executeUpdate();
					
					if (nn==1) {
						String upmachines="UPDATE machines SET book=? WHERE mname=?";
						
						try {
						ps=con.prepareStatement(upmachines);
						ps.setInt(1, (avbook-1));
						ps.setString(2, mname);
					int n=	ps.executeUpdate();
					if (n==1) {
						req.setAttribute("msgReg", "Booking Successful, You Can Check In Booking Details.");
						req.setAttribute("back",null);
						RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
						rd.include(req, resp);
					}
						
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} else if(url.equals("/cbook") && hs.getAttribute("urole").equals("admin")) {
		req.setAttribute("cbook", "cbook");
		req.setAttribute("back", "show");
		RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
		rd.include(req, resp);
	}else if(url.equals("/cbooking") && hs.getAttribute("urole").equals("admin")) {
		int mid=Integer.parseInt(String.valueOf(req.getParameter("cid")));
		String una=(String)hs.getAttribute("uname");
		String checkName="SELECT id,membername,machinename FROM machine_booking WHERE id=?";
		String mnam="";
		
	boolean da=false;
	try {
		ps=con.prepareStatement(checkName);
		ps.setInt(1, mid);
		rs=ps.executeQuery();
		while (rs.next()) 
		{
			if (mid==rs.getInt(1)&& una.equals(rs.getString(2))) {
				mnam=rs.getString(3);
				String can="DELETE FROM machine_booking WHERE id=?";
				ps=con.prepareStatement(can);
				ps.setInt(1, mid);
				int ns=ps.executeUpdate();
				if (ns==1) {
					String mn="";
					int bc=0;
					String se="SELECT mname,book FROM machines WHERE mname=?";
					ps=con.prepareStatement(se);
					ps.setString(1, mnam);
					rs=ps.executeQuery();
					while (rs.next()) {
						mn=rs.getString(1);
						bc=rs.getInt(2);
						
					}
					if (mnam.equals(mn)) {
						String up="UPDATE machines SET book=? WHERE mname=? ";
						ps=con.prepareStatement(up);
						ps.setInt(1, (bc+1));
						ps.setString(2, mn);
						int k=ps.executeUpdate();
						if (k==1) {
							req.setAttribute("msgReg", " Machine Booking Cancel Successful..");
							req.setAttribute("back",null); 
							  RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
							  rd.include(req, resp);
							  da=true;
						}
					}
				}
			}else {
				 da=true;
				 req.setAttribute("msgReg", "You have No Permission to Cancel Bookings of Other Members.");
				req.setAttribute("cbook", "cbook");
				req.setAttribute("back", "show"); 
				RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
					rd.include(req, resp);
			}
		}
		
		if (!da) {
		
			req.setAttribute("msgReg", ".Ooops Machine Data Not Found Please Try Again.");
			req.setAttribute("cbook", "cbook");
			req.setAttribute("back", "show"); 
			  RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			  rd.include(req, resp);
		
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	 
		
	}else if(url.equals("/seeReserv") && hs.getAttribute("urole").equals("admin")) {
		req.setAttribute("seeReserv", "seeReserv");
		req.setAttribute("back", "show");
		RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
		rd.include(req, resp);
	}else if(url.equals("/seeRes") && hs.getAttribute("urole").equals("admin")) {
		
		ArrayList<Machines> seeList=new ArrayList<Machines>();
		Machines m;
		boolean li=false;
		String see="SELECT * FROM machine_booking WHERE session=?";
		
		try {
			ps=con.prepareStatement(see);
			ps.setString(1, (String)req.getParameter("seeRes"));
			rs=ps.executeQuery();
			while (rs.next()) {
				li=true;
				m=new Machines(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				seeList.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!li) {
			req.setAttribute("msgReg", "Reservation Not Found Try Again..");
			req.setAttribute("back", "show");
			RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			rd.include(req, resp);
			
		}
		if (li) {
			req.setAttribute("msgReg", "Reservation Found, Check Anohter..");
			req.setAttribute("seeList", seeList);
			req.setAttribute("back", "show");
			RequestDispatcher rd=req.getRequestDispatcher("adminHome.jsp");
			rd.include(req, resp);
		}
	} else if(url.equals("/logout") && hs.getAttribute("urole").equals("admin")) {
			 hs=req.getSession(false);
			hs.invalidate();
			req.setAttribute("msgReg", ".Logout Successful.");
			RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
			rd.include(req, resp);
		}else if(url.equals("/logout") && hs.getAttribute("urole").equals("user")) {
			 hs=req.getSession(false);
			hs.invalidate();
			req.setAttribute("msgReg", ".Logout Successful.");
			RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
			rd.include(req, resp);
		}  
	}
		
}



