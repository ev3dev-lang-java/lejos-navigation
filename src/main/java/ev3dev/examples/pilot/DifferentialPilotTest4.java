package ev3dev.examples.pilot;

import ev3dev.sensors.Battery;
import lejos.robotics.navigation.DifferentialPilot;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

public @Slf4j class DifferentialPilotTest4 {

    public static void main(final String[] args) throws IOException {

        PilotConfig pilotConf = new PilotConfig();
        final DifferentialPilot pilot = pilotConf.getPilot();

        pilot.travel(20);
        pilot.rotate(90);

        log.info("Voltage: {}", Battery.getInstance().getVoltage());

    }
}
