package lifegame;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class Main implements Runnable {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Main());
	}
	public void run() {
		BoardModel model = new BoardModel(24,18);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel base = new JPanel();
		frame.setContentPane(base);
		frame.setTitle("Lifegame");
		base.setPreferredSize(new Dimension(600, 500));
		frame.setMinimumSize(new Dimension(300, 200));
		base.setLayout(new BorderLayout());
		BoardView view = new BoardView(model);
		base.add(view, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		base.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout());
		
		JButton nextButton = new JButton("next");
		JButton undoButton = new JButton("undo");
		nextButton.addActionListener(new NextButtonActionListener(model, view));
		undoButton.addActionListener(new UndoButtonActionListener(model, view));
		buttonPanel.add(nextButton, BorderLayout.CENTER);
		buttonPanel.add(undoButton, BorderLayout.CENTER);
		
		JButton newGameButton = new JButton("new game");
		newGameButton.addActionListener(new NewGameButtonActionListener());
		buttonPanel.add(newGameButton, BorderLayout.CENTER);
		
		model.setUndoButton(undoButton);
		model.addListener(new RefleshUndoButton());
		
		frame.pack();
		frame.setVisible(true);
	}
}
