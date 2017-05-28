package ev3dev.examples.advanced;

import ev3dev.actuators.ev3.motors.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

public class PilotConfiguration {

	DifferentialPilot pilot = null;

	public PilotConfiguration() {

		final double wheelDiameter = 8.2;
		final double trackWidth = 12.6;
		RegulatedMotor leftMotor = Motor.A;
		RegulatedMotor rightMotor = Motor.B;

		//Special Stop modes from EV3Dev
		leftMotor.brake();
		rightMotor.brake();

		pilot = new DifferentialPilot(
				wheelDiameter,
				trackWidth,
				leftMotor,
				rightMotor);

		//pilot.setAngularAcceleration();
		pilot.setAngularSpeed(100);
		//pilot.setLinearAcceleration();
		pilot.setLinearSpeed(100);
	}

	public DifferentialPilot getPilot(){
		return pilot;
	}

}