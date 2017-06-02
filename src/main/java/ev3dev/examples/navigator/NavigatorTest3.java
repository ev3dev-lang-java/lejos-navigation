package ev3dev.examples.navigator;

import ev3dev.examples.pilot.PilotConfig;
import ev3dev.sensors.Battery;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;

import java.io.IOException;

public class NavigatorTest3 {

    public static void main(final String[] args) throws IOException {

        PilotConfig pilotConf = new PilotConfig();
        final DifferentialPilot pilot = pilotConf.getPilot();

        OdometryPoseProvider pp = new OdometryPoseProvider(pilot);
        Navigator navigator = new Navigator(pilot, pp);

        Path path = new Path();
        path.add(new Waypoint(0,0));
        path.add(new Waypoint(100,0));
        path.add(new Waypoint(50,50));
        path.add(new Waypoint(100,-50));
        path.add(new Waypoint(0,0));
        navigator.setPath(path);
        navigator.followPath();
        navigator.waitForStop();

        System.out.println(navigator.getPoseProvider().getPose());
        System.out.println("Voltage: " + Battery.getInstance().getVoltage());
    }

}
