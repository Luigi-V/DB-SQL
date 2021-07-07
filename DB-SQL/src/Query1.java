
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

//1)Elencare i giochi con PEGI maggiori o uguali a 16 ordinati per titolo(Codice, Titolo, Genere, PEGI);
public class Query1 extends JFrame {

	//Variabili d'istanza
	private JPanel contentPane;
	private String query = "SELECT Codice,Titolo,Genere,PEGI  FROM Videogioco  WHERE PEGI>=16 ORDER BY Titolo";
	private JTable table;
	
	//Costruttore Frame Query 1
	public Query1(Connection myConn) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1050, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Etichetta Codice
		JLabel lblCodice = new JLabel("| CODICE");
		lblCodice.setBounds(10, 10, 90, 15);
		contentPane.add(lblCodice);
		
		//Etichetta Titolo
		JLabel lblTitolo = new JLabel("| TITOLO");
		lblTitolo.setBounds(116, 10, 90, 15);
		contentPane.add(lblTitolo);
		
		//Etichetta Genere
		JLabel lblGenere = new JLabel("| GENERE");
		lblGenere.setBounds(222, 10, 90, 15);
		contentPane.add(lblGenere);
		
		//Etichetta PEGI
		JLabel lblPegi = new JLabel("| PEGI");
		lblPegi.setBounds(328, 10, 90, 15);
		contentPane.add(lblPegi);
		
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
