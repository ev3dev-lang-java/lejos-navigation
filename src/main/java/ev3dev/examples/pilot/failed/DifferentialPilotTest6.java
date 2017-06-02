package ev3dev.examples.pilot.failed;

import ev3dev.examples.pilot.PilotConfig;
import ev3dev.sensors.Battery;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lombok.extern.slf4j.Slf4j;

public @Slf4j class DifferentialPilotTest6 {

  public static void main(String[] args) throws Exception {

    PilotConfig pilotConf = new PilotConfig();
    final DifferentialPilot pilot = pilotConf.getPilot();

    final OdometryPoseProvider odometry = new OdometryPoseProvider(pilot);

    //pilot.rotate(90);
    pilot.travel(10);
    pilot.arc(30, 30);
    //pilot.travel(50);
    
    log.info("End: {}", odometry.getPose());

    log.info("Voltage: {}", Battery.getInstance().getVoltage());

  }
  
}