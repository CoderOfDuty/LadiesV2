package ladies;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Run extends JFrame {

	// private PanelGameTable gamePanel;
	private PanelStartMenu startPanel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Run frame = new Run();
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

	public Run() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Ladies");
		setIconImage(Toolkit.getDefaultToolkit().createImage("img/icon.png"));
		setSize(600, 600);
		setResizable(false);
		this.setJMenuBar(new BarMenu(this));
		startPanel = new PanelStartMenu(this);
		setContentPane(startPanel);
		startPanel.setVisible(true);
	}

	public class BarMenu extends JMenuBar implements ActionListener {
		
		Run frameRun;
		
		JMenu menuFile;
		JMenu menuEdit;
		JMenu menuHelp;
		
		JMenuItem opcionNew;
		JMenuItem opcionOpen;
		JMenuItem opcionClose;

		public BarMenu(Run run) {
			frameRun = run;
			menuFile = new JMenu("File");
			menuEdit = new JMenu("Edit");
			menuHelp = new JMenu("Help");

			opcionNew = new JMenuItem("New");
			opcionOpen = new JMenuItem("Open");
			opcionClose = new JMenuItem("Close");

			opcionClose.addActionListener(this);

			menuFile.add(opcionNew);
			//opcionNew.setEnabled(false);
			menuFile.add(opcionOpen);
			opcionOpen.setEnabled(false);
			menuFile.add(opcionClose);

			add(menuFile);
			add(menuEdit);
			add(menuHelp);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			JMenuItem button = ((JMenuItem) e.getSource());

			if (button.equals(opcionClose)) {
				System.exit(0);
			}else if(button.equals(opcionNew)){
				
				frameRun.getContentPane().removeAll();
				PanelGameTable gamePanel = new PanelGameTable();
				frameRun.setContentPane(gamePanel);
				frameRun.validate();
				frameRun.repaint();
				frameRun.setVisible(true);
			}

		}
	}

}
