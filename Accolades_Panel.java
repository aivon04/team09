package ca.uwo.csd.cs2212.team09;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date; 
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/** Implements the panel to view accolades
 * @author Team 09
 *
 */
public class Accolades_Panel extends JPanel {
	
	
	private class cellRenderer extends JLabel implements ListCellRenderer<Object> {  
		ArrayList<ImageIcon> icons;   
		 public cellRenderer(ArrayList<ImageIcon> icons) {  
			 this.icons = icons;  
		 }  
		 
		 public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {  
			 setText(value.toString());  
			 setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));  
			 if (isSelected) {  
				 setBackground(new Color(0, 150, 136));  
				 setForeground(list.getSelectionForeground());  
			 } 
			 else {  
				 if (index % 2 == 1) {
					 setBackground(new Color(238, 238, 238)); 
				 }
				 else {
					 setBackground(list.getBackground()); 
				 }
				 setForeground(list.getForeground());  
			 }  
			
			 setIcon(Utils.getImageIconForList(icons.get(index).getImage()));  
			 setEnabled(list.isEnabled());  
			 setFont(list.getFont());  
			 setOpaque(true);  
			 return this;  
		 }  
		  
		}  

	private JList mainList = new JList();
	private JScrollPane scrollPane = new JScrollPane();
	
	ArrayList<String[]> newItems = new ArrayList<String []>(30);
	private ArrayList<String> titles = new ArrayList<String>();

	private ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
	
	private JLabel imageLbl = new JLabel("Click a accolade to see detail!");
	private final JLabel lblTitle = new JLabel("-");
	private final JLabel lblDetail = new JLabel("-");
	
	private JDatePickerImpl datePicker;
	private MainView parent;
	
	/**
	 * Creates the panel.
	 * @param p	the parent panel of the dashboard
	 */
	public Accolades_Panel(MainView p) {
		parent = p;
		lblDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
		);
		
		
		imageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel datePickerPanel = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(datePickerPanel, GroupLayout.PREFERRED_SIZE, 201, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
					.addGap(20))
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(lblDetail, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
						.addComponent(imageLbl, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
					.addGap(27))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(imageLbl, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, Short.MAX_VALUE)
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDetail, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(datePickerPanel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		UtilDateModel model=new UtilDateModel();
	    JDatePanelImpl datePanel = new JDatePanelImpl(model);
	    datePicker = new JDatePickerImpl(datePanel);
	    datePicker.setBounds(0, 0, 200, 100);
	    try {
	    	Date date; // your date
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(parent.df.parse(parent.currentDate));
	        int year = cal.get(Calendar.YEAR);
	        int month = cal.get(Calendar.MONTH);
	        int day = cal.get(Calendar.DAY_OF_MONTH);
	    	datePicker.getModel().setDate(year, month, day);
	    	datePicker.getModel().setSelected(true);
	    }
	    catch (Exception e) {
	    	System.out.println("Error");
	    }
	    
	    datePicker.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	if (parent.antiBanTimer != null) {
            		if (parent.antiBanTimer.isRunning()) {
            			
            	        try {
            	        	Date date; // your date
                	        Calendar cal = Calendar.getInstance();
							cal.setTime(parent.df.parse(parent.currentDate));
							int year = cal.get(Calendar.YEAR);
	            	        int month = cal.get(Calendar.MONTH);
	            	        int day = cal.get(Calendar.DAY_OF_MONTH);
	            	    	datePicker.getModel().setDate(year, month, day);
	            	    	datePicker.getModel().setSelected(true);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
						}
            	        
            	    	return;
            		}	
            	}
            	String currDate = datePicker.getModel().getYear() + "-" +
            					  (datePicker.getModel().getMonth()+1<10 ? "0"+(datePicker.getModel().getMonth()+1):(datePicker.getModel().getMonth()+1)) + "-" +
            					  (datePicker.getModel().getDay()<10 ? "0"+datePicker.getModel().getDay():datePicker.getModel().getDay());
                if (!parent.currentDate.equals(currDate)) {
                	Date now = new Date();
                	try {
                		if (!parent.df.parse(currDate).after(now)) {
                    		parent.updateTime(currDate);
                    	}
                		else {
                			Date date; // your date
                	        Calendar cal = Calendar.getInstance();
                	        cal.setTime(parent.df.parse(parent.currentDate));
                	        int year = cal.get(Calendar.YEAR);
                	        int month = cal.get(Calendar.MONTH);
                	        int day = cal.get(Calendar.DAY_OF_MONTH);
                	    	datePicker.getModel().setDate(year, month, day);
                	    	datePicker.getModel().setSelected(true);
                		}
                	}
                	catch (Exception err) {
                		
                	}
                }
            	//System.out.println(currDate);
            }
        });
	    
	    GroupLayout gl_datePickerPanel = new GroupLayout(datePickerPanel);
	    gl_datePickerPanel.setHorizontalGroup(
	    	gl_datePickerPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_datePickerPanel.createSequentialGroup()
	    			.addComponent(datePicker, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
	    			.addGap(22))
	    );
	    gl_datePickerPanel.setVerticalGroup(
	    	gl_datePickerPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_datePickerPanel.createSequentialGroup()
	    			.addGap(5)
	    			.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addContainerGap(11, Short.MAX_VALUE))
	    );
	    datePickerPanel.setLayout(gl_datePickerPanel);
		
		scrollPane.setViewportView(mainList);
		setLayout(groupLayout);
		
		mainList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					updatePriviewArea();
			    }
			}
        });
		
		newItems.clear();
		//Daily Accolades - Distance
		String[] item0 = {"Baby-step", "Distance","daliy","lock"};
		newItems.add(item0);
		String[] item1 = {"Trekker", "Distance","daliy","lock"};
		newItems.add(item1);
		String[] item2 = {"Fit-active", "Distance","daliy", "lock"};
		newItems.add(item2);
		
		//Daily Accolades - Clories
		String[] item3 = {"Awake", "Clories","daliy", "lock"};
		newItems.add(item3);
		String[] item4 = {"Active", "Clories","daliy", "lock"};
		newItems.add(item4);
		String[] item5 = {"Fit-Junkie", "Clories","daliy", "lock"};
		newItems.add(item5);
		
		//Daily Accolades - Floors

		String[] item6 = {"Climber","Floors","daliy", "lock"};
		newItems.add(item6);

		String[] item7 = {"No Elevators", "Floors","daliy", "lock"};
		newItems.add(item7);
		String[] item8 = {"Floor-It!", "Floors","daliy", "lock"};
		newItems.add(item8);
		
		//Daily Accolades - Steps
		String[] item9 = {"Toddler", "Steps","daliy","lock"};
		newItems.add(item9);
		String[] item10 = {"All Around", "Steps","daliy","lock"};
		newItems.add(item10);
		String[] item11 = {"Place to Go!", "Steps","daliy", "lock"};
		newItems.add(item11);
		
		String[] item12 = {"Motor-Vated!", "","daliy", "lock"};
		newItems.add(item12);
		String[] item13 = {"Day-off", "", "daliy","lock"};
		newItems.add(item13);
		
		//Lifetime Accolades - Distance
		String[] item14 = {"Threadmill Master", "Distance","Lifetime", "lock"};
		newItems.add(item14);
		String[] item15 = {"Going the Distance", "Distance", "Lifetime","lock"};
		newItems.add(item15);
		String[] item16 = {"The Flash", "Distance","Lifetime", "lock"};
		newItems.add(item16);
		
		//Lifetime Accolades - Calories
		String[] item17 = {"Counting Calories", "Calories","Lifetime", "lock"};
		newItems.add(item17);
		String[] item18 = {"Burn them All", "Calories","Lifetime","lock"};
		newItems.add(item18);
		String[] item19 = {"Call a Doctor!", "Calories","Lifetime","lock"};
		newItems.add(item19);
		
		//Lifetime Accolades - Floors
		String[] item20 = {"Elevated", "Floors","Lifetime", "lock"};
		newItems.add(item20);
		String[] item21 = {"Floor-ishing", "Floors", "Lifetime","lock"};
		newItems.add(item21);
		String[] item22 = {"Rochy Balboa", "Floors","Lifetime", "lock"};
		newItems.add(item22);
		
		//Lifetime Accolades - Steps
		String[] item23 = {"Step up", "Steps", "Lifetime","lock"};
		newItems.add(item23);
		String[] item24 = {"Step on it", "Steps","Lifetime", "lock"};
		newItems.add(item24);
		String[] item25 = {"Terry Fox!", "Steps","Lifetime", "lock"};
		newItems.add(item25);
		
		String[] item26 = {"The Immorable Object", "","Lifetime", "lock"};
		newItems.add(item26);
		String[] item27 = {"Middle Man", "","Lifetime", "lock"};
		newItems.add(item27);
		
		for (int i=0;i<newItems.size();i++) {
			addItem(newItems.get(i)[0], newItems.get(i)[3]=="lock" ? (new ImageIcon(getClass().getResource("/lock20.png"))) : (new ImageIcon(getClass().getResource("/gold_cup.png"))),i == newItems.size()-1 ? true: false);
		}
		
		DefaultListModel listModel = new DefaultListModel();  
		for(int i=0;i<titles.size();i++){  
			listModel.add(i, titles.get(i));  
		}  
		mainList.setModel(listModel);
		mainList.setCellRenderer(new cellRenderer(images));  
		mainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
	}
	
	/** Method to add items/accolades to the panel
	 * @param title title of the item/accolade
	 * @param image accompanying image of the item
	 * @param refreshNow true if item is to be refreshed
	 */
	private void addItem(String title, ImageIcon image, boolean refreshNow) {
		titles.add(title);
		images.add(image);
		if (refreshNow) {
			SwingUtilities.updateComponentTreeUI(mainList);
		}
	}
	
	/**
	 * Updates the panel
	 */
	private void updatePriviewArea() {
		if (mainList.getSelectedIndex() < 0) {
			imageLbl.setIcon(null);
			imageLbl.setText("Click a accolade to see detail!");
			lblTitle.setText("-");
			lblDetail.setText("-");
			return;
		}
		imageLbl.setText("");
		lblTitle.setText(titles.get(mainList.getSelectedIndex()));
		lblDetail.setText("<html><p align=center style=\"width:100px\">"+"Status: " + newItems.get(mainList.getSelectedIndex())[3] + "ed<br><br>"+
		(newItems.get(mainList.getSelectedIndex())[3].equals("unlock") ? newItems.get(mainList.getSelectedIndex())[4] : "Secret")+"</p></html>");
		
		Utils.styleSquareImageButton(imageLbl, images.get(mainList.getSelectedIndex()).getImage(), 150);
		resizeFont(lblTitle);
	}
	
	/** Method used to resize the font
	 * @param label the label of the font
	 */
	private void resizeFont(JLabel label) {
		Font labelFont = label.getFont();
		String labelText = label.getText();

		int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = label.getWidth();

		// Find out how much the font can grow in width.
		double widthRatio = (double)componentWidth / (double)stringWidth;

		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = label.getHeight();

		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		// Set the label's font size to the newly determined size.
		label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
	}
	
	/**
	 * Method used to update the time
	 */
	public void updateTime() {
		try {
	    	Date date; // your date
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(parent.df.parse(parent.currentDate));
	        int year = cal.get(Calendar.YEAR);
	        int month = cal.get(Calendar.MONTH);
	        int day = cal.get(Calendar.DAY_OF_MONTH);
	    	datePicker.getModel().setDate(year, month, day);
	    	datePicker.getModel().setSelected(true);
	    }
	    catch (Exception e) {
	    	System.out.println("Error");
	    }
	}

	/** Draws the accolades panel
	 * @param acc an array of accolades to be displayed
	 */
	public void drawData(AccAchievement[] acc){
		newItems.clear();
		titles.clear();
		images.clear();
		
		String[] item0 = {"Baby-step", "Distance","daliy",acc[0].isTier1() ? "unlock" : "lock", "Ran a distance of 2km."};
		newItems.add(item0);
		String[] item1 = {"Trekker", "Distance","daliy",acc[0].isTier1() ? "unlock" : "lock", "Ran a distance of 7km."};
		newItems.add(item1);
		String[] item2 = {"Fit-active", "Distance","daliy", acc[0].isTier1() ? "unlock" : "lock", "Ran a distance of 15km."};
		newItems.add(item2);
		
		//Daily Accolades - Clories
		String[] item3 = {"Awake", "Clories","daliy", acc[1].isTier1() ? "unlock" : "lock","Burned 2000 Calories."};
		newItems.add(item3);
		String[] item4 = {"Active", "Clories","daliy", acc[1].isTier1() ? "unlock" : "lock","Burned 2500 Calories."};
		newItems.add(item4);
		String[] item5 = {"Fit-Junkie", "Clories","daliy", acc[1].isTier1() ? "unlock" : "lock","Burned 3000 Calories."};
		newItems.add(item5);
		
		//Daily Accolades - Floors
		String[] item6 = {"Climber", "Floors","daliy", acc[2].isTier1() ? "unlock" : "lock","climbed 5 floors."};
		newItems.add(item6);
		String[] item7 = {"No Elevators  ", "Floors","daliy", acc[2].isTier1() ? "unlock" : "lock", "climbed 15 floors."};
		newItems.add(item7);
		String[] item8 = {"Floor-It!", "Floors","daliy", acc[2].isTier1() ? "unlock" : "lock","climbed 25 floors."};
		newItems.add(item8);
		
		//Daily Accolades - Steps
		String[] item9 = {"Toddler", "Steps","daliy",acc[3].isTier1() ? "unlock" : "lock", "Took 1000 steps."};
		newItems.add(item9);
		String[] item10 = {"All Around", "Steps","daliy",acc[3].isTier1() ? "unlock" : "lock", "Took 2500 steps."};
		newItems.add(item10);
		String[] item11 = {"Place to Go!  ", "Steps","daliy", acc[3].isTier1() ? "unlock" : "lock", "Took 3500 steps."};
		newItems.add(item11);
		
		String[] item12 = {"Motor-Vated!  ", "","daliy", acc[4].isTier1() ? "unlock" : "lock","At the point today you were very active longer than lightly active."};
		newItems.add(item12);
		String[] item13 = {"Day-off", "", "daliy",acc[5].isTier1() ? "unlock" : "lock", "Your sedentary minutes are more than 250 min."};
		newItems.add(item13);
		
		//Lifetime Accolades - Distance
		String[] item14 = {"Threadmill Master  ", "Distance","Lifetime", acc[6].isTier1() ? "unlock" : "lock", "Ran over 500,000km."};
		newItems.add(item14);
		String[] item15 = {"Going the Distance  ", "Distance", "Lifetime",acc[6].isTier1() ? "unlock" : "lock", "Ran over 750,000km."};
		newItems.add(item15);
		String[] item16 = {"The Flash", "Distance","Lifetime", acc[6].isTier1() ? "unlock" : "lock", "Ran over 1,000,000km."};
		newItems.add(item16);
		
		//Lifetime Accolades - Calories
		String[] item17 = {"Counting Calories  ", "Calories","Lifetime", acc[7].isTier1() ? "unlock" : "lock", "Burned over 750,000 Calories."};
		newItems.add(item17);
		String[] item18 = {"Burn them All  ", "Calories","Lifetime",acc[7].isTier1() ? "unlock" : "lock", "Burned over 1,000,000 Calories."};
		newItems.add(item18);
		String[] item19 = {"Call a Doctor!  ", "Calories","Lifetime",acc[7].isTier1() ? "unlock" : "lock", "Burned over 1,500,000 Calories."};
		newItems.add(item19);
		
		//Lifetime Accolades - Floors
		String[] item20 = {"Elevated", "Floors","Lifetime", acc[8].isTier1() ? "unlock" : "lock","Climbed 100,000 floors."};
		newItems.add(item20);
		String[] item21 = {"Floor-ishing  ", "Floors", "Lifetime",acc[8].isTier1() ? "unlock" : "lock","Climbed 300,000 floors."};
		newItems.add(item21);
		String[] item22 = {"Rochy Balboa  ", "Floors","Lifetime", acc[8].isTier1() ? "unlock" : "lock","Climbed 600,000 floors."};
		newItems.add(item22);
		
		//Lifetime Accolades - Steps
		String[] item23 = {"Step up", "Steps", "Lifetime",acc[9].isTier1() ? "unlock" : "lock", "Took 3,000,000 steps."};
		newItems.add(item23);
		String[] item24 = {"Step on it", "Steps","Lifetime", acc[9].isTier1() ? "unlock" : "lock", "Took 7,500,000 steps."};
		newItems.add(item24);
		String[] item25 = {"Terry Fox!", "Steps","Lifetime", acc[9].isTier1() ? "unlock" : "lock", "Took 10,000,000 steps."};
		newItems.add(item25);
		
		String[] item26 = {"The Immorable Object  ", "","Lifetime", acc[10].isTier1() ? "unlock" : "lock", "Sedentary minutes are greater than 2,500,000."};
		newItems.add(item26);
		String[] item27 = {"Middle Man", "","Lifetime", acc[11].isTier1() ? "unlock" : "lock","Fairly active minutes 1,800,000."};
		newItems.add(item27);
		
		for (int i=0;i<newItems.size();i++) {
			addItem(newItems.get(i)[0], newItems.get(i)[3]=="lock" ? (new ImageIcon(getClass().getResource("/lock20.png"))) : (new ImageIcon(getClass().getResource("/gold_cup.png"))),i == newItems.size()-1 ? true: false);
		}
		
		DefaultListModel listModel = new DefaultListModel();  
		for(int i=0;i<titles.size();i++){  
			listModel.add(i, titles.get(i));  
		}  
		mainList.clearSelection();
		mainList.removeAll();
		
		mainList.setModel(listModel);
		mainList.setCellRenderer(new cellRenderer(images));  
		mainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
	}
}
