package com.elezeta.roguecave.gui;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;


public final class RogueCaveLauncher {

	public static void main(String[] args) {
    	System.setProperty("java.library.path",
    			"lib/lwjgl-2.9.0/native/macosx"+File.pathSeparatorChar+
    			"lib/lwjgl-2.9.0/native/windows"+File.pathSeparatorChar+
    			"lib/lwjgl-2.9.0/native/linux"+File.pathSeparatorChar+
    			"RogueCave-build_lib"+File.pathSeparatorChar+
    			System.getProperty("java.library.path"));
    	Field fieldSysPath;
    	if (args.length>0) {
    		if (args[0].equals("--saveDefaultFiles")) {
    			try {
    				GUIConfig.writeDefault("config.cfg");
    			} catch (IOException e) {
    				e.printStackTrace();
    				System.exit(1);
    			}
    			System.exit(0);
    		}
    	}
		try {
			fieldSysPath = ClassLoader.class.getDeclaredField( "sys_paths" );
	    	fieldSysPath.setAccessible( true );
	    	fieldSysPath.set( null, null );
		} catch (Exception e) {
			e.printStackTrace();
		}

		RogueCaveGUI.start();
	}

}
