import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

public class HighScore extends JFrame implements ActionListener {

	JFrame frame;
	JButton submit, add;
	JScrollPane sTable;
	static JTable table = new JTable();
	JPanel panel = new JPanel();
	JTextField textField = new JTextField();
	DefaultTableModel model;

	HighScore() {

		frame = new JFrame();
		Object[] column = { "NR", "NICK", "LVL", "GOLD", "POINTS" };
		Object[] row = new Object[5];
		model = new DefaultTableModel();

		this.setTitle("High Scores");
		this.getContentPane().setBackground(new Color(0, 0, 0));
		this.getContentPane().setForeground(Color.WHITE);
		this.getContentPane().setLayout(null);
		this.setBounds(0, 0, 600, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		model.setColumnIdentifiers(column);
		table.setModel(model);
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);

		submit = new JButton("Submit");
		submit.setFocusable(false);
		submit.setBounds(0, 700, 100, 25);
		submit.addActionListener(this);
		sTable = new JScrollPane(table);
		sTable.setBounds(0, 0, 600, 600);
		this.getContentPane().add(sTable);

		add = new JButton("add");
		add.setBounds(0, 650, 100, 25);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.addRow(row);
			}
		});

		this.add(add);
		this.add(submit);
		this.setResizable(false);

		// this.pack();
		this.setVisible(true);

	}

	public static void createTable() {
		try {
			Connection conn = getConnection();
			PreparedStatement query = conn.prepareStatement(
					"CREATE TABLE Game(Nr int PRIMARY KEY, Nick varchar(50),LVL int,GOLD int,Points int)");
			query.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Stworzono Tabele");
		}
	}

	public static void readAndPost() {
		try {
			Connection conn = getConnection();
			PreparedStatement posted = conn
					.prepareStatement("COPY Game FROM 'C:\\Users\\user\\Desktop\\output.csv' DELIMITER ',' CSV HEADER;");
			posted.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void readFromDB() {
		try {
			Connection conn = getConnection();
			PreparedStatement read = conn.prepareStatement("Select * FROM Game");
			ResultSet rs = read.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			String url = "jdbc:postgresql://localhost:5432/SDA89";
			String username = "postgres";
			String password = "..";
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Po³¹czono");
			return conn;

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			readAndPost();
			readFromDB();
		}

	}
}
