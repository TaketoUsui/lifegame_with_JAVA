package lifegame;

public class RefleshUndoButton implements BoardListener {

	@Override
	public void updated(BoardModel m) {
		// TODO Auto-generated method stub
		m.changeStateOfUndoButton(m.isUndoable());
	}

}
