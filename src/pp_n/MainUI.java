package pp_n;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainUI {
	// get width and height of screen
	private static Dimension screenSize;
	private static int widthScreen;
	private static int heightScreen;
	private static int widthFrame;
	private static int heightFrame;
	
	
	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		widthScreen = (int) (screenSize.getWidth());
		heightScreen = (int) (screenSize.getHeight());
		widthFrame = 800;
		heightFrame = 600;
		
		MainUI window = new MainUI();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {

		initializeMain();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeMain() {
		
		frame = new JFrame("Deffy VS Draft");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds((widthScreen - widthFrame) / 2, (heightScreen - heightFrame) / 2, widthFrame, heightFrame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		// Button Deffy (Server)
		JButton btnDeffy = new JButton("CREATE (Deffy)");
		btnDeffy.setBounds((widthFrame - 160) / 2, (heightFrame - 50) / 2, 160, 50);
		btnDeffy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.removeAll();
				frame.validate();
				frame.repaint();
				Server_Deffy.main(null);
			}
		});
		frame.getContentPane().add(btnDeffy);

		// Button Draft (Client)
		JButton btmDraft = new JButton("JOIN (Draft)");
		btmDraft.setBounds((widthFrame - 160) / 2, (heightFrame + 100) / 2, 160, 50);
		btmDraft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.removeAll();
				frame.validate();
				frame.repaint();
				Client_Draft.main(null);
			}
		});
		frame.getContentPane().add(btmDraft);

	}
}
