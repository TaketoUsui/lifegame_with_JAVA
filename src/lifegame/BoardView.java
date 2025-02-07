package lifegame;
import java.awt.Graphics;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BoardView extends JPanel implements MouseListener, MouseMotionListener{
	int rows;
	int cols;
	boolean[][] cells;
	BoardModel model;
	final int CELL_SIZE = 20;
	
	public BoardView(BoardModel m) {
		model = m;
		cells = m.getCells();
		rows = cells.length;
		cols = cells[0].length;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(int r = 0; r < rows+1; r++) {
			g.drawLine(CELL_SIZE, (r+1)*CELL_SIZE, (cols+1)*CELL_SIZE, (r+1)*CELL_SIZE);
		}
		for(int c = 0; c < cols+1; c++) {
			g.drawLine((c+1)*CELL_SIZE, CELL_SIZE, (c+1)*CELL_SIZE, (rows+1)*CELL_SIZE);
		}
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				if(cells[r][c]) {
					g.fillRect((c+1)*CELL_SIZE+1, (r+1)*CELL_SIZE+1, CELL_SIZE-1, CELL_SIZE-1);
				}
			}
		}
	}
	
	public void sendCoordinateTowards(int[] coordinate) {
		int[] newCoordinate = new int[2];
		newCoordinate[0] = (coordinate[0] - coordinate[0] % CELL_SIZE) / CELL_SIZE - 1;
		newCoordinate[1] = (coordinate[1] - coordinate[1] % CELL_SIZE) / CELL_SIZE - 1;
		model.changeCell(newCoordinate);
		this.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int[] coordinate = new int[2];
		coordinate[0] = arg0.getX();
		coordinate[1] = arg0.getY();
		sendCoordinateTowards(coordinate);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int[] coordinate = new int[2];
		coordinate[0] = arg0.getX();
		coordinate[1] = arg0.getY();
		sendCoordinateTowards(coordinate);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}