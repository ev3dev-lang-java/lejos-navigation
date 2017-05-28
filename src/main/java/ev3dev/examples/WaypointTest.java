package ev3dev.examples;

import ev3dev.actuators.ev3.motors.Motor;
import ev3dev.sensors.Battery;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lombok.extern.slf4j.Slf4j;

public @Slf4j class WaypointTest {

	public static void main(String[] args) {

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

		Navigator nav = new Navigator(pilot);

		OdometryPoseProvider opp = new OdometryPoseProvider(pilot);

		//some waypoints
		Waypoint finish = new Waypoint(50, 50, 0);

		//TODO: Mutable objects. Speak with Aswin
		float[] points = new float[opp.sampleSize()];
		int count = 0;

/*
		// establish a fail-safe: pressing Escape quits
		Brick brick = BrickFinder.getDefault(); // get specifics about this
		// robot
		brick.getKey("Escape").addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(Key k) {
				pilot.stop();
				us.close();
				eopd.close();
				Motor.A.close();	
				Motor.B.close();
				System.exit(1);
			}

			@Override
			public void keyReleased(Key k) {
				System.exit(1);
			}
		});
		*/

		pilot.travel(20);
		//pilot.rotate(45);

		pilot.stop();
		
		pilot.setLinearSpeed(10);  //sets straight ahead speed
		pilot.setAngularSpeed(50); //sets turning speed
		pilot.addMoveListener(opp);  //adds the listerner to capture x,y,heading values
		
		nav.goTo(finish);
		//pilot.steer(0);
		while ( !nav.pathCompleted() ){
			opp.fetchSample(points, 0);
			log.info("Count: {}, x: {}, y: {}, t: {}",  count, points[0], points[1], points[2]);
			count++;
		}

		pilot.stop();
		opp.fetchSample(points, 0);
		log.info("Count: {}, x: {}, y: {}, t: {}",  count, points[0], points[1], points[2]);

		log.info("Voltage: {}", Battery.getInstance().getVoltage());

	}

}