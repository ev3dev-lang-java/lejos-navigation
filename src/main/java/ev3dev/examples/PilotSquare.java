package ev3dev.examples;

import ev3dev.actuators.ev3.motors.Motor;
import ev3dev.sensors.Battery;
import ev3dev.sensors.Button;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Pose;
import lejos.utility.Delay;
import lombok.extern.slf4j.Slf4j;

public @Slf4j class PilotSquare {


   public static void main(String [] args) throws Exception {

       final double wheelDiameter = 8.2;
       final double trackWidth = 12.6;
       RegulatedMotor leftMotor = Motor.A;
       RegulatedMotor rightMotor = Motor.B;

       //Special Stop modes from EV3Dev
       leftMotor.brake();
       rightMotor.brake();

       DifferentialPilot pilot = new DifferentialPilot(
               wheelDiameter,
               trackWidth,
               leftMotor,
               rightMotor);

       //pilot.setAngularAcceleration();
       pilot.setAngularSpeed(100);
       //pilot.setLinearAcceleration();
       pilot.setLinearSpeed(100);

       OdometryPoseProvider poseProvider = new OdometryPoseProvider(pilot);

       Pose initialPose = new Pose(0,0,0);
       poseProvider.setPose(initialPose);

       log.info("Pilot square");
	   
       for(int i = 0; i < 4; i++) {
           pilot.travel(20);           
           show(poseProvider.getPose());
    	   Delay.msDelay(1000);
                      
           pilot.rotate(90);
           show(poseProvider.getPose());
           Delay.msDelay(1000);
       }
       
       pilot.stop();

       log.info("Program stopped");
       log.info("Voltage: {}", Battery.getInstance().getVoltage());
   }

   private static void show(Pose p) {
        log.info("Pose X " + p.getX());
        log.info("Pose Y " + p.getY());
        log.info("Pose V " + p.getHeading());
   }

}