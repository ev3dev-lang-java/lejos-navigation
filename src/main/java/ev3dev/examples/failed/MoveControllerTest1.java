package ev3dev.examples.failed;

import ev3dev.actuators.ev3.motors.EV3LargeRegulatedMotor;
import ev3dev.actuators.ev3.motors.Motor;
import ev3dev.sensors.Battery;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.*;
import lombok.extern.slf4j.Slf4j;

public @Slf4j class MoveControllerTest1 {

	public static void main(String[] args) {

		final double wheelDiameter = 8.2;
		final double trackWidth = 12.6;

		//TODO Pending to add. Speak with Aswin
		//Wheel rodaE =  WheeledChassis.modelWheel(Motor.A, diameter).offset(- offset);//cria um Modeler da roda esquerda
		//Wheel rodaD =  WheeledChassis.modelWheel(Motor.B, diameter).offset(offset);;//cria um Modeler da roda direita
		//Wheel[] rodas = {rodaE,rodaD};// cria um vetor das rodas
		//Chassis chassis = new WheeledChassis(rodas, WheeledChassis.TYPE_DIFFERENTIAL);// cria um chassis
		//MoveController pilot = new MovePilot(chassis);// cria um pilot para robo andar

		/*
		final MoveController pilot = new DifferentialPilot(
				wheelDiameter,
				trackWidth,
				Motor.A,
				Motor.B);
		*/

		final RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		final RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);

		//Special Stop modes from EV3Dev
		leftMotor.brake();
		rightMotor.brake();

		final DifferentialPilot pilot = new DifferentialPilot(
				wheelDiameter,
				trackWidth,
				leftMotor,
				rightMotor);

		//pilot.setAngularAcceleration();
		pilot.setAngularSpeed(100);
		//pilot.setLinearAcceleration();
		pilot.setLinearSpeed(100);

		final OdometryPoseProvider odometro = new OdometryPoseProvider(pilot);
		final Navigator navegacao = new Navigator(pilot, odometro); // cria um navegador para indicar Path's

		Waypoint aWaypoint = new Waypoint(20, 0);
		navegacao.addWaypoint(aWaypoint);
		aWaypoint = new Waypoint(20, 20);
		navegacao.addWaypoint(aWaypoint);
		aWaypoint = new Waypoint(0, 20);
		navegacao.addWaypoint(aWaypoint);
		aWaypoint = new Waypoint(0, 0);
		navegacao.addWaypoint(aWaypoint);
		
		//o robo inicia o caminho
		navegacao.followPath(); // metodo is non-blocking ele nao espera terminar o path

		show(navegacao.getPoseProvider().getPose());

		log.info("Voltage: {}", Battery.getInstance().getVoltage());

	}

	private static void show(Pose p) {
		log.info("Pose X " + p.getX());
		log.info("Pose Y " + p.getY());
		log.info("Pose V " + p.getHeading());
	}
}