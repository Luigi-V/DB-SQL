
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

//4)Selezionare i videogiochi che non sono stati rilasciati da editore esterno(Codice, Titolo, Genere, Pegi, Data_Rilascio);
public class Query4 extends JFrame {

	//Variabili d'istanza
	private JPanel contentPane;
	private String query = "SELECT V.Codice, V.Titolo, V.Genere, V.Pegi, V.Data_Rilascio FROM Videogioco AS V JOIN Editore AS E ON V.IDEditore = E.ID WHERE NOT E.Tipo = 'Esterno'";
	private JTable table;

	//Costruttore Frame Query 4
	public Query4(Connection myConn) {
		
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
		
		//Etichetta Codice
		JLabel lblCodice = new JLabel("| CODICE");
		lblCodice.setBounds(10, 10, 60, 15);
		contentPane.add(lblCodice);
		
		//Etichetta Titolo
		JLabel lblTitolo = new JLabel("| TITOLO");
		lblTitolo.setBounds(95, 10, 60, 15);
		contentPane.add(lblTitolo);
		
		//Etichetta Genere
		JLabel lblGenere = new JLabel("| GENERE");
		lblGenere.setBounds(180, 10, 60, 15);
		contentPane.add(lblGenere);
		
		//Etichetta PEGI
		JLabel lblPegi = new JLabel("| PEGI");
		lblPegi.setBounds(265, 10, 60, 15);
		contentPane.add(lblPegi);
		
		//Etichetta Data Rilascio
		JLabel lblData = new JLabel("| DATA");
		lblData.setBounds(350, 10, 60, 15);
		contentPane.add(lblData);
		
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
