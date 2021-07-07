
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

//6) Visualizzare gli editori di cui non si conosce il numeri di giochi rilasciati (ID, Nome, Tipo, Azienda)
public class Query6 extends JFrame {

	//Variabili d'istanza
	private JPanel contentPane;
	private String query = "SELECT ID,Nome,Tipo,Azienda FROM Editore WHERE Num_Giochi IS NULL";
	private JTable table;

	//Costruttore Frame Query 6
	public Query6(Connection myConn) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1050, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Etichetta ID
		JLabel lblID = new JLabel("| ID");
		lblID.setBounds(10, 10, 90, 15);
		contentPane.add(lblID);
				
		//Etichetta Nome
		JLabel lblNome = new JLabel("| NOME");
		lblNome.setBounds(116, 10, 90, 15);
		contentPane.add(lblNome);
				
		//Etichetta Tipo
		JLabel lblTipo = new JLabel("| TIPO");
		lblTipo.setBounds(222, 10, 90, 15);
		contentPane.add(lblTipo);
		
		//Etichetta Azienda
		JLabel lblAzienda = new JLabel("| AZIENDA");
		lblAzienda.setBounds(328, 10, 90, 15);
		contentPane.add(lblAzienda);
		
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
