package utils;

import java.util.Random;

public final class Utils {
	
	public int getRandomIntBetweenRange(int max) {
		Random rand = new Random();
		return rand.nextInt(max);
	}

}
