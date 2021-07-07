
import java.io.*;
import java.sql.*;
import java.sql.Statement;
import net.proteanit.sql.DbUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Driver {

	public static void main(String[] args) {
		
		//Frame del login
		try {
			LoginFrame Login = new LoginFrame();
			Login.setVisible(true);
		} catch (Exception e) {
			System.out.println("\rERRORE FRAME LOGIN!!!");
			e.printStackTrace();
		}
	}
}	
	
	/*//Rappresentazione del menu di scelta
	public static int scelta() {
		System.out.println("\n\n");
		System.out.println("1. Si inserisca la tupla (Ubisoft, 1986-01-15 , Yves Guillemot) in Azienda;");
		System.out.println("2. Elencare i giochi con PEGI maggiori o uguali a 16(Codice, Titolo, Genere) ordinati per titolo;");
		System.out.println("3. Elencare per ogni team il numero di progettisti appartenenti (ID, Nome, Num_Progettisti);");
		System.out.println("4. Determinare quanti team lavorano in un'azienda e la sede principale delle aziende (Nome, Citta, Num_Team);");
		System.out.println("5. Selezionare i videogiochi che non sono stati rilasciati da editore esterno(Codice, Titolo, Genere, Pegi, Data_Rilascio);");
		System.out.println("6. Individuare il Team che ha progettato il maggior numero di giochi (ID, Nome, Max_Giochi);");
		System.out.println("* Premere 0 per terminare. *");
		System.out.print("Inserisci scelta: ");
		int dec=0;
		Scanner in = new Scanner(System.in);
		dec= in.nextInt();
		return dec;
	}
			
	//1)Si inserisca la tupla (Ubisoft, 1986-01-15 , Yves Guillemot) in Azienda;	
	public static void op1(String url, String user, String password) {
		try {
			//Connessione al DataBase
			Connection myConn = DriverManager.getConnection(url,user,password);
			//Creazione dello Statement
			Statement myStmt = myConn.createStatement();	
			// Esecuzione Query
			String sql =  "INSERT INTO Azienda (Nome,Fondazione,Presidente) VALUES ('Ubisoft', '1986-01-15', 'Yves Guillemot')";	
			myStmt.executeUpdate(sql);		
			System.out.println("Inserimento effettuato");
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
	//2)Elencare i giochi con PEGI maggiori o uguali a 16(Codice, Titolo, Genere) ordinati per titolo;
	public static void op2(String url, String user, String password) {
		//Creazione dello Statement
		try {
			Connection myConn = DriverManager.getConnection(url,user,password);
			Statement myStmt = myConn.createStatement();	
			// Esecuzione Query
			ResultSet myRs = myStmt.executeQuery("SELECT Codice,Titolo,Genere  FROM Videogioco  WHERE PEGI>= 16 ORDER BY Titolo");	
			// Risultato Query
			while(myRs.next()) {
				System.out.println(myRs.getString("Codice") + ", " + myRs.getString("Titolo") + ", " + myRs.getString("Genere"));		
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}	
	
	//3)Elencare per ogni team il numero di progettisti appartenenti (ID, Nome, Num_Progettisti);
	public static void op3(String url, String user, String password) {
		try {
			Connection myConn = DriverManager.getConnection(url,user,password);		
			Statement myStmt = myConn.createStatement();			
			// Esecuzione Query
			ResultSet myRs = myStmt.executeQuery("SELECT T.ID, T.Nome, count(*) AS Num_Progettisti FROM Team AS T JOIN Progettista AS P ON T.ID= P.Team GROUP BY T.Nome");			
			// Risultato Query
			while(myRs.next()) {
				System.out.println(myRs.getString("T.ID") + ", " + myRs.getString("T.Nome") + ", " + myRs.getString("Num_Progettisti"));				
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
	//4)Determinare quanti team lavorano in un'azienda e la sede principale delle aziende (Nome, Citta, Num_Team);
	public static void op4(String url, String user, String password) {
		try {
			Connection myConn = DriverManager.getConnection(url,user,password);			
			Statement myStmt = myConn.createStatement();\			
			// Esecuzione Query
			ResultSet myRs = myStmt.executeQuery("SELECT A.Nome, S.Citta, count(*) AS Num_Team FROM Sede AS S JOIN (Azienda AS A JOIN Team AS T ON A.Nome= T.Azienda) ON S.Azienda = A.Nome GROUP BY A.Nome");
			// Risultato Query
			while(myRs.next()) {
				System.out.println(myRs.getString("A.Nome") + ", " + myRs.getString("S.Citta") + ", " + myRs.getString("Num_Team"));			
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
	//5)Selezionare i videogiochi che non sono stati rilasciati da editore esterno(Codice, Titolo, Genere, Pegi, Data_Rilascio);
	public static void op5(String url, String user, String password) {
		try {
			Connection myConn = DriverManager.getConnection(url,user,password);			
			Statement myStmt = myConn.createStatement();		
			// Esecuzione Query
			ResultSet myRs = myStmt.executeQuery("SELECT V.Codice, V.Titolo, V.Genere, V.Pegi, V.Data_Rilascio FROM Videogioco AS V JOIN Editore AS E ON V.IDEditore = E.ID WHERE NOT E.Tipo = 'Esterno'");		
			// Risultato Query
			while(myRs.next()) {
				System.out.println(myRs.getString("V.Codice") + ", " + myRs.getString("V.Titolo") + ", " + myRs.getString("V.Genere") + ", " + myRs.getString("V.Pegi") + ", " + myRs.getString("V.Data_Rilascio"));			
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
	//6)Individuare il Team che ha progettato il maggior numero di giochi (ID, Nome, Max_Giochi);
	public static void op6(String url, String user, String password) {	
		try {
			Connection myConn = DriverManager.getConnection(url,user,password);	
			Statement myStmt = myConn.createStatement();
			// Esecuzione Query
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM Temp WHERE Num_Giochi= (SELECT Max(Num_Giochi) FROM Temp)");
			// Risultato Query
			while(myRs.next()) {
				System.out.println(myRs.getString("ID") + ", " + myRs.getString("Nome") + ", " + myRs.getString("Num_Giochi"));	
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
}*/
