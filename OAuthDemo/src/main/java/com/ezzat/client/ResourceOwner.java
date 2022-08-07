package com.ezzat.client;
import javax.swing.*;
public class ResourceOwner {
	public static String getUserAuthorization() {
		
		 if(JOptionPane.showConfirmDialog (null, "For resource Owner , Do you accept Authorization request from on port ?","Resource Owner Approval", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	        	return "Yes";
	        }
	        else return "No";	
		}

}
