package lifegame;

public class ModelPrinter implements BoardListener {

	@Override
	public void updated(BoardModel model) {
		model.printForDebug();
	}

}
