package ev3dev.examples.advanced;

import ev3dev.actuators.ev3.motors.Motor;
import ev3dev.sensors.ev3.EV3GyroSensor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;

public class EV3Tester {

    private DifferentialPilot p;
	private EV3GyroSensor gyro;
	private static SampleProvider angleProvider;
	private static OdometryPoseProvider opp;

//	@Test
	public void test() {
//		fail("Not yet implemented");
		EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S2);
//		Motor leftmotor = 
		DifferentialPilot pilot;
		// SegowayPilotDemo demo;
	}

	public void testMotors() {
		
	}

	public void testSensors() {
		p = new DifferentialPilot(5.6, 9.25, Motor.A, Motor.D);
//		lejos.hardware.port.Port port = LocalEV3.get().getPort("S2");
		//Port port2 = SensorPort.S2;
		gyro = new EV3GyroSensor(SensorPort.S2);
		angleProvider = gyro.getAngleMode();
		//p.setTravelSpeed(30);
		//p.setRotateSpeed(360);
		int accel = 60;
		//p.setAcceleration(accel);
		opp = new OdometryPoseProvider(p);
		gyro.reset();
		//Sound.beepSequenceUp();
		report("START acceleration " + accel);
		p.rotate(45);
		report("R 45");
		p.rotate(90);
		report("R 90");
		p.rotate(45);
		report("R 45");
		p.rotate(-180);
		report("R -180");
	}

	public void report(String message) {
		Delay.msDelay(100);
		float heading = opp.getPose().getHeading();
		float[] angle = { 0 };
		angleProvider.fetchSample(angle, 0);
		System.out.println(message + " H " + heading + " A " + angle[0]);
	}

}