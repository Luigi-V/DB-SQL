
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

//7) Estrarre i progettisti che hanno degli omonimi (ovvero persone che hanno lo stesso nome e cognome, ma diverso cod fisc)-(CF, Nome, Cognome, Ruolo)
public class Query7 extends JFrame {

	//Variabili d'istanza
	private JPanel contentPane;
	private JTable table;
	private String query =  "SELECT CF, Nome, Cognome, Ruolo " + 
							"FROM Progettista AS P " + 
							"WHERE EXISTS (SELECT * " + 
							"				FROM Progettista AS P1 " + 
							"                WHERE P.Nome = P1.Nome AND " + 
							"					  P.Cognome = P1.Cognome AND " + 
							"                      P.CF <> P1.CF)";

	//Costruttore Frame Query 6
	public Query7(Connection myConn) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1050, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Etichetta CF
		JLabel lblCF = new JLabel("| CF");
		lblCF.setBounds(10, 10, 90, 15);
		contentPane.add(lblCF);
				
		//Etichetta Nome
		JLabel lblNome = new JLabel("| NOME");
		lblNome.setBounds(116, 10, 90, 15);
		contentPane.add(lblNome);
				
		//Etichetta Cognome
		JLabel lblCognome = new JLabel("| COGNOME");
		lblCognome.setBounds(222, 10, 90, 15);
		contentPane.add(lblCognome);
		
		//Etichetta Ruolo
		JLabel lblRuolo = new JLabel("| RUOLO");
		lblRuolo.setBounds(328, 10, 90, 15);
		contentPane.add(lblRuolo);
		
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
