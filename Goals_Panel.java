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


/** Implements the panel to display goals
 * @author Team 09
 *
 */
public class Goals_Panel extends JPanel {
	
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
	
	private ArrayList<String> titles = new ArrayList<String>();
	private ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
	
	private JLabel imageLbl = new JLabel("Click a goal to see detail!");
	private final JLabel lblTitle = new JLabel("-");
	private final JLabel lblDetail = new JLabel("-");
	private final JPanel datePickerPanel = new JPanel();
	private JDatePickerImpl datePicker;
	
	private MainView parent;
	private String[] data;
	
	/**
	 * Creates the panel.
	 * @param p the parent panel of the dashboard
	 */
	public Goals_Panel(MainView p) {
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
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(imageLbl, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(datePickerPanel, GroupLayout.PREFERRED_SIZE, 181, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDetail, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(imageLbl, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDetail, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(datePickerPanel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(11))
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
            		if (parent.antiBanTimer.isRunning()){
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
	    			.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
	    			.addContainerGap(25, Short.MAX_VALUE))
	    );
	    gl_datePickerPanel.setVerticalGroup(
	    	gl_datePickerPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_datePickerPanel.createSequentialGroup()
	    			.addGap(5)
	    			.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
		
		//Demo only
		//TODO:dssdfsdfjioasdjfklajsdklfjalskdjfksajdfklasjdklfjasbkdvcliwl
		addItem("Calories Burned Goal", (new ImageIcon(getClass().getResource("/SS_Calories.png"))), false);
		addItem("Distance Traveled Goal", (new ImageIcon(getClass().getResource("/SS_Distance.png"))), false);
		addItem("Floors Climbed Goal", (new ImageIcon(getClass().getResource("/SS_Floors.png"))), false);
		addItem("Steps Taken Goal", (new ImageIcon(getClass().getResource("/SS_Steps.png"))), true);
		
		DefaultListModel listModel = new DefaultListModel();  
		for(int i=0;i<titles.size();i++){  
			listModel.add(i, titles.get(i));  
		}  
		mainList.setModel(listModel);
		mainList.setCellRenderer(new cellRenderer(images));  
		mainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
	}
	
	/** Method to add items to the panel
	 * @param title title of the item to be added
	 * @param image image of the item to be added
	 * @param refreshNow true if refreshing
	 */
	private void addItem(String title, ImageIcon image, boolean refreshNow) {
		titles.add(title);
		images.add(image);
		if (refreshNow) {
			SwingUtilities.updateComponentTreeUI(mainList);
		}
	}
	
	/**
	 * Method to update the display
	 */
	private void updatePriviewArea() {
		if (mainList.getSelectedIndex() < 0) {
			imageLbl.setIcon(null);
			imageLbl.setText("Click a goal to see detail!");
			lblTitle.setText("-");
			lblDetail.setText("-");
			lblDetail.setForeground(Color.black);
			return;
		}
		try {
			imageLbl.setText("");
			lblTitle.setText(titles.get(mainList.getSelectedIndex()));
			lblDetail.setText("<html><p align=center style=\"width:100px\">"+data[mainList.getSelectedIndex()*2+1]+"<br><br>"+data[8+mainList.getSelectedIndex()]+"</p></html>");
			if (data[mainList.getSelectedIndex()*2+1].equals("Below the goal") ) {
				lblDetail.setForeground(Color.red);
			}
			else if (data[mainList.getSelectedIndex()*2+1].equals("Reached the goal") ) {
				lblDetail.setForeground(Color.green);
			}
			else if (data[mainList.getSelectedIndex()*2+1].equals("Surpassed goal") ) {
				lblDetail.setForeground(Color.blue);
			}
			
		}
		catch (Exception e) {
			lblDetail.setText("Unknown status.");
		}
		Utils.styleSquareImageButton(imageLbl, images.get(mainList.getSelectedIndex()).getImage(), 150);
		resizeFont(lblTitle);
	}
	
	/** Method to draw the data on the panel
	 * @param d string of data to be displayed
	 */
	public void drawData(String[] d) {
		if (d.length == 0)
			return;
		mainList.removeAll();
		titles.clear();
		images.clear();
		
		data = d;
		
		for (int i=0;i<=6;i+=2) {
			String picName;
			if (i==0) {
				picName = "/SS_Calories.png";
			}
			else if (i==2) {
				picName = "/SS_Distance.png";
			}
			else if (i==4) {
				picName = "/SS_Floors.png";
			}
			else {
				picName = "/SS_Steps.png";
			}
			addItem(d[i], (new ImageIcon(getClass().getResource(picName))), 
					i==data.length-2 ? true: false);
		}
		
		DefaultListModel listModel = new DefaultListModel();  
		for(int i=0;i<titles.size();i++){  
			listModel.add(i, titles.get(i));  
		}  
		mainList.setModel(listModel);
		mainList.setCellRenderer(new cellRenderer(images));  
		mainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
	}
	
	/** Method used to resize the font
	 * @param label accompanying JLabel
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
	 * Method used to update the time of the display
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
}
