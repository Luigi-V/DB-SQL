
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import java.awt.Color;
import java.awt.Font;

public class InsertFrame extends JFrame {
	
	//Variabili d'istanza
	private JTextField FieldNome;
	private JTextField FieldFondazione;
	private JTextField FieldPresidente;
	private String nome = null;
	private String fondazione = null;
	private String presidente = null;
	
	//Frame dell'inserimento
	public InsertFrame(Connection myConn) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1050, 200, 350, 400);
		getContentPane().setLayout(null);
		
		//Struttura dell'Etichetta
		JLabel lblInsert = new JLabel("INSERIRE I DATI DELLA NUOVA AZIENDA:");
		lblInsert.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInsert.setBounds(25, 11, 309, 50);
		getContentPane().add(lblInsert);
		
		//Struttura dell'etichetta 'Nome Azienda'
		JLabel lblNome = new JLabel("NOME AZIENDA");
		lblNome.setBounds(25, 72, 130, 30);
		getContentPane().add(lblNome);
		
		//Struttura dell'etichetta 'Data Fondazione'
		JLabel lblFondazione = new JLabel("DATA FONDAZIONE");
		lblFondazione.setBounds(25, 113, 130, 30);
		getContentPane().add(lblFondazione);
		
		//Struttura dell'etichetta 'Nome Presidente'
		JLabel lblPresidente = new JLabel("NOME PRESIDENTE");
		lblPresidente.setBounds(25, 154, 130, 30);
		getContentPane().add(lblPresidente);
		
		//Struttura della casella di testo 'Nome Azienda'
		FieldNome = new JTextField();
		FieldNome.setBounds(160, 77, 120, 20);
		getContentPane().add(FieldNome);
		FieldNome.setColumns(10);
		
		//Struttura della casella di testo 'Data Fondazione'
		FieldFondazione = new JTextField();
		FieldFondazione.setBounds(160, 118, 120, 20);
		getContentPane().add(FieldFondazione);
		FieldFondazione.setColumns(10);
		
		//Struttura della casella di testo 'Nome Presidente'
		FieldPresidente = new JTextField();
		FieldPresidente.setColumns(10);
		FieldPresidente.setBounds(160, 160, 120, 20);
		getContentPane().add(FieldPresidente);
		
		//Messaggio di inserimento effettuato
		JLabel lblInsEffettuato = new JLabel("Inserimento effettuato");
		lblInsEffettuato.setForeground(new Color(143, 188, 143));
		lblInsEffettuato.setBounds(90, 225, 150, 15);
		getContentPane().add(lblInsEffettuato);
		lblInsEffettuato.setVisible(false);
		
		//Messaggio di errore inserimento
		JLabel lblInsErrore = new JLabel("Errore inserimento");
		lblInsErrore.setForeground(new Color(255, 0, 0));
		lblInsErrore.setBounds(100, 225, 150, 15);
		getContentPane().add(lblInsErrore);
		lblInsErrore.setVisible(false);
		
		//Struttura del bottone "implementa"
		JButton btnNewButton = new JButton("IMPLEMENTA");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					lblInsErrore.setVisible(false);
					lblInsEffettuato.setVisible(false);
					
					//Salvataggio dei dati inseriti				
					nome = FieldNome.getText();
					fondazione = FieldFondazione.getText();
					presidente = FieldPresidente.getText();
										
					//Svuotamento delle caselle(se si vuole inserire piu volte)
					FieldNome.setText(null);
					FieldFondazione.setText(null);
					FieldPresidente.setText(null);
					
					//Verifica dell'input********************
					System.out.println("\rNome       : " + nome + "\rFondazione : " + fondazione + "\rPresidente : " + presidente);
					
					//Implementazione dei dati nel DataBase
					Statement myStmt = myConn.createStatement();
					String sql =  "INSERT INTO Azienda (Nome,Fondazione,Presidente) VALUES ('"+nome+"', '"+fondazione+"', '"+presidente+"')";
					myStmt.executeUpdate(sql);
					
					//********************************
					System.out.println(sql);
					
					lblInsEffettuato.setVisible(true);
				}
				catch(Exception exc){
					lblInsEffettuato.setVisible(false);
					lblInsErrore.setVisible(true);
					exc.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(100, 240, 120, 30);
		getContentPane().add(btnNewButton);
		
	}
}
