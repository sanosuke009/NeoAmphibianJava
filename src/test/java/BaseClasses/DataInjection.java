package BaseClasses;

import AtomicMethods.ElementActs;
import io.cucumber.java.Scenario;

public class DataInjection extends ElementActs{
	
	public DataInjection(Scenario sc) {
		scenario = sc;
	}

}
