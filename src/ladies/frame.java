package ladies;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class frame extends JFrame {

	private PaneGameTable gamePane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame frame = new frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Ladies");
		setIconImage(Toolkit.getDefaultToolkit().createImage("img/icon.png"));
		setSize(500,500);
		gamePane = new PaneGameTable();
		setContentPane(gamePane);
		gamePane.setVisible(true);
	}

}
