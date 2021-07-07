
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MainFrame extends LoginFrame {

	//Variabili d'istanza
	private JPanel contentPane;
	
	//Frame del main
	public MainFrame(Connection myConn) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 650, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Etichetta
		JLabel lblScegliere = new JLabel("SCEGLIERE UNA DELLE SEGUENTI OPERAZIONI:");
		lblScegliere.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblScegliere.setForeground(SystemColor.infoText);
		lblScegliere.setBounds(25, 10, 350, 40);
		contentPane.add(lblScegliere);
		
		//Creazione frame di inserimento
		InsertFrame frameIn = new InsertFrame(myConn);
		//Creazione dei frame delle query
		Query1 query1 = new Query1(myConn);
		Query2 query2 = new Query2(myConn);
		Query3 query3 = new Query3(myConn);
		Query4 query4 = new Query4(myConn);
		Query5 query5 = new Query5(myConn);
		Query6 query6 = new Query6(myConn);
		Query7 query7 = new Query7(myConn);
		
		//Inserimento dei dati di una nuova azienda
		JButton btn1 = new JButton("    INSERIRE NEL DATABASE I DATI DI UNA NUOVA AZIENDA");
		btn1.setHorizontalAlignment(SwingConstants.LEFT);
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					//Chiusura degli altri frame
					frameIn.setVisible(false);
					query1.setVisible(false);
					query2.setVisible(false);
					query3.setVisible(false);
					query4.setVisible(false);
					query5.setVisible(false);
					query6.setVisible(false);
					query7.setVisible(false);
					
					//InsertFrame frameIn = new InsertFrame(myConn);
					frameIn.setVisible(true);
				}
				catch(Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		btn1.setBounds(20, 50, 600, 50);
		contentPane.add(btn1);
		
		//1)Elencare i giochi con PEGI maggiori o uguali a 16 ordinati per titolo(Codice, Titolo, Genere);
		JButton btn2 = new JButton("1. ELENCARE I GIOCHI CON PEGI MAGGIORI O UGUALI A 16, ORDINATI PER TITOLO");
		btn2.setHorizontalAlignment(SwingConstants.LEFT);
		btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//Creazione dello Statement
				try {
					//Chiusura degli altri frame
					frameIn.setVisible(false);
					query1.setVisible(false);
					query2.setVisible(false);
					query3.setVisible(false);
					query4.setVisible(false);
					query5.setVisible(false);
					query6.setVisible(false);
					query7.setVisible(false);
					
					//Avvio frame query1
					query1.setVisible(true);
					
					//Verifica dell`output*********************************
					Statement myStmt = myConn.createStatement();
					//Esecuzione Query
					ResultSet myRs = myStmt.executeQuery("SELECT Codice,Titolo,Genere,PEGI  FROM Videogioco  WHERE PEGI>= 16 ORDER BY Titolo");
					//Risultato Query
					while(myRs.next()) {
						System.out.println(myRs.getString("Codice") + ", " + myRs.getString("Titolo") + ", " + myRs.getString("Genere")+ ", " + myRs.getString("PEGI"));
					}//**************************************************
				}
				catch(Exception exc){
					exc.printStackTrace();
				}
				
			}
		});
		btn2.setBounds(20, 110, 600, 50);
		contentPane.add(btn2);
		
		//2)Elencare per ogni team il numero di progettisti appartenenti (ID, Nome, Num_Progettisti);
		JButton btn3 = new JButton("2. ELENCARE PER OGNI TEAM IL NUMERO DI PROGETTISTI APPARTENENTI");
		btn3.setHorizontalAlignment(SwingConstants.LEFT);
		btn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Creazione dello Statement
				try {
					
					//Chiusura degli altri frame
					frameIn.setVisible(false);
					query1.setVisible(false);
					query2.setVisible(false);
					query3.setVisible(false);
					query4.setVisible(false);
					query5.setVisible(false);
					query6.setVisible(false);
					query7.setVisible(false);
					
					//Avvio frame query2
					query2.setVisible(true);
					
					//Verifica dell`output*********************************
					Statement myStmt = myConn.createStatement();
					// Esecuzione Query
					ResultSet myRs = myStmt.executeQuery("SELECT T.ID, T.Nome, count(*) AS Num_Progettisti FROM Team AS T JOIN Progettista AS P ON T.ID= P.Team GROUP BY T.Nome");
					
					// Risultato Query
					while(myRs.next()) {
						System.out.println(myRs.getString("T.ID") + ", " + myRs.getString("T.Nome") + ", " + myRs.getString("Num_Progettisti"));	
					}//**************************************************
				}
				catch(Exception exc){
					exc.printStackTrace();
				}
				
			}
		});
		btn3.setBounds(20, 170, 600, 50);
		contentPane.add(btn3);
		
		//3)Determinare quanti team lavorano in un'azienda e la sede principale delle aziende (Nome, Citta, Num_Team);
		JButton btn4 = new JButton("3. DETERMINARE QUANTI TEAM LAVORANO IN UN AZIENDA, E LA SEDE PRINCIPALE DELLE AZIENDE");
		btn4.setHorizontalAlignment(SwingConstants.LEFT);
		btn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Creazione dello Statement
				try {			
					//Chiusura degli altri frame
					frameIn.setVisible(false);
					query1.setVisible(false);
					query2.setVisible(false);
					query3.setVisible(false);
					query4.setVisible(false);
					query5.setVisible(false);
					query6.setVisible(false);
					query7.setVisible(false);
					
					//Avvio frame query2
					query3.setVisible(true);
					
					//Verifica dell`output*********************************
					Statement myStmt = myConn.createStatement();
					// Esecuzione Query
					ResultSet myRs = myStmt.executeQuery("SELECT A.Nome, S.Citta, count(*) AS Num_Team FROM Sede AS S JOIN (Azienda AS A JOIN Team AS T ON A.Nome= T.Azienda) ON S.Azienda = A.Nome GROUP BY A.Nome");
					
					// Risultato Query
					while(myRs.next()) {
						System.out.println(myRs.getString("A.Nome") + ", " + myRs.getString("S.Citta") + ", " + myRs.getString("Num_Team"));	
					}//**************************************************
				}
				catch(Exception exc){
					exc.printStackTrace();
				}
				
			}
		});
		btn4.setBounds(20, 230, 600, 50);
		contentPane.add(btn4);
		
		//4)Selezionare i videogiochi che non sono stati rilasciati da editore esterno(Codice, Titolo, Genere, Pegi, Data_Rilascio);
		JButton btn5 = new JButton("4. SELEZIONARE I VIDEOGIOCHI CHE NON SONO STATI RILASCIATI DA UN EDITORE ESTERNO");
		btn5.setHorizontalAlignment(SwingConstants.LEFT);
		btn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Creazione dello Statement
				try {
						
					//Chiusura degli altri frame
					frameIn.setVisible(false);
					query1.setVisible(false);
					query2.setVisible(false);
					query3.setVisible(false);
					query4.setVisible(false);
					query5.setVisible(false);
					query6.setVisible(false);
					query7.setVisible(false);
					
					//Avvio frame query2
					query4.setVisible(true);
					
					//Verifica dell`output*********************************
					Statement myStmt = myConn.createStatement();
					// Esecuzione Query
					ResultSet myRs = myStmt.executeQuery("SELECT V.Codice, V.Titolo, V.Genere, V.Pegi, V.Data_Rilascio FROM Videogioco AS V JOIN Editore AS E ON V.IDEditore = E.ID WHERE NOT E.Tipo = 'Esterno'");
					// Risultato Query
					while(myRs.next()) {
						System.out.println(myRs.getString("V.Codice") + ", " + myRs.getString("V.Titolo") + ", " + myRs.getString("V.Genere") + ", " + myRs.getString("V.Pegi") + ", " + myRs.getString("V.Data_Rilascio"));	
					}//**************************************************
				}
				catch(Exception exc){
					exc.printStackTrace();
				}
				
			}
		});
		btn5.setBounds(20, 290, 600, 50);
		contentPane.add(btn5);
		
		//5)Individuare il Team che ha progettato il maggior numero di giochi (ID, Nome, Max_Giochi);
		JButton btn6 = new JButton("5. INDIVIDUARE IL TEAM CHE HA PROGETTATO IL MAGGIOR NUMERO DI GIOCHI");
		btn6.setHorizontalAlignment(SwingConstants.LEFT);
		btn6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Creazione dello Statement
				try {
					
					//Chiusura degli altri frame
					frameIn.setVisible(false);
					query1.setVisible(false);
					query2.setVisible(false);
					query3.setVisible(false);
					query4.setVisible(false);
					query5.setVisible(false);
					query6.setVisible(false);
					query7.setVisible(false);
					
					//Avvio frame query2
					query5.setVisible(true);
					
					//Verifica dell`output*********************************
					Statement myStmt = myConn.createStatement();
					// Esecuzione Query
					ResultSet myRs = myStmt.executeQuery("SELECT * FROM Temp WHERE Num_Giochi= (SELECT Max(Num_Giochi) FROM Temp)");
					// Risultato Query
					while(myRs.next()) {
						System.out.println(myRs.getString("ID") + ", " + myRs.getString("Nome") + ", " + myRs.getString("Num_Giochi"));
					}//**************************************************
				}
				catch(Exception exc){
					exc.printStackTrace();
				}
				
			}
		});
		btn6.setBounds(20, 350, 600, 50);
		contentPane.add(btn6);
		
		//6) Visualizzare gli editori di cui non si conosce il numeri di giochi rilasciati (ID, Nome, Tipo, Azienda)
		JButton btn7 = new JButton("6. VISUALIZZARE GLI EDITORE DI CUI NON SI CONOSCE IL NUMERO DI GIOCHI RILASCIATI");
		btn7.setHorizontalAlignment(SwingConstants.LEFT);
		btn7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Creazione dello Statement
				try {
					
					//Chiusura degli altri frame
					frameIn.setVisible(false);
					query1.setVisible(false);
					query2.setVisible(false);
					query3.setVisible(false);
					query4.setVisible(false);
					query5.setVisible(false);
					query6.setVisible(false);
					query7.setVisible(false);
					
					//Avvio frame query2
					query6.setVisible(true);
				}
				catch(Exception exc){
					exc.printStackTrace();
				}
				
			}
		});
		btn7.setBounds(20, 410, 600, 50);
		contentPane.add(btn7);
		
		//7) Estrarre i progettisti che hanno degli omonimi (ovvero persone che hanno lo stesso nome e cognome, ma diverso cod fisc)-(CF, Nome, Cognome, Ruolo)
		JButton btn8 = new JButton("7. ESTRARRE I PROGETTISTI CHE HANNO DEGLI OMONIMI");
		btn8.setHorizontalAlignment(SwingConstants.LEFT);
		btn8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Creazione dello Statement
				try {
					
					//Chiusura degli altri frame
					frameIn.setVisible(false);
					query1.setVisible(false);
					query2.setVisible(false);
					query3.setVisible(false);
					query4.setVisible(false);
					query5.setVisible(false);
					query6.setVisible(false);
					query7.setVisible(false);
					
					//Avvio frame query2
					query7.setVisible(true);
				}
				catch(Exception exc){
					exc.printStackTrace();
				}
			}
		});
		btn8.setBounds(20, 470, 600, 50);
		contentPane.add(btn8);
	}	
}
