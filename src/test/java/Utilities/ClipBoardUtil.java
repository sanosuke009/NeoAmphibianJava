package Utilities;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ClipBoardUtil {
	
	private static final int controlkey = KeyEvent.VK_CONTROL;
	
	public static boolean copy(String txt){
		boolean res;
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(new StringSelection(txt), null);
            res = true;
        } catch (HeadlessException ex) {
        	System.out.println("Error occurred during copying "+txt+" in clipboard.");
        	res = false;
      }
        return res;
    }
	
	public static String get(){
		String res = "";
        try {
        	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        	DataFlavor dataflavor = DataFlavor.stringFlavor;
            if(clipboard.isDataFlavorAvailable(dataflavor))
            {
            	res = clipboard.getData(dataflavor).toString();
            }
        } catch (HeadlessException | UnsupportedFlavorException | IOException ex) {
        	System.out.println("Error occurred during getting the content of clipboard.");
        	res = "";
      }
        return res;
    }
	
	public static boolean paste(){
		boolean res;
        try {
            Robot robot = new Robot();
            robot.keyPress(controlkey);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(controlkey);
            res = true;
        } catch (AWTException ex) {
        	System.out.println("Error occurred during pasting the content of clipboard.");
        	res = false;
      }
        return res;
    }
	
	public static boolean copy(){
		boolean res;
        try {
            Robot robot = new Robot();
            robot.keyPress(controlkey);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(controlkey);
            res = true;
        } catch (AWTException ex) {
        	System.out.println("Error occurred during copying the content in clipboard.");
        	res = false;
      }
        return res;
    }

}