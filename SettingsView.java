package ca.uwo.csd.cs2212.team09;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

/**
 * Implements the settings dialog box
 * @author Team 9
 */
public class SettingsView extends JDialog {
	
	private MainView parent;
	private JCheckBox chckbxNewCheckBox = new JCheckBox("Calories burned (out)");
	JPanel buttonPane = new JPanel();
	JButton okButton = new JButton("OK");
	JButton cancelButton = new JButton("Cancel");
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	JPanel panel = new JPanel();
	
	JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Total distance");
	JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Floors climbed");
	JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Steps");
	JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Active minutes");
	JCheckBox chckbxSedentaryMinutes = new JCheckBox("Sedentary minutes");

	JLabel lblWhat = new JLabel("Elements shown on your daily dashboard:");
	
	boolean[] selectionArray = new boolean[6];



	/**
	 * Create the dialog.
	 * @param p the parent dashboard
	 * @param modal whether the dashboard is modal or not
	 * @param checkMark the settings of the dashboard (Shown/Hidden)
     */
	public SettingsView(MainView p, boolean modal, boolean[] checkMark) {
		selectionArray = checkMark;
		setTitle("Settings");
		setModal(modal);
		setAlwaysOnTop(true);
		parent = p;
		setBounds(100, 100, 560, 350);
		setResizable(false);
		{
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						//calories
						if(chckbxNewCheckBox.isSelected()){
							selectionArray[0] = true;
						}
						else{
							selectionArray[0] = false;
						}
						//total distance
						if(chckbxNewCheckBox_1.isSelected()){
							selectionArray[1] = true;
						}
						else{
							selectionArray[1] = false;
						}
						
						//floors climbed
						if(chckbxNewCheckBox_2.isSelected()){
							selectionArray[2] = true;
						}
						else{
							selectionArray[2] = false;
						}
						//steps
						if(chckbxNewCheckBox_3.isSelected()){
							selectionArray[3] = true;
						}
						else{
							selectionArray[3] = false;
						}
						//active minutes
						if(chckbxNewCheckBox_4.isSelected()){
							selectionArray[4] = true;
						}
						else{
							selectionArray[4] = false;
						}
						//sedentary minutes
						if(chckbxSedentaryMinutes.isSelected()){
							selectionArray[5] = true;
						}
						else{
							selectionArray[5] = false;
						}
						parent.customizeDashboard(selectionArray);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton.setActionCommand("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				
				buttonPane.add(cancelButton);
			}
		}
		{
			{
				tabbedPane.addTab("Daily Dashboard", null, panel, null);
				
				chckbxNewCheckBox.setSelected(true);
				
				
				chckbxNewCheckBox_1.setSelected(true);
				
				chckbxNewCheckBox_2.setSelected(true);
				
				chckbxNewCheckBox_3.setSelected(true);
				
				chckbxNewCheckBox_4.setSelected(true);
				
				chckbxSedentaryMinutes.setSelected(true);
				
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxNewCheckBox)
								.addComponent(chckbxNewCheckBox_1)
								.addComponent(chckbxNewCheckBox_2)
								.addComponent(chckbxNewCheckBox_3)
								.addComponent(chckbxNewCheckBox_4)
								.addComponent(chckbxSedentaryMinutes)
								.addComponent(lblWhat))
							.addContainerGap(369, Short.MAX_VALUE))
				);
				gl_panel.setVerticalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(14)
							.addComponent(lblWhat)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxNewCheckBox)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxNewCheckBox_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxNewCheckBox_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxNewCheckBox_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxNewCheckBox_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxSedentaryMinutes)
							.addContainerGap(33, Short.MAX_VALUE))
				);
				panel.setLayout(gl_panel);
			}
			
			
		}
		chckbxNewCheckBox.setSelected(selectionArray[0]);
		
		chckbxNewCheckBox_1.setSelected(selectionArray[1]);
		
		chckbxNewCheckBox_2.setSelected(selectionArray[2]);
		
		chckbxNewCheckBox_3.setSelected(selectionArray[3]);
		
		chckbxNewCheckBox_4.setSelected(selectionArray[4]);
		
		chckbxSedentaryMinutes.setSelected(selectionArray[5]);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
				.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("About", null, panel_1, null);
		
		JLabel lblFitviewwe = new JLabel("FitViewer");
		lblFitviewwe.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		JLabel lblVersiona = new JLabel("Version: 1.00");
		
		JLabel lblBroughtToYou = new JLabel("Brought to you by UWO->2016->CS2212B->Team09");
		
		JLabel lblCopyrightcTeam = new JLabel(" © 2016 team09. All rights not reserved.");
		
		JLabel lblNewLabel = new JLabel("Paul Henderson, Winston Leung, Michael Song, Hashim Salem, Yuchen Wang, ");
		
		JLabel lblYitingWangHaoyu = new JLabel("Yiting Wang, Haoyu Gu, Taylor LeBlanc");
		
		JLabel lblUniversal = new JLabel("Minion images: © 2016 Universal Studios. All Rights Reserved.");
		lblUniversal.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUniversal)
						.addComponent(lblCopyrightcTeam)
						.addComponent(lblYitingWangHaoyu)
						.addComponent(lblNewLabel)
						.addComponent(lblBroughtToYou)
						.addComponent(lblVersiona)
						.addComponent(lblFitviewwe, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(26)
					.addComponent(lblFitviewwe, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblVersiona)
					.addGap(18)
					.addComponent(lblBroughtToYou)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(lblYitingWangHaoyu)
					.addGap(28)
					.addComponent(lblCopyrightcTeam)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUniversal)
					.addGap(9))
		);
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);
		
	}
}
