package com.mainone.util;

public class RandomTools {
	public static String getRandom(){
	StringBuffer ss = new StringBuffer();
	 for(int i = 0; i < 4; i++){
		 int t = (int)(Math.random()*10);
		 switch (t) {
		 case 1:
			ss.append((char)(Math.random ()*26+'a'));
			break;
		 case 2:
			 ss.append((char)(Math.random ()*26+'a'));
				break;
		 case 5:
			 ss.append((char)(Math.random ()*26+'a'));
				break;
		 case 8:
			 ss.append((char)(Math.random ()*26+'a'));
				break;
		 case 9:
			 ss.append((char)(Math.random ()*26+'a'));
				break;
		 default:
			 ss.append(t);
				break;
			}
		 }
	 return ss.toString();
	}

}
