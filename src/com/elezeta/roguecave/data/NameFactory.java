package com.elezeta.roguecave.data;

import java.util.Random;
import java.util.regex.Pattern;

import com.elezeta.roguecave.gui.RogueCaveGUI;

public final class NameFactory {

	private static Random r = new Random();

	private static Pattern p = Pattern.compile("^[A-Za-z]*$");

	private static String[] vowels = {"a","a","a","a","a",
				      "e","e","e","e","e","e",
				      "i","i","i",
				      "o","o","o","o",
				      "u","u","u",
				      "y",
				      "a","a","a","a","a",
				      "e","e","e","e","e","e",
				      "i","i","i",
				      "o","o","o","o",
				      "u","u","u",
				      "a","a","a","a","a",
				      "e","e","e","e","e","e",
				      "i","i","i",
				      "o","o","o","o",
				      "u","u","u",
				      "y",
				      "ae","ai","ao","au",
				      "ea","ei","eo","eu",
				      "ia","ie","io","iu",
				      "oa","oe","oi","ou",
				      "ua","ue","ui","uo",
				      };
	private static String[] startConsonants = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","y","z",
								"b","c","d","f","g","h","j","k","l","m","n","p","r","s","t","v",
								"b","c","d","f","g","h","j","k","l","m","n","p","r","s","t","v",
								"b","c","d","f","g","h","j","k","l","m","n","p","r","s","t","v",
								"b","c","d","f","g","h","j","k","l","m","n","p","r","s","t","v",
			                    "bl","bh","br","by",
			                    "cl","ch","cr","cy",
			                    "dl","dh","dr","dv","dy",
			                    "fl","fh","fr","fy",
			                    "gl","gh","gr","gy",
			                    "hr","hy",
			                    "jy",
			                    "kl","kh","kr","ky",
			                    "ll","lh","ly",
			                    "my",
			                    "ny",
			                    "pl","ph","pr","py",
			                    "qh","qr","qy","qu",
			                    "rh","rr","ry",
			                    "sl","sh","sr","sy",
			                    "tl","th","tr","ty",
			                    "vl","vh","vr","vy",
			                    "wl","wh","wr","wy",
			                    "xh","xy",
			                    "yl","yh","yr",
			                    "zh","zy",
			                    "","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""
			                    };
	private static String[] endConsonants = {"b","c","d","f","g","h","j","k","l","m","n","p","r","s","t","v","w","x","z",
							  "b","c","d","f","g","h","j","k","l","m","n","p","r","s","t","v","w","x","z",
							  //"rb","rc","rd","rf","rg","rj","rk","rl","rm","rn","rp","rs","rt","rz",
							  //"sf","sg","sk","sp","ss","st",
							  "","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",
							  };

	private NameFactory() {
	}

	public static String generateName() {
		String res = null;
		do {
			res = "";
			int syllabes = r.nextInt(3)+1;
			for (int i = 0;i < syllabes;i++)
				res+=generateSyllabe();
		} while (!checkGeneratedName(res));
		return capitalize(res);
	}
	
	public static String capitalize(String res) {
		if (res.length()==0)
			return res;
		return res.substring(0,1).toUpperCase()+res.substring(1).toLowerCase();
	}

	public static boolean checkGeneratedName(String str) {
		if (str.length()>7)
			return false;
		if (str.length()<2)
			return false;
		if (str.contains("yy"))
			return false;
		if (!p.matcher(str).find())
			return false;
		return checkNameSoft(str);
	}
	public static boolean checkName(String str) {
		if (str.length()>RogueCaveGUI.config.maxName)
			return false;
		if (str.length()<1)
			return false;
		if (!p.matcher(str).find())
			return false;
		return true;
	}

	public static boolean checkNameSoft(String str) {
		if (str.length()>RogueCaveGUI.config.maxName)
			return false;
		if (!p.matcher(str).find())
			return false;
		return true;
	}
	
	private static String generateSyllabe() {
		return getRandom(startConsonants)+getRandom(vowels)+getRandom(endConsonants);
	}
	
	
	private static String getRandom(String[] list) {
		return list[r.nextInt(list.length)];
	}

}
