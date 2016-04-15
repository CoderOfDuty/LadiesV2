package ladies;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PaneGameTable extends JPanel implements ActionListener {

	final static int BOARDWIDTH = 8;
	final static int MAX_BUTTONS = 64;

	public final static int EMPTY = 0;
	public final static int BLANCAS = 1;
	public final static int NEGRAS = 2;

	public JButtonTaulell[] arrayButtons;
	public JButtonTaulell[][] taulell;
	int num = 0;
	ImageIcon b;
	ImageIcon n;
	int anterior=-1;

	int turno = BLANCAS;
	int origen;
	boolean moveDone = false;

	public PaneGameTable() {

		origen = -1;
		setLayout(new GridLayout(8, 8));

		arrayButtons = new JButtonTaulell[MAX_BUTTONS];
		taulell = new JButtonTaulell[BOARDWIDTH][BOARDWIDTH];
		b = new ImageIcon("img/b.png");
		n = new ImageIcon("img/n.png");

		for (int i = 0; i < BOARDWIDTH; i++) {
			for (int j = 0; j < BOARDWIDTH; j++) {

				taulell[i][j] = new JButtonTaulell(num, i, j);
				arrayButtons[num] = taulell[i][j];
				num++;

				if (i % 2 == 0) {
					if (j % 2 == 0) {
						taulell[i][j].setBackground(Color.white);
					} else {
						taulell[i][j].addActionListener(this);
						taulell[i][j].setBackground(Color.black);
						if (i < 3) {
							taulell[i][j].setEstado(NEGRAS);
							taulell[i][j].setIcon(n);
						} else if (i > 4) {
							taulell[i][j].setEstado(BLANCAS);
							taulell[i][j].setIcon(b);
						} else {
							taulell[i][j].setEstado(EMPTY);
						}
					}
				} else {
					if (j % 2 == 1) {
						taulell[i][j].setBackground(Color.white);
					} else {
						taulell[i][j].addActionListener(this);
						taulell[i][j].setBackground(Color.black);
						if (i < 3) {
							taulell[i][j].setEstado(NEGRAS);
							taulell[i][j].setIcon(n);
						} else if (i > 4) {
							taulell[i][j].setEstado(BLANCAS);
							taulell[i][j].setIcon(b);
						} else {
							taulell[i][j].setEstado(EMPTY);
						}
					}
				}
				add(taulell[i][j]);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {

		JButtonTaulell button = (JButtonTaulell) e.getSource();
		
		int botonSelectedId = button.getId();
		int estado = button.getEstado();
		if(anterior!=-1){
			origen=anterior;
			anterior=-1;
		}
		if (estado == turno) {
			if(moveDone&&botonSelectedId!=origen&&estado==turno){}
			else{
				button.setBackground(Color.BLUE);
				if (origen != -1 && origen != botonSelectedId)
					arrayButtons[origen].setBackground(Color.BLACK);
				else if ((origen != -1 && origen == botonSelectedId)){}
					/*
				 	* //Comprovoar si estas obligado a matar
			 		* if(obligadoAMatar&&laFichaSeleccionadaNoEsLaCorrecta){origen=0;}
			 		* else{ cambiar imagen boton a seleccionado deseleccionar otros }
			 		*/
			
				if (botonSelectedId == origen && moveDone == true)
					changeTurn(button);
				else{
					origen = botonSelectedId;
				}
			}
		} else if (estado == PaneGameTable.EMPTY && origen != -1) {
			int movimiento;
			movimiento=comprovarMovimiento(origen,botonSelectedId);
			if(movimiento==0&&moveDone)
				anterior=origen;
			else if(movimiento==0){//moverse sin matar
				moveDone = true;
				arrayButtons[origen].setEstado(EMPTY);
				arrayButtons[origen].setIcon(null);
				arrayButtons[origen].setBackground(Color.BLACK);
				if (turno == BLANCAS) {
					button.setEstado(BLANCAS);
					button.setIcon(b);
				} else {
					button.setEstado(NEGRAS);
					button.setIcon(n);
				}
				changeTurn(button);
			}
			else if(movimiento==-1){//no te puedes mover
				anterior=origen;
			} 
			else{ //el valor movimiento es el ID de la ficha que se elimina
				moveDone = true;
				arrayButtons[origen].setEstado(EMPTY);
				arrayButtons[origen].setIcon(null);
				arrayButtons[origen].setBackground(Color.BLACK);
				if (turno == BLANCAS) {
					button.setEstado(BLANCAS);
					button.setIcon(b);
				} else {
					button.setEstado(NEGRAS);
					button.setIcon(n);
				}
				button.setBackground(Color.BLUE);
				arrayButtons[movimiento].setEstado(EMPTY);
				arrayButtons[movimiento].setIcon(null);
				arrayButtons[movimiento].setBackground(Color.BLACK);
				if(seguirMatando(origen, botonSelectedId)==false)
					changeTurn(button);
			}
			origen=botonSelectedId;
		} 
			

		  
	}

	private boolean seguirMatando(int origen, int botonSelectedId) {
		int direccion;
		if(turno==1){
			direccion=-1;
		}
		else{
			direccion=1;
		}
		if(botonSelectedId+18*direccion<0||botonSelectedId+18*direccion>63)
			return false;
		if(botonSelectedId+1%8==0){
			if(arrayButtons[botonSelectedId+7*direccion].getEstado()==0||arrayButtons[botonSelectedId+7*direccion].getEstado()==turno){return false;}
			if(arrayButtons[botonSelectedId+14*direccion].getEstado()!=0){ return false;}
			return true;
		}
		else if(botonSelectedId+1%8==1||botonSelectedId==0){
			if(arrayButtons[botonSelectedId+9*direccion].getEstado()==0||arrayButtons[botonSelectedId+9*direccion].getEstado()==turno){return false;}
			if(arrayButtons[botonSelectedId+18*direccion].getEstado()!=0){ return false;}
			return true;
		}
		else{
			if((arrayButtons[botonSelectedId+(9*direccion)].getEstado()==0||arrayButtons[botonSelectedId+(9*direccion)].getEstado()==turno)&&(arrayButtons[botonSelectedId+(7*direccion)].getEstado()==0||arrayButtons[botonSelectedId+(7*direccion)].getEstado()==turno)){return false;}
			if((arrayButtons[botonSelectedId+(14*direccion)].getEstado()!=0)&&(arrayButtons[botonSelectedId+(18*direccion)].getEstado()!=0)){return false;}
			return true;
		}
	}

	private void changeTurn(JButtonTaulell button) {
		button.setBackground(Color.BLACK);
		if (turno == BLANCAS){
			turno = NEGRAS;
		}else{
			turno = BLANCAS;
		}
		origen = -1;
		moveDone= false;
	}

	private int comprovarMovimiento(int origen, int botonSelectedId) {
		int direccion;
		if(turno==1){
			direccion=-1;
		}
		else{
			direccion=1;
		}

		if(origen+1%8==0){
			if(botonSelectedId-origen==7*direccion){return 0;}
			if(botonSelectedId-origen==14*direccion){
				return comprovarEliminacion(origen,botonSelectedId);
			}
		}
		else if(origen+1%8==1||origen==0){
			if(botonSelectedId-origen==9*direccion){return 0;}
			if(botonSelectedId-origen==18*direccion){
				return comprovarEliminacion(origen,botonSelectedId);
			}
		}
		else{
			if(botonSelectedId-origen==7*direccion||botonSelectedId-origen==9*direccion){return 0;}
			if(botonSelectedId-origen==14*direccion||botonSelectedId-origen==18*direccion){
				return comprovarEliminacion(origen,botonSelectedId);
			}
		}
		return -1;
	}

	private int comprovarEliminacion(int origen, int botonSelectedId) {
		int eliminada=origen+((botonSelectedId-origen)/2);
		if(arrayButtons[eliminada].getEstado()==turno||arrayButtons[eliminada].getEstado()==0){
			return -1;
		}
		else{
			return eliminada;
		}
	}
	
	

}
