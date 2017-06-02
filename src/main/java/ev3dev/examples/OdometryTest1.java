package ev3dev.examples;

import ev3dev.actuators.ev3.motors.EV3LargeRegulatedMotor;
import ev3dev.sensors.Battery;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lombok.extern.slf4j.Slf4j;

public @Slf4j class OdometryTest1 {

  public static void main(String[] args) throws Exception {

    final double wheelDiameter = 8.2;
    final double trackWidth = 12.6;

    /*
    final DifferentialPilot robot = new DifferentialPilot(
            wheelDiameter,
            trackWidth,
            Motor.A,
            Motor.B);
            */

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

    final OdometryPoseProvider odometry = new OdometryPoseProvider(pilot);

    //pilot.rotate(90);
    pilot.travel(10);
    pilot.arc(30, 30);
    //pilot.travel(50);
    
    log.info("End: {}", odometry.getPose());

    log.info("Voltage: {}", Battery.getInstance().getVoltage());

  }
  
}