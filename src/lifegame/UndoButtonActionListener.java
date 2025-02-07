package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoButtonActionListener implements ActionListener {
	BoardModel model;
	BoardView view;
	
	public UndoButtonActionListener(BoardModel m, BoardView v) {
		model = m;
		view = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		model.undo();
		view.repaint();
	}
}
