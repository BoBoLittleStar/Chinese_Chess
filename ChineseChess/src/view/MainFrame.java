package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import controller.GameController;
import data.Data;
import model.Board;

public class MainFrame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ChessPanel chessPanel;
	private OptionPanel optionPanel;
	private OptionPanel zoomPanel;
	private ListPanel redListPanel;
	private ListPanel blackListPanel;
	private GameController controller;

	public MainFrame() {
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Board board = new Board();
		this.chessPanel = new ChessPanel(board);
		this.optionPanel = new OptionPanel(new String[] { Data.NEW, Data.SET });
		this.controller = new GameController(board, this);
		this.optionPanel.addActionListener(this.controller);
		this.add(this.chessPanel);
		this.add(this.optionPanel, BorderLayout.EAST);
		this.zoomPanel = new OptionPanel(new String[] { Data.ZOOMIN, Data.ZOOMOUT });
		this.zoomPanel.addActionListener(this.controller);
		this.add(this.zoomPanel, BorderLayout.WEST);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	@Override
	public void revalidate() {
		this.chessPanel.revalidate();
		this.optionPanel.revalidate();
		this.zoomPanel.revalidate();
		this.setVisible(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		super.revalidate();
	}
}