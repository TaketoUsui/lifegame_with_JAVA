package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameButtonActionListener implements ActionListener {
	public NewGameButtonActionListener() {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Main.main(null);
	}
}
