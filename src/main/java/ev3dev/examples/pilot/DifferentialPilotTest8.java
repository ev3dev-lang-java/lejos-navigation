package ev3dev.examples.pilot;

import ev3dev.actuators.ev3.motors.Motor;
import ev3dev.sensors.Battery;
import ev3dev.sensors.ev3.EV3GyroSensor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.DirectionFinderAdapter;
import lejos.robotics.localization.CompassPoseProvider;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

public @Slf4j class DifferentialPilotTest8 {

    public static void main(final String[] args) throws IOException {

        PilotConfig pilotConf = new PilotConfig();
        final DifferentialPilot pilot = pilotConf.getPilot();

        OdometryPoseProvider pp = new OdometryPoseProvider(pilot);
        Navigator navigator = new Navigator(pilot, pp);

        navigator.goTo(0,0);
        navigator.goTo(100,0);
        navigator.goTo(50,50);
        navigator.goTo(100,-50);
        navigator.followPath();
        navigator.waitForStop();

        System.out.println(pp.getPose().getHeading());
        System.out.println(pp.getPose().getX());
        System.out.println(pp.getPose().getY());

        log.info("Voltage: {}", Battery.getInstance().getVoltage());

    }

}
