package ev3dev.examples.advanced;

import lejos.robotics.navigation.DestinationUnreachableException;

public class Main2 {

    public static void main (String[] args) throws DestinationUnreachableException {

    	PilotConfiguration pilot = new PilotConfiguration();
		Naviguy naviG = new Naviguy(pilot);
		naviG.start();
	}

}