package ev3dev.examples.advanced;

import ev3dev.examples.pilot.PilotConfig2;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.*;

public class Naviguy {
    static Rectangle area = null;

	static Line[] lines;
	static LineMap map;
	static FourWayGridMesh fwgm;
	private int waypointNum = 1;
	Waypoint w;
	Pose pose;
	PoseProvider posi;
	Navigator n;

	public Naviguy(PilotConfig2 p) {
		posi = new OdometryPoseProvider(p.getPilot());
		/// set position
		pose = new Pose(0, 0, 90);
		posi.setPose(pose);
		n = new Navigator(p.getPilot(), posi);
	}

	public FourWayGridMesh makeGrid() {

		lines = new Line[3];
		area = new Rectangle(0, 0, 137, 159);
		lines[0] = new Line(38, 65, 85, 65);

		lines[1] = new Line(85, 65, 85, 107);

		lines[2] = new Line(37, 120, 37, 159);
		map = new LineMap(lines, area);
		fwgm = new FourWayGridMesh(map, 10, 10);
		return fwgm;
	}

	public Path makePath(FourWayGridMesh fwgm) throws DestinationUnreachableException {
		AstarSearchAlgorithm algo = new AstarSearchAlgorithm();
		PathFinder pf = new NodePathFinder(algo, fwgm);
		switch (waypointNum) {
		case 1:
			w = new Waypoint(20, 20);
			break;
		case 2:
			w = new Waypoint(50, 50);
			break;
		case 3:
			w = new Waypoint(20, 20);
			break;
		}
		Path p = pf.findRoute(posi.getPose(), w);
		return p;
	}

	public int getWaypointNum() {
		return waypointNum;
	}

	public void start() throws DestinationUnreachableException {

		while (getWaypointNum() <= 3) {
			n.followPath(makePath(makeGrid()));
			n.waitForStop();
		}

	}
}