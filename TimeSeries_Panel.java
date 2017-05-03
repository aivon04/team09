package ca.uwo.csd.cs2212.team09;


import javax.swing.JPanel;
import org.jfree.chart.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;



/** Implements the panel to display TimeSeries
 * @author Team 09
 *
 */
public class TimeSeries_Panel extends JPanel {

	/**
	 * Create the panel.
	 */
	private JPanel chartPanel = new JPanel();
	private String currDate;
	private ChartPanel cPanel;
	private MainView parent;
	private JTextField textField;
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private JComboBox<String> hourIntBox = new JComboBox<String>();
	private int currComboBoxIndex = 0;
	private String[] timeStr = {"", "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00",
									"07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", 
									"14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00",
									"21:00", "22:00", "23:00", "23:59"};
	private JFreeChart chart;
	
	private boolean isSelfUpdating = false;
	
	/** Default constructor for the TimeSeries panel
	 * @param p parent panel of the dashboard
	 */
	public TimeSeries_Panel(MainView p) {
		parent = p;
		Utils.stylePanel(this);
		currDate = df.format(new Date());
		String[] hL = getHourInterval();
		for (int i=0; i<hL.length;i++) {
			hourIntBox.addItem(hL[i]);
		}
		hourIntBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent.antiBanTimer != null) {
            		if (parent.antiBanTimer.isRunning()) {
            			textField.setText(currDate);
            			return;
            		}	
            	}
				if (hourIntBox.getSelectedIndex() != currComboBoxIndex) {
					if (hourIntBox.getSelectedIndex()<0)
						return;
					currComboBoxIndex = hourIntBox.getSelectedIndex();
					setNewDate((hourIntBox.getSelectedIndex() == 0?false:true),
							currDate, 
(hourIntBox.getSelectedIndex() == 0?parent.TIME_SERIES_INTERVAL_15_MIN:parent.TIME_SERIES_INTERVAL_1_MIN),
	(hourIntBox.getSelectedIndex() == 0?"":timeStr[hourIntBox.getSelectedIndex()]), 
	(hourIntBox.getSelectedIndex() == 0?"":timeStr[hourIntBox.getSelectedIndex()+1]), true);
				}
			}
		});
		hourIntBox.setSelectedIndex(0);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (parent.antiBanTimer != null) {
            		if (parent.antiBanTimer.isRunning()) {
            			textField.setText(currDate);
            			return;
            		}	
            	}
				if (setNewDate((hourIntBox.getSelectedIndex() == 0?false:true),
															textField.getText(), 
		(hourIntBox.getSelectedIndex() == 0?parent.TIME_SERIES_INTERVAL_15_MIN:parent.TIME_SERIES_INTERVAL_1_MIN),
									(hourIntBox.getSelectedIndex() == 0?"":timeStr[hourIntBox.getSelectedIndex()]), 
									(hourIntBox.getSelectedIndex() == 0?"":timeStr[hourIntBox.getSelectedIndex()+1]), true)) {
				}
				else {
					textField.setText("Format:yyyy-MM-dd");
					textField.selectAll();
				}
			}
		});
		
		JButton btnReset = new JButton("Reset");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parent.updateTime(parent.currentDate);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnOk)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnReset)
					.addPreferredGap(ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
					.addComponent(hourIntBox, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addComponent(chartPanel, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnOk)
								.addComponent(hourIntBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnReset)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chartPanel, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	/** Sets new date to be pull data
	 * @param zoomed true if zoomed, false otherwise
	 * @param date date of the data to be pulled
	 * @param detailLevel level of detail
	 * @param startTime starting time of the data
	 * @param endTime ending time of the data
	 * @param callback true if calling back
	 * @return
	 */
	public boolean setNewDate(boolean zoomed, String date, String detailLevel, String startTime, String endTime, boolean callback) {
		try {
			Date tmpDate = df.parse(date);
			Date now = new Date();
			if (tmpDate.after(now))
				return false;
			Date d = df.parse(date);
			String oldDate = currDate;
			currDate = df.format(d);
			
			boolean willRefresh = false;
			
			if (!oldDate.equals(currDate)) {
				hourIntBox.removeAllItems();
				String[] hL = getHourInterval();
				for (int i=0; i<hL.length;i++) {
					hourIntBox.addItem(hL[i]);
				}
				hourIntBox.setSelectedIndex(0);
				willRefresh = true;
			}
				
			if (callback) {
				isSelfUpdating = true;
				parent.getTSData(willRefresh ? false:zoomed, currDate, willRefresh ? parent.TIME_SERIES_INTERVAL_15_MIN:detailLevel, 
								willRefresh ? "":startTime, 
								willRefresh ? "":endTime);
				EventQueue.invokeLater(new Runnable() {
		            public void run() {
		            	parent.updateDataOnPanels();
		            }
				});
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/** Method to retrieve hour intervals
	 * @return array of strings of hour intervals
	 */
	private String[] getHourInterval() {
		
		Date date = new Date();
		String currD = df.format(date);
		if (!currDate.equals(currD)) {
			String[] rt = {"Whole day", "0:00", "1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", 
					"12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
					
			return rt;
		}
		String[] rt = new String[date.getHours()+(date.getHours()==0?2:1)];
		rt[0] = "Whole day";
		for (int i=0;i<date.getHours()+(date.getHours()==0?1:0);i++) {
			rt[1+i] = i + ":00";
		}
		
		return rt;
	}
	
	/** Draws the data to be displayed
	 * @param data array of TimeSeries
	 * @param date date of the data pulled
	 */
	public void drawData(TimeSeries_Record[] data, String date) {
		TimeSeries seriesSteps = new TimeSeries("Steps", Minute.class);
        TimeSeries seriesCalories = new TimeSeries("Calories (J)", Minute.class);
        TimeSeries seriesDistance = new TimeSeries("Distance (M)", Minute.class);
        TimeSeries seriesHr = new TimeSeries("Heart Rate", Minute.class);
        
        if (!isSelfUpdating) {
        	hourIntBox.removeAllItems();
    		String[] hL = getHourInterval();
    		for (int i=0; i<hL.length;i++) {
    			hourIntBox.addItem(hL[i]);
    		}
        }
        
        currDate = date;
        textField.setText(currDate);
        
        final Day day = new Day();
        for (int i=0;i<data.length;i++) {
        	Hour hour = new Hour(Integer.parseInt(data[i].getTime().substring(0, 2)), day);
        	Minute minute = new Minute(Integer.parseInt(data[i].getTime().substring(3, 5)), hour);
        	seriesSteps.add(minute, data[i].getSteps());
        	seriesCalories.add(minute, data[i].getCalories());
        	seriesDistance.add(minute, data[i].getDistance()*1000);
        	seriesHr.add(minute, data[i].getHr());
        }
        
        final TimeSeriesCollection dataset = new TimeSeriesCollection(seriesSteps);
        dataset.addSeries(seriesCalories);
        dataset.addSeries(seriesDistance);
        dataset.addSeries(seriesHr);
        chart = ChartFactory.createTimeSeriesChart(
            "Time Series on " + currDate,
            "Time", 
            "Value",
            dataset,
            true,
            true,
            false
        );
        if (cPanel != null) {
        	cPanel.setChart(chart);
        }
        else {
        	cPanel = new ChartPanel(chart);
        }
        cPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        GroupLayout gl_chartPanel = new GroupLayout(chartPanel);
        gl_chartPanel.setHorizontalGroup(
        	gl_chartPanel.createParallelGroup(Alignment.LEADING)
        		.addComponent(cPanel, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
        );
        gl_chartPanel.setVerticalGroup(
        	gl_chartPanel.createParallelGroup(Alignment.LEADING)
        		.addComponent(cPanel, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
        );
        chartPanel.setLayout(gl_chartPanel);
        isSelfUpdating = false;
	}
}
