import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Ongoing_Trip extends JFrame {

	private JPanel contentPane;
	JLabel lblWaittime;
	JLabel lblDriverrating;
	JLabel lblDrivername;
	JLabel lblDrivercontact;
	JLabel lblFrom;
	JLabel lbl_to;
	JLabel lbl_driver_loc;
	JButton btnNewButton;
	
	static String str_user,str2,str6,pick_point,drop_point,dri_name;
	static String fare_sting;
	static int new_balance;
	JLabel lblVechileId;
	JLabel lbl_vechile_id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ongoing_Trip frame = new Ongoing_Trip();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	public Ongoing_Trip(String str_main,String pup,String dop,String driver_name)
	{
		str_user = str_main;
		pick_point = pup;
		drop_point = dop;
		dri_name = driver_name;
		
		try {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cab","root","vinay18");
		Statement stmt =  conn.createStatement();
		String sql = "Select * from users where user_id=" + str_user;
		ResultSet rs = stmt.executeQuery(sql); 
		if(rs.next())
		{
			System.out.println("confirm trip page user id :" + str_user + " "+  rs.getString(6) + " " + pup + " " + dop);
			str6 = rs.getString(6);
			str2 = rs.getString(2);
		}
		else
		{
			System.out.println("Some user error");
		}
		conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update_balance()
	{
		try {
			Connection conn = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cab","root","vinay18");
			Statement stmt =  conn.createStatement();
			String sql = "Select * from points where pick_point='"+ pick_point +"' and drop_point='"+ drop_point +"'";
			ResultSet rs2 = stmt.executeQuery(sql); 
			if(rs2.next())
			{
				fare_sting = rs2.getString(4);
			}
			else
			{
				System.out.println("Some error");
			}
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		int x=0;
                if(fare_sting!=null && !fare_sting.isEmpty())
                {
                    x=Integer.parseInt(fare_sting);
                }
		
                int bal=0;
		 if(str6!=null && !str6.isEmpty())
                    {
                           bal=Integer.parseInt(str6);
                    }
		int new_balance = bal -  x;
		
		
		Connection connec = null;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/cab","root","vinay18");
			Statement stmt =  connec.createStatement();
			String sql = "update users " + " set balance = '" + new_balance +"' " +"where user_id='"+ str_user +"' "; 		
			stmt.executeUpdate(sql);
			System.out.println("Update done");
			connec.close();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
		System.out.println(new_balance);
		
		
		System.out.println(str_user + "  " +pick_point + "  " + str6 +"  " + drop_point + "  " +dri_name);
	}
	
	public void update_driver()
	{
		Connection conn = null;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cab","root","vinay18");
			Statement stmt =  conn.createStatement();
			String sql = "update drivers " + " set def_location = '" + drop_point +"' ,status= '" + '0' +"' " +"where name='"+ dri_name +"' "; 		
			stmt.executeUpdate(sql);
			System.out.println("Update done");
			conn.close();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	public void driver_distribution()
	{
		int count=0;
		int num_34=0,num_58=0,num_20=0,num_9=0,num_45=0,num_15=0,num_25=0,num_main=0,num_law=0,num_bh4=0,num_bh6=0;
		int arr[] = null;
		try {
			Connection conn = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cab","root","vinay18");
			Statement stmt =  conn.createStatement();
			String sql = "Select * from drivers where def_location='"+ drop_point +"' ";
			ResultSet rs2 = stmt.executeQuery(sql); 
			while(rs2.next())
			{
				count++;
			}
			System.out.println("The number is " + count);
			if(count > 4)
			{
				System.out.println("in the count >4");
				String sql7 = "Select * from drivers where def_location='"+ "block 34" +"' ";
				ResultSet rs7 = stmt.executeQuery(sql7); 
				while(rs7.next())
				{
					num_34++;
				}
				
				String sql8 = "Select * from drivers where def_location='"+ "block 58" +"' ";
				ResultSet rs8 = stmt.executeQuery(sql8); 
				while(rs8.next())
				{
					num_58++;
				}
				
				String sql9 = "Select * from drivers where def_location='"+ "block 20" +"' ";
				ResultSet rs9 = stmt.executeQuery(sql9); 
				while(rs9.next())
				{
					num_20++;
				}
				
				
				String sql6 = "Select * from drivers where def_location='"+ "block 9" +"' ";
				ResultSet rs6 = stmt.executeQuery(sql6); 
				while(rs6.next())
				{
					num_9++;
				}
				
				String sql5 = "Select * from drivers where def_location='"+ "block 45" +"' ";
				ResultSet rs5 = stmt.executeQuery(sql5); 
				while(rs5.next())
				{
					num_45++;
				}
                                String sql20 = "Select * from drivers where def_location='"+ "block 15" +"' ";
				ResultSet rs20 = stmt.executeQuery(sql20); 
				while(rs5.next())
				{
					num_15++;
				}
                                String sql21 = "Select * from drivers where def_location='"+ "block 45" +"' ";
				ResultSet rs21 = stmt.executeQuery(sql21); 
				while(rs5.next())
				{
					num_45++;
				}
                                String sql22 = "Select * from drivers where def_location='"+ "block 25" +"' ";
				ResultSet rs22 = stmt.executeQuery(sql22); 
				while(rs5.next())
				{
					num_25++;
				}
                                String sql23 = "Select * from drivers where def_location='"+ "main gate" +"' ";
				ResultSet rs23 = stmt.executeQuery(sql23); 
				while(rs5.next())
				{
					num_main++;
				}
                                String sql24 = "Select * from drivers where def_location='"+ "law gate" +"' ";
				ResultSet rs24 = stmt.executeQuery(sql24); 
				while(rs5.next())
				{
					num_law++;
				}
                                String sql25 = "Select * from drivers where def_location='"+ "BH-4" +"' ";
				ResultSet rs25 = stmt.executeQuery(sql25); 
				while(rs5.next())
				{
					num_bh4++;
				}
                                String sql26 = "Select * from drivers where def_location='"+ "BH-6" +"' ";
				ResultSet rs26 = stmt.executeQuery(sql26); 
				while(rs5.next())
				{
					num_bh6++;
				}
				
				System.out.println("starting if");
				
				if(num_34<=num_58&&num_34<=num_20&&num_34<=num_9&&num_34<=num_45&&num_34<=num_15&&num_34<=num_25&&num_34<=num_main&&num_34<=num_law&&num_34<=num_bh4&&num_34<=num_bh6 )
				{
					String sql_kom = "update drivers " + " set def_location = '" + "block 34" +"' " +"where name='"+ dri_name +"' "; 	
					stmt.executeUpdate(sql_kom);
				}
				else if(num_58<=num_34&&num_58<=num_20&&num_58<=num_9&&num_58<=num_45&&num_58<=num_15&&num_58<=num_25&&num_58<=num_main&&num_58<=num_law&&num_58<=num_bh4&&num_58<=num_bh6 )
				{
					String sql_kom = "update drivers " + " set def_location = '" + "block 58" +"' " +"where name='"+ dri_name +"' "; 	
					stmt.executeUpdate(sql_kom);
				}
				else if(num_20<=num_34&&num_20<=num_58&&num_20<=num_9&&num_20<=num_45&&num_20<=num_15&&num_20<=num_25&&num_20<=num_main&&num_20<=num_law&&num_20<=num_bh4&&num_20<=num_bh6 )
				{
					String sql_kom = "update drivers " + " set def_location = '" + "block 20" +"' " +"where name='"+ dri_name +"' "; 	
					stmt.executeUpdate(sql_kom);
				}
				else if(num_9<=num_34&&num_9<=num_58&&num_9<=num_20&&num_9<=num_45&&num_9<=num_15&&num_9<=num_25&&num_9<=num_main&&num_9<=num_law&&num_9<=num_bh4&&num_9<=num_bh6 )
				{
					String sql_kom = "update drivers " + " set def_location = '" + "block 9" +"' " +"where name='"+ dri_name +"' "; 	
					stmt.executeUpdate(sql_kom);
				}
                                else if(num_45<=num_34&&num_45<=num_58&&num_45<=num_20&&num_45<=num_9&&num_45<=num_15&&num_45<=num_25&&num_45<=num_main&&num_45<=num_law&&num_45<=num_bh4&&num_45<=num_bh6 )
				{
					String sql_kom = "update drivers " + " set def_location = '" + "block 45" +"' " +"where name='"+ dri_name +"' "; 	
					stmt.executeUpdate(sql_kom);
				}
                                else if(num_15<=num_34&&num_15<=num_58&&num_15<=num_20&&num_15<=num_9&&num_15<=num_45&&num_15<=num_25&&num_15<=num_main&&num_15<=num_law&&num_15<=num_bh4&&num_15<=num_bh6 )
				{
					String sql_kom = "update drivers " + " set def_location = '" + "block 15" +"' " +"where name='"+ dri_name +"' "; 	
					stmt.executeUpdate(sql_kom);
				}
                                else if(num_25<=num_34&&num_25<=num_58&&num_25<=num_20&&num_25<=num_9&&num_25<=num_45&&num_25<=num_15&&num_25<=num_main&&num_25<=num_law&&num_25<=num_bh4&&num_25<=num_bh6 )
				{
					String sql_kom = "update drivers " + " set def_location = '" + "block 25" +"' " +"where name='"+ dri_name +"' "; 	
					stmt.executeUpdate(sql_kom);
				}
                                else if(num_main<=num_34&&num_main<=num_58&&num_main<=num_20&&num_main<=num_9&&num_main<=num_45&&num_main<=num_15&&num_main<=num_25&&num_main<=num_law&&num_main<=num_bh4&&num_main<=num_bh6 )
				{
					String sql_kom = "update drivers " + " set def_location = '" + "main gate" +"' " +"where name='"+ dri_name +"' "; 	
					stmt.executeUpdate(sql_kom);
				}
                                else if(num_law<=num_34&&num_law<=num_58&&num_law<=num_20&&num_law<=num_9&&num_law<=num_45&&num_law<=num_15&&num_law<=num_25&&num_law<=num_main&&num_law<=num_bh4&&num_law<=num_bh6 )
				{
					String sql_kom = "update drivers " + " set def_location = '" + "law gate" +"' " +"where name='"+ dri_name +"' "; 	
					stmt.executeUpdate(sql_kom);
				}
                                else if(num_bh4<=num_34&&num_bh4<=num_58&&num_bh4<=num_20&&num_bh4<=num_9&&num_bh4<=num_45&&num_bh4<=num_15&&num_bh4<=num_25&&num_bh4<=num_main&&num_bh4<=num_law&&num_bh4<=num_bh6 )
				{
					String sql_kom = "update drivers " + " set def_location = '" + "BH-4" +"' " +"where name='"+ dri_name +"' "; 	
					stmt.executeUpdate(sql_kom);
				}
                                else if(num_bh6<=num_34&&num_bh6<=num_58&&num_bh6<=num_20&&num_bh6<=num_9&&num_bh6<=num_45&&num_bh6<=num_15&&num_bh6<=num_25&&num_bh6<=num_main&&num_bh6<=num_law&&num_bh6<=num_bh4 )
				{
					String sql_kom = "update drivers " + " set def_location = '" + "BH-6" +"' " +"where name='"+ dri_name +"' "; 	
					stmt.executeUpdate(sql_kom);
				}
	
				
				
				
			}
			
			

			System.out.println(num_34 + "  " + num_58 +"  "+ num_20 +"  " + num_9+"  "+ num_45+" "+num_15+" "+num_25+" "+num_main+" "+num_law+" "+num_bh4+" "+num_bh6);
			
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	
	public Ongoing_Trip() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 497);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		lblFrom = new JLabel("FROM");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblFrom.setBounds(27, 26, 146, 35);
		contentPane.add(lblFrom);
		
		lbl_to = new JLabel("TO");
		lbl_to.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lbl_to.setBounds(233, 26, 158, 35);
		contentPane.add(lbl_to);
		
		JLabel lblName = new JLabel("Name ");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblName.setBounds(62, 121, 99, 35);
		contentPane.add(lblName);
		
		JLabel lblTo = new JLabel(">>");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblTo.setBounds(185, 27, 36, 35);
		contentPane.add(lblTo);
		
		JLabel lblDriverDetails = new JLabel("Driver Details");
		lblDriverDetails.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblDriverDetails.setBounds(117, 73, 158, 35);
		contentPane.add(lblDriverDetails);
		
		JLabel lblCotact = new JLabel("Contact");
		lblCotact.setForeground(Color.WHITE);
		lblCotact.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblCotact.setBounds(62, 158, 99, 35);
		contentPane.add(lblCotact);
		
		JLabel lblRating = new JLabel("Rating");
		lblRating.setForeground(Color.WHITE);
		lblRating.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblRating.setBounds(62, 231, 99, 35);
		contentPane.add(lblRating);
		
		lblDrivername = new JLabel("driver-name");
		lblDrivername.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblDrivername.setBounds(191, 121, 158, 35);
		contentPane.add(lblDrivername);
		
		lblDrivercontact = new JLabel("driver-contact");
		lblDrivercontact.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblDrivercontact.setBounds(191, 158, 158, 35);
		contentPane.add(lblDrivercontact);
		
		lblDriverrating = new JLabel("driver-rating");
		lblDriverrating.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblDriverrating.setBounds(191, 231, 158, 35);
		contentPane.add(lblDriverrating);
		
		JLabel lblWaitTime = new JLabel("Wait Time");
		lblWaitTime.setForeground(Color.WHITE);
		lblWaitTime.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblWaitTime.setBounds(62, 264, 99, 35);
		contentPane.add(lblWaitTime);
		
		lblWaittime = new JLabel("1 min ");
		lblWaittime.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblWaittime.setBounds(191, 264, 158, 35);
		contentPane.add(lblWaittime);
		
		JLabel lblDriverLocation = new JLabel("Driver At");
		lblDriverLocation.setForeground(Color.WHITE);
		lblDriverLocation.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblDriverLocation.setBounds(62, 297, 99, 35);
		contentPane.add(lblDriverLocation);
		
		lbl_driver_loc = new JLabel("driver-location");
		lbl_driver_loc.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lbl_driver_loc.setBounds(191, 297, 158, 35);
		contentPane.add(lbl_driver_loc);
		
		btnNewButton = new JButton("Done Trip");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection conn = null;
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cab","root","vinay18");
					Statement stmt =  conn.createStatement();
					String sql = "update users " + " set status = '" + 0 +"' " +"where user_id='"+ str_user +"' "; 		
					stmt.executeUpdate(sql);
					System.out.println("Update done");
					conn.close();
				} 
				catch (Exception e) 
				{
					System.out.println(e);
				}
				
				update_balance();
				update_driver();
				driver_distribution();
				Rating_Driver rd = new Rating_Driver();
				Rating_Driver rd2 = new Rating_Driver(str_user,pick_point,drop_point,dri_name,new_balance);
				rd.driv_name.setText(dri_name);
				rd.setVisible(true);
				dispose();

			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnNewButton.setBounds(117, 357, 130, 44);
		contentPane.add(btnNewButton);
		
		lblVechileId = new JLabel("Vehicle Id");
		lblVechileId.setForeground(Color.WHITE);
		lblVechileId.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblVechileId.setBounds(62, 194, 99, 35);
		contentPane.add(lblVechileId);
		
		lbl_vechile_id = new JLabel("vehicle-id");
		lbl_vechile_id.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lbl_vechile_id.setBounds(191, 194, 158, 35);
		contentPane.add(lbl_vechile_id);

	}

}
