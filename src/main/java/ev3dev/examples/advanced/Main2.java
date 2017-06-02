package ev3dev.examples.advanced;

import ev3dev.examples.pilot.PilotConfig2;
import lejos.robotics.navigation.DestinationUnreachableException;

public class Main2 {

    public static void main (String[] args) throws DestinationUnreachableException {

    	PilotConfig2 pilot = new PilotConfig2();
		Naviguy naviG = new Naviguy(pilot);
		naviG.start();
	}

}