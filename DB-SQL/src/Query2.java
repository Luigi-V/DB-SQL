
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

//2)Elencare per ogni team il numero di progettisti appartenenti (ID, Nome, Num_Progettisti);
public class Query2 extends JFrame {

	//Variabili d'istanza
	private JPanel contentPane;
	private String query = "SELECT T.ID, T.Nome, count(*) AS Num_Progettisti FROM Team AS T JOIN Progettista AS P ON T.ID= P.Team GROUP BY T.Nome";
	private JTable table;

	//Costruttore Frame Query 2
	public Query2(Connection myConn) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1050, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Etichetta ID
		JLabel lblID = new JLabel("| ID");
		lblID.setBounds(10, 10, 130, 15);
		contentPane.add(lblID);
		
		//Etichetta Nome Team
		JLabel lblTeam = new JLabel("| TEAM");
		lblTeam.setBounds(151, 10, 130, 15);
		contentPane.add(lblTeam);
		
		//Etichetta Numero Progettisti
		JLabel lblProgettisti = new JLabel("| PROGETTISTI");
		lblProgettisti.setBounds(292, 10, 130, 15);
		contentPane.add(lblProgettisti);
		
		//Tabella dei valori
		table = new JTable();
		table.setBounds(10, 30, 425, 230);
		contentPane.add(table);
		
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
