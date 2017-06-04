# lejos-navigation

This library represent the local navigation stack from LeJOS project.

## What packages include?

The packages included in this library are:

- lejos.robotics.localization
- lejos.robotics.mapping
- lejos.robotics.navigation
- lejos.robotics.objectdetection
- lejos.robotics.pathfinding

## Getting Started

To start a new project with this library, add the following repository and dependency.

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

``` xml
<dependency>
    <groupId>com.github.ev3dev-lang-java</groupId>
    <artifactId>lejos-navigation</artifactId>
    <version>v0.1.0</version>
</dependency>
```
	
Further information about the Maven dependency: 
https://jitpack.io/#ev3dev-lang-java/lejos-navigation/v0.1.0

Now if you use EV3Dev + EV3Dev-lang-java, it is possible to enjoy with this kind of examples:

``` java 

import ev3dev.utils.PilotProps;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

import java.io.IOException;

public class PilotConfig {

    private DifferentialPilot pilot;

    public PilotConfig() throws IOException {

        PilotProps pp = new PilotProps();
        pp.loadPersistentValues();
        float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER));
        float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH));
        RegulatedMotor leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR));
        RegulatedMotor rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR));
        boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE));

        //Special Stop modes from EV3Dev
        leftMotor.brake();
        rightMotor.brake();

        System.out.println("Any button to start");

        pilot = new DifferentialPilot(wheelDiameter, trackWidth, leftMotor, rightMotor, reverse);
        //pilot.setAngularAcceleration();
        pilot.setAngularSpeed(100);
        //pilot.setLinearAcceleration();
        pilot.setLinearSpeed(100);
        int accel = 60;
        //pilot.setAcceleration(accel);
    }

    public DifferentialPilot getPilot(){
        return pilot;
    }
}
```

``` java

import ev3dev.sensors.Button;
import ev3dev.sensors.ev3.EV3UltrasonicSensor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RangeFinderAdapter;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.RangeFeatureDetector;

import java.io.IOException;

public class FeatureAvoider2 {
     
    static final float MAX_DISTANCE = 50f;
    static final int DETECTOR_DELAY = 1000;
 
    public static void main(String[] args) throws IOException {

        final PilotConfig pilotConf = new PilotConfig();
        final DifferentialPilot pilot = pilotConf.getPilot();

        final EV3UltrasonicSensor ir = new EV3UltrasonicSensor(SensorPort.S1);
        final RangeFeatureDetector detector = new RangeFeatureDetector(
                new RangeFinderAdapter(ir.getDistanceMode()), MAX_DISTANCE, DETECTOR_DELAY);

        detector.enableDetection(true);
        pilot.forward();
         
        detector.addListener(new FeatureListener() {
            public void featureDetected(Feature feature, FeatureDetector detector) {
                detector.enableDetection(false);
                pilot.travel(-30);
                pilot.rotate(30);
                detector.enableDetection(true);
                pilot.forward();
            }       
        });

        Button.waitForAnyPress();
    }
}
```

## UML Diagram

![](https://github.com/ev3dev-lang-java/lejos-navigation/raw/develop/docs/uml/graph.png)

## Open issues

- Chassis package is not included (Speak with Aswin & Andy)
- DifferentialPilot: (I have doubts with methods with arcs)
- Fusor detector (Possible concurrency problems)
