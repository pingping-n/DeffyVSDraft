package pp_n;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;


public class Server_Deffy {

	private static JFrame frame;
	private static JLabel labelIP;
	private static JTextPane textPane;
	private static JTextField textField;
	private static JButton btnSend;

	private static ServerSocket ssckt;
	private static Socket sckt;
	private static DataInputStream dtinpt;
	private static DataOutputStream dtotpt;

	private static String ipServer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server_Deffy window = new Server_Deffy();
					window.frame.setVisible(true);				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			ipServer = InetAddress.getLocalHost().getHostAddress();
			ssckt = new ServerSocket(51515);
			sckt = ssckt.accept();
			
			if (sckt != null) {
				frame.getContentPane().remove(labelIP);
				frame.getContentPane().add(textPane);
				frame.getContentPane().add(textField);
				frame.getContentPane().add(btnSend);
				
				// refresh GUI
				frame.validate();
				frame.repaint();
				
				String msgin = "";
				dtinpt = new DataInputStream(sckt.getInputStream());
				dtotpt = new DataOutputStream(sckt.getOutputStream());
				while (!msgin.equals("exit")) {
					msgin = dtinpt.readUTF();
					textPane.setText(textPane.getText().trim() + "\n Client: " + msgin);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public Server_Deffy() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Deffy");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		labelIP = new JLabel("IP : " + ipServer);
		labelIP.setHorizontalAlignment(SwingConstants.CENTER);
		labelIP.setBounds(frame.getHeight()/2, frame.getWidth()/3, 137, 27);
		frame.getContentPane().add(labelIP);

		textPane = new JTextPane();
		textPane.setBounds(10, 11, 424, 167);
		textPane.setEditable(false);

		textField = new JTextField();
		textField.setBounds(10, 189, 317, 71);
		textField.setColumns(10);

		btnSend = new JButton("Send");
		btnSend.setBounds(337, 189, 97, 71);
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String msgout = "";
					msgout = textField.getText().trim();
					dtotpt.writeUTF(msgout);

				} catch (Exception x) {
				}
			}
		});
	}
}
