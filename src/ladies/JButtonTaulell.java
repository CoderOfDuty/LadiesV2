package ladies;

import javax.swing.JButton;

public class JButtonTaulell extends JButton {
	
	private int id, col, row, estado;
	
	public JButtonTaulell(int id, int col, int row) {
		super();
		
		this.id = id;
		this.col = col;
		this.row = row;
		
	}
	
	public int getId(){return id;}
	public int getCol(){return col;}
	public int getRow(){return row;}
	public void setEstado(int estado){this.estado = estado;}
	public int getEstado(){return estado;}

}
