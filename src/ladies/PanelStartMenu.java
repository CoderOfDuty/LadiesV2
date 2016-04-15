package ladies;

import javax.swing.JPanel;
import javax.swing.JLayeredPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PanelStartMenu extends JPanel implements ActionListener{

	/**
	 * Create the panel.
	 * @param frame 
	 */
	
	Run frameRun;
	
	public PanelStartMenu(Run frameRun) {
		
		this.frameRun = frameRun;
		
		JLayeredPane layeredPane = new JLayeredPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(151)
					.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addGap(150))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(67)
					.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
					.addGap(78))
		);
		
		JButton btn2Players = new JButton("2 Players");
		btn2Players.addActionListener(this);
		
		JButton btn1Players = new JButton("1 Players");
		btn1Players.addActionListener(this);
		btn1Players.setEnabled(false);
		
		JLabel lblLadies = new JLabel("Ladies");
		lblLadies.setHorizontalAlignment(SwingConstants.CENTER);
		lblLadies.setFont(new Font("Tahoma", Font.PLAIN, 36));
		GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_layeredPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLadies, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
						.addComponent(btn2Players, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
						.addComponent(btn1Players, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_layeredPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLadies, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
					.addComponent(btn1Players, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btn2Players, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		layeredPane.setLayout(gl_layeredPane);
		setLayout(groupLayout);

	}

	public void actionPerformed(ActionEvent e) {
		
		
		frameRun.getContentPane().removeAll();
		PanelGameTable gamePanel = new PanelGameTable();
		
		frameRun.setContentPane(gamePanel);
		frameRun.validate();
		frameRun.repaint();
		frameRun.setVisible(true);
		
	}
}
