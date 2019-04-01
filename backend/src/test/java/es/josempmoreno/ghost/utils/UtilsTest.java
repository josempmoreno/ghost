package es.josempmoreno.ghost.utils;

import org.junit.Assert;
import org.junit.Test;

import utils.Utils;

public class UtilsTest {
	
	Utils utils;
	
	@Test
	public void checkIfNumberRandomIsBetweenRange() {
		
		utils = new Utils();
		int number = utils.getRandomIntBetweenRange(50);
		boolean isValid = false;
		if(number<=50 || number>=0) {
			isValid = true;
		}
		Assert.assertTrue(isValid);
	}

}
