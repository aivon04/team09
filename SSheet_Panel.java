package ca.uwo.csd.cs2212.team09;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

/**Implements the panel to show daily and lifetime bests
 * 
 * @author Team 09
 *
 */
public class SSheet_Panel extends JPanel {
	
	JLabel lblBest = new JLabel("Best");
	JLabel lblLifetimeTotal = new JLabel("Lifetime Total");
	JLabel lblDistance = new JLabel("Distance (km)");
	JLabel lblFloors = new JLabel("Floors");
	JLabel lblSteps = new JLabel("Steps");
	
	JLabel lblDistanceBest = new JLabel("NA");
	JLabel lblDistanceLT = new JLabel("NA");
	JLabel lblFloorsBest = new JLabel("NA");
	JLabel lblFloorsLT = new JLabel("NA");
	JLabel lblStepsBest = new JLabel("NA");
	JLabel lblStepsLT = new JLabel("NA");
	
	private int fontZoomer = 5;
	public boolean firstShow = true;
	
	/**
	 * Creates the panel.
	 */
	public SSheet_Panel() {
		Utils.stylePanel(this);
		
		
		lblBest.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBest.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		lblLifetimeTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLifetimeTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		lblDistance.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		Utils.styleSquareImageButton(lblDistance, new ImageIcon(getClass().getResource("/SS_Distance.png")).getImage(), 35);
		
		lblFloors.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		Utils.styleSquareImageButton(lblFloors, new ImageIcon(getClass().getResource("/SS_Floors.png")).getImage(), 35);
		
		
		lblSteps.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		Utils.styleSquareImageButton(lblSteps, new ImageIcon(getClass().getResource("/SS_Steps.png")).getImage(), 35);
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBest, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLifetimeTotal))
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblDistance, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
								.addGap(69))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblDistanceBest)
								.addGap(82)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDistanceLT)
							.addGap(82)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblFloors, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
								.addGap(81))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblFloorsBest)
								.addGap(73)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFloorsLT)
							.addGap(73)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblStepsLT)
							.addGap(64))
						.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblStepsBest)
								.addContainerGap(64, Short.MAX_VALUE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblSteps, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(78)))))
		);
		
		lblDistanceBest.setFont(new Font("Lucida Grande", Font.PLAIN, 18 + fontZoomer));
		lblFloorsBest.setFont(new Font("Lucida Grande", Font.PLAIN, 18 + fontZoomer));
		lblStepsBest.setFont(new Font("Lucida Grande", Font.PLAIN, 18 + fontZoomer));
		lblDistanceLT.setFont(new Font("Lucida Grande", Font.PLAIN, 18 + fontZoomer));
		lblFloorsLT.setFont(new Font("Lucida Grande", Font.PLAIN, 18 + fontZoomer));
		lblStepsLT.setFont(new Font("Lucida Grande", Font.PLAIN, 18 + fontZoomer));
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSteps)
						.addComponent(lblFloors)
						.addComponent(lblDistance))
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBest, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
						.addComponent(lblDistanceBest)
						.addComponent(lblFloorsBest)
						.addComponent(lblStepsBest))
					.addGap(76)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(lblFloorsLT, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(lblStepsLT, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLifetimeTotal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblDistanceLT, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(116))
		);
		setLayout(groupLayout);

	}
	
	/** Sets the data of the panel
	 * @param distBest User's best distance walked in a day
	 * @param distLT User's lifetime distance walked
	 * @param floorsBest User's best floors climbed in a day
	 * @param floorsLT User's lifetime floors climbed
	 * @param stepsBest User's best number of steps taken in a day
	 * @param stepsLT User's lifetime steps taken
	 */
	public void setData(String distBest, String distLT, String floorsBest, String floorsLT, String stepsBest, String stepsLT) {
		lblDistanceBest.setText(distBest + "");
		lblDistanceLT.setText(distLT + "");
		lblFloorsBest.setText(floorsBest + "");
		lblFloorsLT.setText(floorsLT + "");
		lblStepsBest.setText(stepsBest + "");
		lblStepsLT.setText(stepsLT + "");
	}
}
