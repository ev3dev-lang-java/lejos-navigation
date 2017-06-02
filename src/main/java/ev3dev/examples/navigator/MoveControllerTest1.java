package ev3dev.examples.navigator;

import ev3dev.examples.pilot.PilotConfig;
import ev3dev.sensors.Battery;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

public @Slf4j class MoveControllerTest1 {

	public static void main(String[] args) throws IOException {

		PilotConfig pilotConf = new PilotConfig();
		final DifferentialPilot pilot = pilotConf.getPilot();

		final OdometryPoseProvider odometry = new OdometryPoseProvider(pilot);
		final Navigator navigator = new Navigator(pilot, odometry); // cria um navegador para indicar Path's

		Waypoint waypoint = new Waypoint(0, 0);
		navigator.addWaypoint(waypoint);
		waypoint = new Waypoint(20, 0);
		navigator.addWaypoint(waypoint);
		waypoint = new Waypoint(20, 20);
		navigator.addWaypoint(waypoint);
		waypoint = new Waypoint(20, 0);
		navigator.addWaypoint(waypoint);
		waypoint = new Waypoint(0, 0);
		navigator.addWaypoint(waypoint);

		navigator.followPath();
		navigator.waitForStop();

		show(navigator.getPoseProvider().getPose());

		log.info("Voltage: {}", Battery.getInstance().getVoltage());

	}

	private static void show(Pose p) {
		log.info("Pose X " + p.getX());
		log.info("Pose Y " + p.getY());
		log.info("Pose V " + p.getHeading());
	}
}