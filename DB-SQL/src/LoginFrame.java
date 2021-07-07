
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class LoginFrame extends JFrame {	

	//Variabili d'istanza
	private JPanel contentPane;
	private JTextField FieldUser;
	private JTextField FieldPassword;
	private String url = "jdbc:mysql://localhost:3306/azienda_videogiochi?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String user;
	private String password;

	//Costruttore del Frame per il Login
	public LoginFrame() {
		
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 300, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Struttura dell'etichetta 'USERNAME' 
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setBounds(160, 10, 100, 30);
		contentPane.add(lblUsername);
		
		//Struttura della casella di testo 'USERNAME'
		FieldUser = new JTextField();
		FieldUser.setBounds(160, 40, 120, 20);
		contentPane.add(FieldUser);
		FieldUser.setColumns(10);
		
		//Struttura dell'etichetta 'PASSWORD'
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(160, 80, 100, 30);
		contentPane.add(lblPassword);
		
		//Struttura della casella di testo 'PASSWORD'
		FieldPassword = new JTextField();
		FieldPassword.setBounds(160, 110, 120, 20);
		contentPane.add(FieldPassword);
		FieldPassword.setColumns(10);
		
		//Messaggio di errore
		JLabel lblError = new JLabel("Credenziali errate...");
		lblError.setForeground(Color.RED);
		lblError.setBounds(170, 164, 120, 20);
		contentPane.add(lblError);
		lblError.setVisible(false);
		
		//Struttura del bottone 'LOGIN'
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Salvataggio delle credenziali inserite
				user = FieldUser.getText();
				password = FieldPassword.getText();
				
				//Verifica delle credenziali*************
				System.out.println("\rUser     : " + user + "\rPassword : " + password);
				
				//Avvio Frame delle operazioni
				try {
					lblError.setVisible(false);
					//Connessione al DataBase
					Connection myConn = DriverManager.getConnection(url,user,password);
					MainFrame frame = new MainFrame(myConn);
					frame.setVisible(true);
					dispose();
				} catch (Exception exc) {
					lblError.setVisible(true);
					System.out.println("\rERRORE CONNESSIONE");
					exc.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(170, 180, 90, 30);
		contentPane.add(btnLogin);
		
	}
	
	//Recupero user
	public String getUser()	 	{	return user;		}
	
	//Recupero password
	public String getPassword() {	return password;	}
	
	//Recupero url
	public String getUrl() 		{	return url;			}
	
}
