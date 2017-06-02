package ev3dev.examples.navigator.failed;

import ev3dev.examples.pilot.PilotConfig;
import ev3dev.sensors.Battery;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

public @Slf4j class DifferentialPilotTest6 {

    private static Navigator navigator;
    private static OdometryPoseProvider pp;

    public static void main(final String[] args) throws IOException {

        PilotConfig pilotConf = new PilotConfig();
        final DifferentialPilot pilot = pilotConf.getPilot();

        pp = new OdometryPoseProvider(pilot);
        navigator = new Navigator(pilot, pp);

        for (int i = 0; i < 2; i++) {
            Move (50.0f, 0.0f);
            Move (50.0f, 50.0f);
            Move (0.0f, 50.0f);
            Move (0.0f, 0.0f);
            Pose tmp = new Pose(0.0f, 0.0f, 0.0f);
            pp.setPose(tmp);
        }

        log.info("Voltage: {}", Battery.getInstance().getVoltage());

    }

    private static void Move (float x, float y) {
        navigator.goTo(x, y);
        while (navigator.isMoving()) {
            System.out.println(pp.getPose());
        }
    }
}
