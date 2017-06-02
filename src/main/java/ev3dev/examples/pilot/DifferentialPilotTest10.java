package ev3dev.examples.pilot;

import ev3dev.sensors.ev3.EV3UltrasonicSensor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.MoveController;

import java.io.IOException;

public class DifferentialPilotTest10 {

  MoveController pilot;
  EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S1);
  SampleProvider bump = us.getDistanceMode();
  float[] sample = new float[1];
 
  public void go() {
    pilot.forward();
    while (pilot.isMoving()) {
      bump.fetchSample(sample, 0);
      if (sample[0] < 20) pilot.stop();
    }
    float dist = pilot.getMovement().getDistanceTraveled();
    System.out.println("Distance = " + dist);
    pilot.travel(-dist);
  }
 
  public static void main(String[] args) throws IOException {

    final DifferentialPilotTest10 traveler = new DifferentialPilotTest10();
    final PilotConfig pilotConf = new PilotConfig();
    final DifferentialPilot pilot = pilotConf.getPilot();

    traveler.pilot = pilot;
    traveler.go();
  }
}