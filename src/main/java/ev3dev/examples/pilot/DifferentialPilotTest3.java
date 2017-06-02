package ev3dev.examples.pilot;

import ev3dev.actuators.ev3.motors.EV3LargeRegulatedMotor;
import ev3dev.sensors.Battery;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lombok.extern.slf4j.Slf4j;

public @Slf4j class DifferentialPilotTest3 {

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

        //pilot.setAngularAcceleration();
        pilot.setAngularSpeed(100);
        //pilot.setLinearAcceleration();
        pilot.setLinearSpeed(100);

		pilot.stop();
		pilot.rotate(45);
		pilot.rotate(45, true);//Pending
		pilot.travel(10);
		pilot.travel(10, true);
		pilot.setAngularSpeed(0);
		pilot.setAngularAcceleration(0);
		pilot.setLinearAcceleration(0);
		pilot.setAngularSpeed(0);
		pilot.setLinearSpeed(0);
		pilot.setMinRadius(0);
		pilot.addMoveListener(null);
		pilot.arc(0, 0);
		pilot.arc(0, 0, true);
		pilot.arcBackward(0);
		pilot.arcForward(0);
		pilot.reset();
		pilot.rotateLeft();
		pilot.rotateRight();
		pilot.steer(0);
		pilot.steer(0,0);
		pilot.steer(0,0,true);
		pilot.steerBackward(0);
		pilot.travelArc(0,10);
		pilot.travelArc(0,10,true);


		log.info("Voltage: {}", Battery.getInstance().getVoltage());
	}
}