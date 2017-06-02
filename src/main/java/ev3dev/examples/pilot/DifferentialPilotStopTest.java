package ev3dev.examples.pilot;

import ev3dev.actuators.ev3.motors.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lombok.extern.slf4j.Slf4j;

public @Slf4j class DifferentialPilotStopTest {

    public static void main(String[] args) {

		final double wheelDiameter = 8.2;
		final double trackWidth = 12.6;

        final RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
        final RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);

        //Special Stop modes from EV3Dev
        leftMotor.brake();
        rightMotor.brake();

        final DifferentialPilot pilot = new DifferentialPilot(
                wheelDiameter,
                trackWidth,
                leftMotor,
                rightMotor);
		final OdometryPoseProvider poseProvider = new OdometryPoseProvider(pilot);

        //pilot.setAngularAcceleration();
        pilot.setAngularSpeed(100);
        //pilot.setLinearAcceleration();
        pilot.setLinearSpeed(100);

		pilot.stop();

    }
}