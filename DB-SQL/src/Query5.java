
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;

//5)Individuare il Team che ha progettato il maggior numero di giochi (ID, Nome, Max_Giochi);
public class Query5 extends JFrame {

	//Variabili d'istanza
	private JPanel contentPane;
	private String query = "SELECT * FROM Temp WHERE Num_Giochi= (SELECT Max(Num_Giochi) FROM Temp)";
	private JTable table;

	//Costruttore Frame Query 5
	public Query5(Connection myConn) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1050, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Tabella dei valori
		table = new JTable();
		table.setBounds(10, 30, 425, 230);
		contentPane.add(table);
		
		//Etichetta ID
		JLabel lblID = new JLabel("| ID");
		lblID.setBounds(10, 10, 130, 15);
		contentPane.add(lblID);
		
		//Etichetta Cittá
		JLabel lblTeam = new JLabel("| TEAM");
		lblTeam.setBounds(151, 10, 130, 15);
		contentPane.add(lblTeam);
		
		//Etichetta Massimo Numero Gioco
		JLabel lblGiochi = new JLabel("| GIOCHI");
		lblGiochi.setBounds(292, 10, 130, 15);
		contentPane.add(lblGiochi);
		
		//Esecuzione Query
		try {
			//Dichiarazione della query
			PreparedStatement myStmt = myConn.prepareStatement(query);
			ResultSet myRs = myStmt.executeQuery();
			//Stampa del risultato in tabella
			table.setModel(DbUtils.resultSetToTableModel(myRs));
			}
			catch(Exception exc){
				exc.printStackTrace();
		}
	}
}
