package tp.utiles;


import org.mindrot.jbcrypt.BCrypt;

public class AppUtils {
	
	/**
	 * Permet d'encrypter
	 * @param word
	 * @return Le mot avec l'initiale en majuscule
	 */
	public static String capitalizeWord(String word) {
		if(word.isEmpty() || word == null) {
			return word;
		}
		else {
			return word.substring(0,1).toUpperCase() + word.substring(1);
		}
	}
	
	public static String hashPassword(String mdp) {
		return BCrypt.hashpw(mdp, BCrypt.gensalt());
	}
	
	/**
	 * Permet de vérifier
	 * @param mdp
	 * @param mdpHache
	 * @return
	 */
	public static boolean checkPassword(String mdp, String mdpHache) {
		return BCrypt.checkpw(mdp, mdpHache); 
	}
	
}
