package ev3dev.examples;

import ev3dev.actuators.ev3.motors.Motor;
import ev3dev.sensors.Button;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;

public class NavigatorTest {

	private static final int TireDiameterMm = 64;
    private static final int TrackWidthMm = 144;
	private static double RobotMoveSpeed;
	private static double RobotRotateSpeed;

	public static void main(String[] args) {

		DifferentialPilot pilot = new DifferentialPilot(TireDiameterMm,
				TrackWidthMm, Motor.C, Motor.A, false);
		Navigator nav = new Navigator(pilot);
		/*
		RobotMoveSpeed = pilot.getMaxTravelSpeed() * 0.2;
		RobotRotateSpeed = pilot.getMaxRotateSpeed() * 0.1;
		pilot.setTravelSpeed(RobotMoveSpeed);
		pilot.setRotateSpeed(RobotRotateSpeed);
		*/
		OdometryPoseProvider pp = new OdometryPoseProvider(pilot);
		pilot.addMoveListener(pp);

		Button.waitForAnyPress();

		// Create path
		nav.addWaypoint(new Waypoint(200, 0));
		nav.addWaypoint(new Waypoint(200, 200));
		nav.addWaypoint(new Waypoint(0, 200));
		nav.addWaypoint(new Waypoint(0, 0));
		nav.followPath();

		// Print final position
		Pose pos = pp.getPose();
		//LCD.clear();
		System.out.println("Final pose is:");
		System.out.println(pos);

		Button.waitForAnyPress();

	}

}