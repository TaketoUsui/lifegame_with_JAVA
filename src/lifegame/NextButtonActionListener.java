package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextButtonActionListener implements ActionListener {
	BoardModel model;
	BoardView view;
	
	public NextButtonActionListener(BoardModel m, BoardView v) {
		model = m;
		view = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.next();
		view.repaint();
	}
}
