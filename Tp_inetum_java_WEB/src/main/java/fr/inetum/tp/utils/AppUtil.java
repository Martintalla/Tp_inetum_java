package fr.inetum.tp.utils;

import org.mindrot.jbcrypt.BCrypt;

public class AppUtil {

	/**
	 * 
	 * @param word
	 * @return
	 */
	public static String capitalize(String word) {
		if (word == null || word.isEmpty()) {
			return word;
		}
		return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
	}
	
	/**
	 * 
	 * @param mdp
	 * @return
	 */
	public static String hashPassword(String mdp) {
		return BCrypt.hashpw(mdp, BCrypt.gensalt());
	}
	
	/**
	 * 
	 * @param mdp
	 * @param mdpHache
	 * @return
	 */
	public static boolean checkPassword(String mdp, String mdpHache) {
		return BCrypt.checkpw(mdp, mdpHache);
	}
}
