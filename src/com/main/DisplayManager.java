package com.main;

import com.gui.Screen1;
import com.gui.Screen2;
import com.gui.Screen3;
import com.gui.Screen4;

public class DisplayManager {
	private FlightManager mgr;
	private Screen1 screen1;
    private Screen2 screen2;
    private Screen3 screen3;
    private Screen4 screen4;
    public DisplayManager(FlightManager mgr)     {
        this.setMgr(mgr);
        screen1 = new Screen1(this);
        screen2 = new Screen2(this);
        screen3 = new Screen3(this);
        screen4 = new Screen4(this);
    }
   
    public void showScreen1(DisplayManager dmgr) {
    	screen1.setDMgr(dmgr);
        screen2.setVisible(false);
        screen3.setVisible(false);
        screen4.setVisible(false);
        screen1.setVisible(true);
    }
   
    public void showScreen2(DisplayManager dmgr) {
    	screen2.setDMgr(dmgr);
    	screen1.setVisible(false);
        screen3.setVisible(false);
        screen4.setVisible(false);
        screen2.setVisible(true);
        
    }
    
    public void showScreen3(DisplayManager dmgr) {
    	screen3.setDMgr(dmgr);
        screen1.setVisible(false);
        screen2.setVisible(false);
        screen4.setVisible(false);
        screen3.setVisible(true);
    }
    
    public void showScreen4(DisplayManager dmgr) {
    	screen4.setDMgr(dmgr);
        screen1.setVisible(false);
        screen2.setVisible(false);
        screen3.setVisible(false);
        screen4.setVisible(true);
	}
    
    public void exitFRS() {
        screen4.setVisible(false);
        System.exit(0);
    }

	public FlightManager getMgr() {
		return mgr;
	}

	public void setMgr(FlightManager mgr) {
		this.mgr = mgr;
	}
	
	
}
