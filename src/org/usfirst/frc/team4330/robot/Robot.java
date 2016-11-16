
package org.usfirst.frc.team4330.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
// import edu.wpi.first.wpilibj.tables.ITableProvider;


/**
 * This is a demo program showing the use of the RobotDrive class.
 * The SampleRobot class is the base of a robot application that will automatically call your
 * Autonomous and OperatorControl methods at the right time as controlled by the switches on
 * the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
    RobotDrive myRobot;
    Joystick stick;
    SpeedController motor;
    Relay minimotor;
    Servo serve;
    DigitalInput button;
    AnalogPotentiometer me;
    Pneumatics pneu;
    Compressor comp;
    Encoder mew;
//    Solenoid armSol = new Solenoid(7, 4);
//    Compressor aCompressor = new Compressor(1); //create a compressor with the default slots and relay and pressure switch channels 1 and 1.
    
    public Robot() {
        myRobot = new RobotDrive(0, 1);
        comp = new Compressor();
        pneu = new Pneumatics(new Compressor(RobotMap.COMPRESSOR), new DoubleSolenoid(RobotMap.ANOTHAONE_SOLF,RobotMap.ANOTHAONE_SOLB), new DoubleSolenoid(RobotMap.BIGONE_SOLF, RobotMap.BIGONE_SOLB));
        myRobot.setExpiration(0.1);
        stick = new Joystick(0);
        motor = new Victor(RobotMap.MOTOR);
        minimotor = new Relay(0);
        serve = new Servo(9);
        button = new DigitalInput(9);
        me = new AnalogPotentiometer(1);
        mew = new Encoder(0, 1); //11 and 10
//        comp.start(); //start the compressor.
    }

    public void autonomous() {
		if (isEnabled() && isAutonomous()) {
			 double old = mew.getRaw();
//		}
			 
    	while (isEnabled() && isAutonomous()) {
//        	motor.disable();
//        	motor.set(.3);
//        	Timer.delay(7);
//        	motor.set(-.3);
//        	motor.disable();
    		
    		serve.setAngle(90);
    		Timer.delay(.5);
    		serve.setAngle(360);
    		motor.set(.5);
    		Timer.delay(3);
    		serve.setAngle(old);
    		motor.disable();
    		    		
//    		if (mew.getRaw() <= 360 + old) {
//    			System.out.println(mew.getRaw() + " : " + mew.get());
//    			serve.setAngle(mew.getRaw() - old);
//    		} else if (mew.get() > 360 + old && mew.get() <= 720 + old){
//    			System.out.println(mew.getRaw() + " : " + mew.get());
//    			serve.setAngle(360 - (mew.getRaw() - old));
//    		} else {
//    			Timer.delay(.1);
//    			serve.setAngle(old);
//    		}
    	}
		}
    	Timer.delay(.005);
    }

    public void operatorControl() {
//        myRobot.setSafetyEnabled(false);
        while (isOperatorControl() && isEnabled()) {
			if(stick.getRawButton(7)) pneu.BigoneIn();
			else if(stick.getRawButton(8)) pneu.BigoneOut();
			  else pneu.BigoneOff();
			  if(stick.getRawButton(9)) pneu.AnothaoneIn();
			  else if(stick.getRawButton(10)) pneu.AnothaoneOut();
			  else pneu.AnothaoneOff();
			  if( stick.getRawButton(12)) pneu.comp.start();
			  else if(stick.getRawButton(11)) pneu.comp.stop();
			  if(stick.getY() != 0) motor.set(stick.getY());
        	}
        	
        }

    /**
     * Runs during test mode
     */
    public void test() {
    	while (isEnabled() && isTest()) {
//    		serve.setAngle(120);
//    		serve.setAngle(90);
//    		while (button.equals(false)) {
//    			minimotor.setDirection(Direction.kForward);
//    		}
//    		
//    		System.out.println(mew.get());
    		
//    		for (int i = 0; i < 10; i++) {
//    			serve.setAngle(0);
//    			Timer.delay(1);
//    			serve.setAngle(180);
//    			Timer.delay(1);
//    			serve.setAngle(360);
//    			Timer.delay(1);
//    			serve.setAngle(180);
////    			Timer.delay(1);
//    		}
    		
    		minimotor.setDirection(Direction.kReverse);
    		
//    		armSol.set(true); //turns the Solenoid on
    		
    	}
    }
}
