package ev3dev.examples.advanced;

import ev3dev.actuators.Sound;
import ev3dev.actuators.ev3.motors.NXTRegulatedMotor;
import ev3dev.sensors.Button;
import ev3dev.sensors.ev3.EV3TouchSensor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Point;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.TouchFeatureDetector;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.ShortestPathFinder;

import java.io.IOException;
import java.util.ArrayList;

public class FeatureDetectionTest1 implements FeatureListener {

    static NXTRegulatedMotor m1 = new NXTRegulatedMotor(MotorPort.A),
			m2 = new NXTRegulatedMotor(MotorPort.C);
	static final DifferentialPilot pilot = new DifferentialPilot(5.77, 12.2,
			m2, m1);
	static final OdometryPoseProvider PP = new OdometryPoseProvider(pilot);
	static final Navigator n = new Navigator(pilot, PP);
	static final TouchFeatureDetector tfd = new TouchFeatureDetector(
			new EV3TouchSensor(SensorPort.S4), 0, 12.0);
	static final ArrayList<Line> lines = new ArrayList<Line>();

	static final ShortestPathFinder path = new ShortestPathFinder(new LineMap(new Line[0], new Rectangle(-1000, -1000, 2000, 2000)));

	public static void main(String[] args) {


        new FeatureDetectionTest1().go();

    }

	private void go() {
		try {


			n.addWaypoint(100, 0);
			n.followPath();
			//tfd.enableDetection(true);
			//tfd.addListener(this);

			try {
				new LineMap(((Line[]) path.getMap().toArray(new Line[path.getMap().size()])), new Rectangle(
						-500, -500, 1000, 1000)).createSVGFile("map.svg");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				Button.ESCAPE.waitForPress();
			}

		} catch (Throwable t) {
			//Sound.beepSequenceUp();
			t.printStackTrace();
		}
		
	}

	public void featureDetected(Feature feature, FeatureDetector detector) {
		pilot.stop();
		Sound.getInstance().beep();
		n.stop();
		n.clearPath();

		Point p = PP.getPose().getLocation();
		pilot.travel(-25);

		Line l1 = new Line(p.x - 25, p.y, p.x + 25, p.y);
		Line l2 = new Line(p.x, p.y - 25, p.x, p.y + 25);

		lines.add(l1);
		lines.add(l2);

		path.setMap(lines);
		
		try {
			Path np = path.findRoute(PP.getPose(), new Waypoint(100, 0));
			n.setPath(np);
			n.followPath();
		} catch (DestinationUnreachableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
