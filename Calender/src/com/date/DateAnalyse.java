package com.date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DateAnalyse {
    private String date;
    private JTable jTable1;
    //private Map<Integer, String> map = new HashMap<Integer, String>();
    
    public DateAnalyse(String date, JTable jTable1) {
    	this.date = date;
    	this.jTable1 = jTable1;
    }
    //@Test
    public void Analyse() {
    	String[] dates = date.split("/");
    	int year = Integer.parseInt(dates[0]);
    	int month = Integer.parseInt(dates[1]);
    	int day = Integer.parseInt(dates[2]);
    	/*//String date = "1994/01/15";
    	  	
    	//只适合于1582年（我国明朝万历十年）10月15日之后的情形
    	map.put(0, "星期日");
    	map.put(1, "星期一");
    	map.put(2, "星期二");
    	map.put(3, "星期三");
    	map.put(4, "星期四");
    	map.put(5, "星期五");
    	map.put(6, "星期六");
    	//转换为Integer
    	System.out.println(year);
    	System.out.println(month);
    	System.out.println(day);
    	int week;
    	//Zeller公式
    	int result = (year % 100) + (year % 100)/4 + (year/100)/4 - 2*(year/100) + (26*(month+1)/10) + day - 1;
    	if(result >= 0) 
    		week = result % 7;
    	else 
    		week = ((result % 7) + 7) % 7;*/
    	//获取基准时间
    	addTable(year, month, day);
        
        //计算往后推一周的时间
    	for(int i = 0; i < 7; i++) {
	    	day = day + 1;
	    	if(isLeapYear(year)){
	    		if(day > monthAmountLeap[month-1]) {
	    			month = month + 1;
	    			day = 1;
	    			if(month > 13) {
	    				year = year + 1;
	    				month = 1;
	    			}
	    		}
	    	}
	    	else {
	    		if(day > monthAmountLeap[month-1]) {
	    			month = month + 1;
	    			day = 1;
	    			if(month > 13) {
	    				year = year + 1;
	    				month = 1;
	    			}
	    		}
	    	}
	    	
	    	addTable(year, month, day);
    	}
        
        
        
    }
	public void addTable(int year, int month, int day) {
		ChineseCalendar calendar = new ChineseCalendar(false, year, month-1, day);
    	String result = calendar.toString();
        System.out.println(result);
    	
    	String[] results = result.split(" ");
    	
    	DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.addRow(new Object[] {
        		results[0],
        		results[1],
        		results[2],
        		results[3]
        		});
        jTable1.invalidate();
	}
    
    public static boolean isLeapYear(int year) {
		if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
			return true;
		else
            return false;  	
    }
    
    public static int monthAmountLeap[] = {
    	31,29,31,30,31,30,31,31,30,31,30,31
    };
    
    public static int monthAmountNormal[] = {
    	31,28,31,30,31,30,31,31,30,31,30,31
    };
}
