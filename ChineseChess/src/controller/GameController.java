package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.Data;
import model.Board;
import model.Checker;
import view.MainFrame;

public class GameController implements ActionListener {
	private Board board;
	private MainFrame mainFrame;

	public GameController(Board board, MainFrame mainFrame) {
		this.board = board;
		this.mainFrame = mainFrame;
	}

	public boolean move(Point src, Point dest) {
		boolean moved = this.board.move(src, dest);
		Checker checker = this.board.lastTaken();
		if (moved && checker != null)
			this.mainFrame.put(checker.player, checker);
		return moved;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case Data.NEW:
			this.board.start();
			this.mainFrame.repaint();
			break;
		case Data.SET:
			this.board.toggleMode();
			this.mainFrame.repaint();
			break;
		case Data.FINISH:
			break;
		case Data.ZOOMIN:
			Data.setUnit(1);
			this.mainFrame.revalidate();
			break;
		case Data.ZOOMOUT:
			Data.setUnit(-1);
			this.mainFrame.revalidate();
			break;
		}
	}
}