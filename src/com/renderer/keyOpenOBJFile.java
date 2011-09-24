package com.renderer;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

import org.lwjgl.opengl.GL11;

import com.loader.WavefrontObject;

public class keyOpenOBJFile implements KeyBoardKeyHandler {

	public void onKeyPressed() {
		
		String filePath = null;
		
		 try {
			    // Set System L&F
		        UIManager.setLookAndFeel(
		            UIManager.getSystemLookAndFeelClassName());
		    } 
		    catch (UnsupportedLookAndFeelException e) {
		       // handle exception
		    }
		    catch (ClassNotFoundException e) {
		       // handle exception
		    }
		    catch (InstantiationException e) {
		       // handle exception
		    }
		    catch (IllegalAccessException e) {
		       // handle exception
		    }
		
		// Create a window
		JFrame frame = new JFrame("Chose an OBJ File");
		
		
		// Inject a FileChooser inside
		JFileChooser chooser = new JFileChooser();
		OBJFilter filter = new OBJFilter();
		
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
//		 Display it
		frame.add(chooser);
		frame.setVisible(true);
		
		int returnVal = chooser.showOpenDialog(frame);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	filePath = chooser.getSelectedFile().getAbsolutePath();
	    }

		// Once chose, set it
		frame.dispose();
	    
		if (filePath != null)
		{
			GL11.glDeleteLists(Starter.obj.displayListId, 1); 
			Starter.obj = new WavefrontObject(""+filePath);
		}
	}

	private class OBJFilter extends FileFilter
	{
		
		 public String getExtension(File f) {
		        String ext = null;
		        String s = f.getName();
		        int i = s.lastIndexOf('.');

		        if (i > 0 &&  i < s.length() - 1) {
		            ext = s.substring(i+1).toLowerCase();
		        }
		        return ext;
		    }

		@Override
		public boolean accept(File f) {
			
			
			
			 if (f.isDirectory()) 
				 return false;
				  

		    String extension = getExtension(f);
		    if (extension == null) 
		    	return false;
		    else
			if (extension.equals("obj"))   return true;
			

			return false;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "*.obj";
		}
		
	}
}
