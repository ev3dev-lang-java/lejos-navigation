package ev3dev.examples.pilot;

import ev3dev.actuators.ev3.motors.EV3LargeRegulatedMotor;
import ev3dev.actuators.ev3.motors.Motor;
import ev3dev.sensors.Battery;
import ev3dev.sensors.Button;
import ev3dev.utils.PilotProps;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;


import java.io.IOException;

public class PilotPropsTest {

    public static void main(String[] args) throws IOException, InterruptedException, DestinationUnreachableException {

		PilotProps pp = new PilotProps();
		pp.loadPersistentValues();
		float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER));
		float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH));
		RegulatedMotor leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR));
		RegulatedMotor rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR));
		boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE));

		//Special Stop modes from EV3Dev
		leftMotor.brake();
		rightMotor.brake();

		System.out.println("Any button to start");

		DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, leftMotor, rightMotor, reverse);
		Navigator nav = new Navigator(pilot);

		System.out.println(Battery.getInstance().getVoltage());

		//pilot.setAngularAcceleration();
		pilot.setAngularSpeed(100);
		//pilot.setLinearAcceleration();
		pilot.setLinearSpeed(100);

		pilot.travel(20);
		pilot.rotate(90);
		pilot.stop();
	}

}