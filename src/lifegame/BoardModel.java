package lifegame;
import java.util.ArrayList;
import javax.swing.JButton;

public class BoardModel {
	private int rows;
	private int cols;
	private boolean[][] cells;
	private boolean[][][] cells_history;
	private int history_length;
	private int history_pointer;
	final int HISTORY_CAP = 32;
	int[] lastChangedCell;
	
	private ArrayList<BoardListener> listeners;
	
	private JButton undoButton;
	
	public BoardModel(int c, int r) {
		cols = c;
		rows = r;
		cells = new boolean[rows][cols];
		cells_history = new boolean[HISTORY_CAP][rows][cols];
		history_pointer = -1;
		history_length = 0;
		listeners = new ArrayList<BoardListener>();
		
		lastChangedCell = new int[2];
		lastChangedCell[0] = -1;
		lastChangedCell[1] = -1;
	}
	public int getCols() {
		return cols;
	}
	public int getRows() {
		return rows;
	}
	public boolean[][] getCells() {
		return cells;
	}
	
	public void setUndoButton(JButton b) {
		undoButton = b;
	}
	
	public void changeStateOfUndoButton(boolean enable) {
		undoButton.setEnabled(enable);
	}
	
	public void printForDebug() {
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				if(cells[r][c]) {
					System.out.print("*");
				}else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void changeCellState(int x, int y) {
		add_history();
		cells[y][x] = !cells[y][x];
		this.fireUpdate();
	}
	
	public void addListener(BoardListener listener) {
		listeners.add(listener);
	}
	private void fireUpdate() {
		for (BoardListener listener: listeners) {
			listener.updated(this);
		}
	}
	
	public void next() {
		add_history();
		int[][] newcells;
		newcells = new int[rows + 2][cols + 2];
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				if(cells[r][c]) {
					for(int r_temp  = r; r_temp <= r + 2; r_temp++) {
						for(int c_temp = c; c_temp <= c + 2; c_temp++) {
							if(r_temp != r+1 || c_temp != c+1)newcells[r_temp][c_temp]++;
						}
					}
				}
			}
		}
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				if((cells[r][c] && newcells[r+1][c+1] == 2) || newcells[r+1][c+1] == 3) {
					cells[r][c] = true;
				}else {
					cells[r][c] = false;
				}
			}
		}
		this.fireUpdate();
	}
	
	public void undo() {
		if(isUndoable()) {
			copy_two_dim_array(cells, cells_history[history_pointer], rows, cols);
			history_length--;
			history_pointer = (history_pointer + HISTORY_CAP - 1) % HISTORY_CAP;
			this.fireUpdate();
		}else {
			System.out.println("Error: undo is unavailable. There is no history.");
		}
	}
	
	private void add_history() {
			history_pointer = (history_pointer + 1) % HISTORY_CAP;
			copy_two_dim_array(cells_history[history_pointer], cells, rows, cols);
		if(history_length < HISTORY_CAP) {
			history_length++;
		}
	}
	
	private void copy_two_dim_array(boolean[][] copy, boolean[][] original, int rows_len, int cols_len) {
		for(int r = 0; r < rows_len; r++) {
			for(int c = 0; c < cols_len; c++) {
				copy[r][c] = original[r][c];
			}
		}
	}
	
	public boolean isUndoable() {
		if(history_length > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void changeCell(int[] coordinate) {
		if((coordinate[0] != lastChangedCell[0] || coordinate[1] != lastChangedCell[1]) && coordinate[0] >=0 && coordinate[0] < cols && coordinate[1] >=0 && coordinate[1] < rows) {
			lastChangedCell[0] = coordinate[0];
			lastChangedCell[1] = coordinate[1];
			changeCellState(coordinate[0], coordinate[1]);
		}
	}
}