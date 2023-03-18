/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.management.system;



import java.awt.BorderLayout;
import java.awt.*;

import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Report extends JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JPanel contentPane;
        private JLabel lblId, lblNewLabel, lblGender, lblCountry, lblRoom, lblStatus, checkout, lblNewLabel_1;
	private JTable table;
        JComboBox c1;
        static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report frame = new Report();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void close()
	{
		this.dispose();
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Report() throws SQLException {
		//conn = Javaconnect.getDBConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 200, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPickUpService = new JLabel("Customer Report");
		lblPickUpService.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPickUpService.setBounds(90, 11, 158, 25);
		contentPane.add(lblPickUpService);
		
		JLabel lblTypeOfCar = new JLabel("Range");
		lblTypeOfCar.setBounds(32, 97, 89, 14);
		contentPane.add(lblTypeOfCar);

		String course[] = {"1 Week","1 Month","3 Month","6 Month","1 Year","Complete"};
                c1 = new JComboBox(course);
                c1.setBackground(Color.WHITE);
                c1.setBounds(123, 94, 150, 25);
                add(c1);
		
//		JLabel lblInfo = new JLabel("Name");
//		lblInfo.setBounds(24, 208, 46, 14);
//		contentPane.add(lblInfo);
		
		JButton btnRegister = new JButton("Display");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

                            
                Calendar now = Calendar.getInstance();
                
                // String endDate = sdf.format(now.getTime());
                now.set(Calendar.HOUR_OF_DAY, 23);
                now.set(Calendar.MINUTE, 59);
                now.set(Calendar.SECOND, 59);
                String endDate = sdf.format(now.getTime());
                
                now.add(Calendar.DAY_OF_MONTH, -7);
                now.set(Calendar.HOUR_OF_DAY, 0);
                now.set(Calendar.MINUTE, 0);
                now.set(Calendar.SECOND, 0);
                String strtDate = sdf.format(now.getTime());
                
                System.out.println(strtDate);
                System.out.println(endDate);
              
				String SQL = "select * from customer where is_delete = 1 AND timeStampCol BETWEEN '" + strtDate + "' AND '" + endDate + "';";
				try{
					conn c = new conn();
					rs = c.s.executeQuery(SQL);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
					
				}catch (SQLException ss)
				{
					ss.printStackTrace();
				}
				
				
			}
		});
		btnRegister.setBounds(200, 500, 120, 30);
                btnRegister.setBackground(Color.BLACK);
                btnRegister.setForeground(Color.WHITE);
		contentPane.add(btnRegister);
		
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Reception().setVisible(true);
                                setVisible(false);
			}
		});
		btnExit.setBounds(420, 500, 120, 30);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
		
                table = new JTable();
                // table.setBounds(0, 200, 1200, 600);
		table.setBounds(2, 233, 1000, 250);
		contentPane.add(table);
                
		lblId = new JLabel("ID");
                lblId.setBounds(24, 208, 46, 14);
                contentPane.add(lblId);

                JLabel l1 = new JLabel("Number");
                l1.setBounds(125, 208, 46, 14);
                contentPane.add(l1);

                lblNewLabel = new JLabel("Name");
                lblNewLabel.setBounds(225, 208, 80, 14);
                contentPane.add(lblNewLabel);

                lblGender = new JLabel("Gender");
                lblGender.setBounds(325, 208, 105, 14);
                contentPane.add(lblGender);

                lblCountry = new JLabel("Country");
                lblCountry.setBounds(400, 208, 73, 14);
                contentPane.add(lblCountry);

                lblRoom = new JLabel("Room");
                lblRoom.setBounds(500, 208, 73, 14);
                contentPane.add(lblRoom);

                lblStatus = new JLabel("Check-in Status");
                lblStatus.setBounds(600, 208, 100, 14);
                contentPane.add(lblStatus);

                lblNewLabel_1 = new JLabel("Deposit");
                lblNewLabel_1.setBounds(720, 208, 73, 14);
                contentPane.add(lblNewLabel_1);
                
                lblNewLabel_1 = new JLabel("Is CheckOut");
                lblNewLabel_1.setBounds(800, 208, 73, 14);
                contentPane.add(lblNewLabel_1);
                
                lblNewLabel_1 = new JLabel("CheckIn DateTime");
                lblNewLabel_1.setBounds(900, 208, 120, 14);
                contentPane.add(lblNewLabel_1);
                
                // table.getColumnModel().getColumn(9).setPreferredWidth(120);
                
                getContentPane().setBackground(Color.WHITE);
	}
}