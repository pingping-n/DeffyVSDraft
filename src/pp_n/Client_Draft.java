package pp_n;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Client_Draft {

	private JFrame frame;
	private static JTextPane textPane;
	private JTextField textField;
	private JButton btnSend;

	static Socket sckt;
	static DataInputStream dtinpt;
	static DataOutputStream dtotpt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_Draft window = new Client_Draft();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			sckt = new Socket("127.0.0.1", 51515);
			dtinpt = new DataInputStream(sckt.getInputStream());
			dtotpt = new DataOutputStream(sckt.getOutputStream());
			String msgin = "";
			while (!msgin.equals("Exit")) {
				msgin = dtinpt.readUTF();
				textPane.setText(textPane.getText().trim() + "\n Server: " + msgin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public Client_Draft() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Draft");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		textPane = new JTextPane();
		textPane.setBounds(10, 11, 424, 167);
		textPane.setEditable(false);
		frame.getContentPane().add(textPane);

		textField = new JTextField();
		textField.setBounds(10, 189, 317, 71);
		frame.getContentPane().add(textField);
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
		frame.getContentPane().add(btnSend);
	}

}
