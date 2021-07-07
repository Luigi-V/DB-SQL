
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

//3)Determinare quanti team lavorano in un'azienda e la sede principale delle aziende (Nome, Citta, Num_Team);
public class Query3 extends JFrame {

	//Variabili d'istanza
	private JPanel contentPane;
	private String query = "SELECT A.Nome, S.Citta, count(*) AS Num_Team FROM Sede AS S JOIN (Azienda AS A JOIN Team AS T ON A.Nome= T.Azienda) ON S.Azienda = A.Nome GROUP BY A.Nome";
	private JTable table;

	//Costruttore Frame Query 3
	public Query3(Connection myConn) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1050, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Etichetta Azienda
		JLabel lblAzienda = new JLabel("| AZIENDA");
		lblAzienda.setBounds(10, 10, 130, 15);
		contentPane.add(lblAzienda);
		
		//Etichetta Cittá
		JLabel lblCitta = new JLabel("| CITTÁ");
		lblCitta.setBounds(151, 10, 130, 15);
		contentPane.add(lblCitta);
		
		//Etichetta Numero Team
		JLabel lblTeam = new JLabel("| TEAM");
		lblTeam.setBounds(292, 10, 130, 15);
		contentPane.add(lblTeam);
		
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
