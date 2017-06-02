package ev3dev.examples.pilot.failed;

import ev3dev.examples.pilot.PilotConfig;
import ev3dev.sensors.ev3.EV3GyroSensor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;

import java.io.IOException;

public class DifferentialPilotTest11 {

	static EV3GyroSensor gyro;
	static SampleProvider angleProvider;
	static OdometryPoseProvider opp;

	public static void main(String[] args) throws IOException {

		gyro = new EV3GyroSensor(SensorPort.S2);
		angleProvider = gyro.getAngleMode();

		PilotConfig pilotConf = new PilotConfig();
		final DifferentialPilot p = pilotConf.getPilot();

		opp = new OdometryPoseProvider(p);
		gyro.reset();

		p.rotate(45);
		report("R 45");
		p.rotate(90);
		report("R 90");
		p.rotate(45);
		report("R 45");
		p.rotate(-180);
		report("R -180");
	}

	public static void report(String message) {
		Delay.msDelay(100);
		float heading = opp.getPose().getHeading();

		float[] angle = { 0 };
		angleProvider.fetchSample(angle, 0);
		System.out.println(message + " H " + heading + " A " + angle[0]);
	}

}