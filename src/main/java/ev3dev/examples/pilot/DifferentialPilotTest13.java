package ev3dev.examples.pilot;

import ev3dev.actuators.ev3.motors.Motor;
import ev3dev.sensors.Battery;
import ev3dev.sensors.ev3.EV3GyroSensor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.DirectionFinder;
import lejos.robotics.DirectionFinderAdapter;
import lejos.robotics.SampleProvider;
import lejos.robotics.localization.CompassPoseProvider;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;

import java.io.IOException;

public class DifferentialPilotTest13 {

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

        System.out.println(navigator.getPoseProvider().getPose().getHeading());
        System.out.println(navigator.getPoseProvider().getPose().getX());
        System.out.println(navigator.getPoseProvider().getPose().getY());

        System.out.println("Voltage: " + Battery.getInstance().getVoltage());
    }

}
