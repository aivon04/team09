package ca.uwo.csd.cs2212.team09;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

/** Implements cards to display heart rate information
 * @author Team 9
 *
 */
public class Dashboard_HRCard extends JPanel {

	private JLabel lblTitle = new JLabel("title");
	private JLabel lblCalories = new JLabel("Calories");
	private JLabel lblTime = new JLabel("Time");
	private JLabel lblHeartIcon = new JLabel("");

	/**
	 * Create the panel
	 * @param title Title of the panel
	 * @param calories The calories to be displayed
	 * @param time The time to be displayed
     */
	public Dashboard_HRCard(String title, String calories, String time) {
		setSize(196, 196);
		setTitle(title);
		setCalories(calories);
		setTime(time);
		
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalories.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		
		lblCalories.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		if (title != null)
			if (title.contains("Resting")) {
				lblCalories.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
			}
		
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblHeartIcon.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTime, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(lblCalories, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(lblTitle, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(lblHeartIcon, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(lblCalories, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblHeartIcon, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		Utils.styleImage(lblHeartIcon, new ImageIcon(getClass().getResource("/HRZone_Heart.png")).getImage(), 35, 35);
	}

	/**
	 * Changes the title of the panel
	 * @param title title of the panel
     */
	public void setTitle(String title) {
		lblTitle.setText(title);
		if (title != null)
			if (title.contains("Resting")) {
				lblCalories.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
			}
	}

	/**
	 * Changes the calories of the panel
	 * @param calories calories of the panel
     */
	public void setCalories(String calories) {
		lblCalories.setText(calories);
	}

	/**
	 * Changes the time of the panel
	 * @param time time of the panel
     */
	public void setTime(String time) {
		lblTime.setText(time);
	}
}
