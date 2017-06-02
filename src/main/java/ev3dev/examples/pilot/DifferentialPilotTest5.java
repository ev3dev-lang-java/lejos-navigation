package ev3dev.examples.pilot;

import ev3dev.sensors.Battery;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

public @Slf4j class DifferentialPilotTest5 {

    public static void main(final String[] args) throws IOException {

        PilotConfig pilotConf = new PilotConfig();
        final DifferentialPilot pilot = pilotConf.getPilot();

        OdometryPoseProvider pp = new OdometryPoseProvider(pilot);
        Navigator navigator = new Navigator(pilot, pp);

        navigator.singleStep(true);

        navigator.addWaypoint(0.0f, 40.0f, 0.0f);
        //navigator.addWaypoint(0.0f, 80.0f);
        //navigator.addWaypoint(-80.0f, 00.0f, 90.0f);

        navigator.followPath();
        navigator.waitForStop();

        log.info("Voltage: {}", Battery.getInstance().getVoltage());

    }
}
