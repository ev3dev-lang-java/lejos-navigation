package ev3dev.examples.navigator;

import ev3dev.examples.pilot.PilotConfig;
import ev3dev.sensors.Battery;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

public @Slf4j class NavigatorTest2 {

    public static void main(final String[] args) throws IOException {

        PilotConfig pilotConf = new PilotConfig();
        final DifferentialPilot pilot = pilotConf.getPilot();

        OdometryPoseProvider pp = new OdometryPoseProvider(pilot);
        Navigator navigator = new Navigator(pilot, pp);

        navigator.goTo(0,0);
        navigator.goTo(100,0);
        navigator.goTo(50,50);
        navigator.goTo(100,-50);
        navigator.goTo(0,0);
        navigator.followPath();
        navigator.waitForStop();

        System.out.println(pp.getPose());
        
        log.info("Voltage: {}", Battery.getInstance().getVoltage());

    }

}
