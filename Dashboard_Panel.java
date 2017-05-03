package ca.uwo.csd.cs2212.team09;

import java.awt.Dimension;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/** This class implements the panel that will be on the dashboard
 * and will display various data. Parent container of the card
 * @author Team 9
 *
 */
public class Dashboard_Panel extends JPanel {
	private static final long serialVersionUID = 8925528422220724438L;
	private List<JPanel>panels = new ArrayList<JPanel>();
	private Dimension currLayout;

	public MainView parentView;
	
	/** Constructs a panel for the dashboard
	 * @param p the main dashboard for which the panel is on
	 */
	public Dashboard_Panel(MainView p) {
		parentView = p;
	}

	
	/** Adds a panel to the dashboard
	 * @param panel the card itself
	 * @param layoutNow true to reorganize the panel
	 */
	public void add(JPanel panel, Boolean layoutNow) {
		panels.add(panel);
		if (layoutNow)
			layoutPanel(currLayout);
	}
	
	/** Decides which cards are in the panel
	 * @param cardIndex position of the card
	 * @return returns the cards on the panel at cardIndex
	 */
	public JPanel modifyAt(int cardIndex) {
		if (cardIndex >= panels.size()) {
			return null;
		}
		return (JPanel) panels.get(cardIndex);
	}
	
	/** Removes a panel from the dashboard
	 * @param panel the panel to be removed
	 */ //TODO: Complete this method
	public void remove(int cardIndex) {
		panels.remove(cardIndex);
		layoutPanel(currLayout);
	}
	
	/**
	 * Removes all cards from the dashboard
	 */
	public void removeAllCards() {
		removeAll();
		panels.clear();
	}
	
	/**
	 * Updates the date of the panel
	 * @param date date to be displayed in the panel
	 */
	public void changeDate(String date) {
		parentView.updateTime(date);
	}
	
	/** Returns how many cards are in the panel
	 * @return number of cards in the panel
	 */
	public int subviewCount() {
		if (panels == null)
			return 0;
		return panels.size();
	}
	
	/** Organizes the cards
	 * @param layoutMode How many cards are in each row
	 */
	public void layoutPanel(Dimension layoutMode) {
		if (layoutMode == null)
			return;
		currLayout = layoutMode;

		removeAll();
		setLayout(null);
		int x = 0;
		int y = 0;
		for (int i=0;i<panels.size()/currLayout.height+(panels.size()%currLayout.height==0?0:1);i++) {
			for (int j=0;j<currLayout.height;j++) {
				if (i*currLayout.height+j >= panels.size())
					break;
				panels.get(i*currLayout.height+j).setLocation(x, y);
				add(panels.get(i*currLayout.height+j));
				y += MainView.CARD_SIZE + MainView.CARD_GAP_SIZE;
			}
			y = 0;
			x += MainView.CARD_SIZE + MainView.CARD_GAP_SIZE;
		}
		updateUI();
	}
	
}
