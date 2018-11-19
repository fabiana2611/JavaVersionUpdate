package br.bia.upgrade.objective.localization;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Using property files we were using classes
 * @author fabiana
 *
 */
public class BundleExamples {

	 public static void init () {
		 System.out.println("\n#### Localization - bundle ####\n");
	        Locale spain = new Locale("es", "ES");
	        Locale spanish = new Locale("es");

	        ResourceBundle rb =
	            ResourceBundle.getBundle("br.bia.upgrade.objective.localization.bundles.MyBundle", spain);
	        System.out.format("%s %s\n",
	            rb.getString("hi"), rb.getString("s"));

	        rb = ResourceBundle.getBundle("br.bia.upgrade.objective.localization.bundles.MyBundle", spanish);
	        System.out.format("%s %s\n",
	            rb.getString("hi"), rb.getString("s"));
	        
	        System.out.println();
	    }

}