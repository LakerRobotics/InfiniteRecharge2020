// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// Left and Right is relative to the "front" which hasn't been defined
// In this case, Left is the side that receives positive power from
// the Differential Drive Functions.

package frc.robot.subsystems;


import frc.robot.Robot;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDBase.Tolerance;

import java.lang.annotation.Target;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.ControlMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

import edu.wpi.first.wpilibj.shuffleboard.*;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
    private final double MINIMUM_VISION_TURN_SPEED = 0.5;           // Carpet Values       
    private final double MAXIMUM_VISION_TURN_SPEED = 0.7;           // Carpet Values
    // private final double MINIMUM_VISION_TURN_SPEED = 0.3;       // Concrete Values
    // private final double MAXIMUM_VISION_TURN_SPEED = 0.4;       // Concrete Values
    private final double VISION_TOLERANCE = 1;
    private final double INCHES_PER_ROTATION = 5.75 * Math.PI;
    private final double ENCODER_INCHES_TOLERANCE = 6;
    private final double ALLOWABLE_CLOSED_LOOP_ERROR = 50;
    private final double MINICIM_MAX_FREE_RPM = 5840 * 0.9;         // DOCUMENTED FREE SPEED MINUS 10%
    

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonSRX leftSRX;
private WPI_TalonSRX rightSRX;
private DifferentialDrive diffDrive;
private WPI_VictorSPX leftSPX1;
private WPI_VictorSPX leftSPX2;
private WPI_VictorSPX rightSPX1;
private WPI_VictorSPX rightSPX2;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private double desiredDriveHeading;

    public DriveTrain() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
leftSRX = new WPI_TalonSRX(1);


        
rightSRX = new WPI_TalonSRX(2);


        
diffDrive = new DifferentialDrive(leftSRX, rightSRX);
addChild("diffDrive",diffDrive);
diffDrive.setSafetyEnabled(true);
diffDrive.setExpiration(0.1);
diffDrive.setMaxOutput(1.0);

        
leftSPX1 = new WPI_VictorSPX(10);


        
leftSPX2 = new WPI_VictorSPX(16);


        
rightSPX1 = new WPI_VictorSPX(13);


        
rightSPX2 = new WPI_VictorSPX(14);


        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        diffDrive.setDeadband(0.05);
        diffDrive.setRightSideInverted(false);                  // 2-21-2020 Implement per CTRE standards

        configurePID(leftSRX);
        leftSRX.setSelectedSensorPosition(0, 0, 0);
        leftSPX1.follow(leftSRX);
        leftSPX2.follow(leftSRX);
        leftSRX.setInverted(false);                     // 2-21-2020 Implement per CTRE standards
        leftSPX1.setInverted(InvertType.FollowMaster); // 2-21-2020 Implement per CTRE standards
        leftSPX2.setInverted(InvertType.FollowMaster); // 2-21-2020 Implement per CTRE standards
        /**
         * Phase sensor accordingly. 
         * Positive Sensor Reading should match Green (blinking) Leds on Talon
         */
        // leftSRX.setSensorPhase(true);
        


        /* MEW 1/19/2020
         * If you declare these controls to be inverted, the Differential Drive
         * methods will not work properly -- The Differential Drive methods
         * automatically Invert the right side.
         * 
         * REMEMBER TO HANDLE INVERSION MANUALLY IF NOT USING THE INHERITED
         * DIFFERENTIAL DRIVE METHODS
         *
         * diffDrive.setRightSideInverted(false);
         * leftSRX.setInverted(true);
         * leftSPX1.setInverted(true);
         * leftSPX2.setInverted(true);
        */
        

        configurePID(rightSRX);
        rightSRX.setSelectedSensorPosition(0, 0, 0);
        rightSPX1.follow(rightSRX);
        rightSPX2.follow(rightSRX);

        rightSRX.setInverted(true);                     // 2-21-2020 Implement per CTRE standards
        rightSPX1.setInverted(InvertType.FollowMaster); // 2-21-2020 Implement per CTRE standards
        rightSPX2.setInverted(InvertType.FollowMaster); // 2-21-2020 Implement per CTRE standards
        /**
         * Phase sensor accordingly. 
         * Positive Sensor Reading should match Green (blinking) Leds on Talon
         */
        // rightSRX.setSensorPhase(true);
        


    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new driveArcadePercentOutput());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
    
        Robot.oi.updateDriveLeftEncoder(leftSRX.getSelectedSensorPosition(0));
        Robot.oi.updateDriveLeftVelocity(leftSRX.getSelectedSensorVelocity(0));

        Robot.oi.updateDriveRightEncoder(rightSRX.getSelectedSensorPosition(0));
        Robot.oi.updateDriveRightVelocity(rightSRX.getSelectedSensorVelocity(0));

        Robot.oi.updateDriveDualEncoderDifference(Math.abs(leftSRX.getSelectedSensorPosition(0)) - Math.abs(rightSRX.getSelectedSensorPosition()));
        Robot.oi.updateDriveDualVelocityDifference(Math.abs(leftSRX.getSelectedSensorVelocity(0)) - Math.abs(rightSRX.getSelectedSensorVelocity()));

        SmartDashboard.putNumber("ClosedLoopError", leftSRX.getClosedLoopError());

    }
    

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void stop() {
        diffDrive.stopMotor();
    }

    public void arcadePercentOutput(double power, double rotation) {
        diffDrive.arcadeDrive(power, rotation, false);
    }

    public void driveVelocity(double velocity, double inches) {
        /*
        double leftDistance = getDistanceTravelled(leftSRX);
        double rightDistance = getDistanceTravelled(rightSRX);
        double averageDistance = (leftDistance + rightDistance) / 2;
        double error = inches - averageDistance;

        double adjustment = error / inches;
        double newVelocity = velocity * adjustment;

        SmartDashboard.putNumber("leftDistance", leftDistance);
        SmartDashboard.putNumber("rightDistance", rightDistance);
        SmartDashboard.putNumber("newVelocity", newVelocity);
        */
        double newVelocity = velocity;
        SmartDashboard.putNumber("newVelocity", newVelocity);
        leftSRX.set(ControlMode.Velocity, newVelocity);
        rightSRX.set(ControlMode.Velocity, newVelocity);
    }

    public void curvaturePercentOutput(double power, double rotation) {
        diffDrive.curvatureDrive(power, rotation, false);
    }

    public void tankPercentOutput(double leftSpeed, double rightSpeed) {
        diffDrive.tankDrive(leftSpeed, rightSpeed);
    }

    public void directControl(double leftSpeed, double rightSpeed) {
        leftSRX.set(ControlMode.PercentOutput, leftSpeed);
        rightSRX.set(ControlMode.PercentOutput, rightSpeed);
    }

    public void dualDirectControl(double speed) {
        leftSRX.set(ControlMode.PercentOutput, speed);
        rightSRX.set(ControlMode.PercentOutput, speed);
    }

    public void driveStraightUsingEncoderPID(double inches, double speed) {
        diffDrive.setSafetyEnabled(false);
        double setPoint = (inches / INCHES_PER_ROTATION) * 4096;
        int cruiseVelocity = (int) (speed * ((MINICIM_MAX_FREE_RPM * 4096) / 600)); // velocity: ticks per 100msec
        int acceleration = (int) (0.25 * cruiseVelocity);

        leftSRX.configMotionCruiseVelocity(cruiseVelocity);
        leftSRX.configMotionAcceleration(acceleration);
        leftSRX.setSelectedSensorPosition(0, 0, 0);

        rightSRX.configMotionCruiseVelocity(cruiseVelocity);
        rightSRX.configMotionAcceleration(acceleration);
        rightSRX.setSelectedSensorPosition(0, 0, 0);

        leftSRX.set(ControlMode.MotionMagic, setPoint);
        rightSRX.set(ControlMode.MotionMagic, setPoint);

        SmartDashboard.putNumber("Target", leftSRX.getClosedLoopTarget());
    }

    public boolean isDriveUsingEncoderPIDFinished() {
        SmartDashboard.putNumber("ClosedLoopError", leftSRX.getClosedLoopError());

        if ((leftSRX.getSelectedSensorVelocity() == 0) && (rightSRX.getSelectedSensorVelocity() == 0)) return true;
        else return false;

        // if (Math.abs(leftSRX.getClosedLoopError()) <= ALLOWABLE_CLOSED_LOOP_ERROR) return true;
        // else return false;
    }

    private void configurePID(WPI_TalonSRX _talon) {
	    final int kSlotIdx = 0;
	    final int kPIDLoopIdx = 0;
        final int kTimeoutMs = 30;
        final double kP = 1.0;
	    final double kI = 0.0;
	    final double kD = 10;
	    final double kF = 0.27;
	    final int kIzone = 0;
	    final double kPeakOutput = 1.00;

        /* Factory Default all hardware to prevent unexpected behaviour */
        _talon.configFactoryDefault();

        _talon.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
        _talon.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);

        /* Config sensor used for Primary PID [Velocity] */
        // _talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
        //                                     kPIDLoopIdx, 
        //                                     kTimeoutMs);
        
        /* Config the peak and nominal outputs */
        // _talon.configNominalOutputForward(0, kTimeoutMs);
        // _talon.configNominalOutputReverse(0, kTimeoutMs);
        // _talon.configPeakOutputForward(1, kTimeoutMs);
        // _talon.configPeakOutputReverse(-1, kTimeoutMs);
        
        /* Config the Velocity closed loop gains in slot0 */
        // _talon.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
        // _talon.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
        // _talon.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
        // _talon.config_kD(kPIDLoopIdx, kD, kTimeoutMs);

        
    }

    public void aimUsingChassis() {

        /* ***********************************************************************
         * This method yields results between -2.7 and 2.7, which means
         * that for much of the rotation the robot will be moving at full
         * speed.
         * 
         * I think it would be better to create an error that is a ratio
         * of the full movement so that the error is between -1 and 1
         *      final double visionkP = -0.1;
         *      double error = Robot.robotSensors.getTX();              // Value -27 to 27 degrees
         *      double turnSpeed = error * visionkP;
         * **********************************************************************/
        double error = Robot.robotSensors.getTX() / 27;              // tx value -27 to 27 degrees, error value -1 to 1
        double turnSpeed = error * MAXIMUM_VISION_TURN_SPEED;   
        if (Math.abs(turnSpeed) < MINIMUM_VISION_TURN_SPEED) {
            if (turnSpeed < 0) turnSpeed = -MINIMUM_VISION_TURN_SPEED;
            else turnSpeed = MINIMUM_VISION_TURN_SPEED;
        }                       
        
        /* ***********************************************************************
         * BACKWARDS
         * positive tx means that the robot needs to turn counter-clockwise
         * (rightSide positive, leftSide negative).
         * positive tx:
         *       calculations result in a positive error and turnSpeed
         *       the robot needs to turn counter-clockwise
         *       leftSide negative, rightSide positive
         * 
         * negative tx:
         *       calculations result in a negative error and turnSpeed
         *       the robot needs to turn clockwise
         *       leftSide positive, rightSide negative
         * **********************************************************************/
        // diffDrive.tankDrive(leftSpeed, rightSpeed);
        diffDrive.tankDrive(turnSpeed, -turnSpeed);
    }

    public boolean isRobotAimed() {

        double tx = Robot.robotSensors.getTX();

        if (Robot.robotSensors.getTV()) {
            if (Math.abs(tx) < VISION_TOLERANCE) return true;
            else return false;
        }
        else {
            SmartDashboard.putString("ERROR MESSAGE", "Target not acquired");
            return false;
        }
        
    }

    public void setHeading() {
        desiredDriveHeading = Robot.robotSensors.getGyroAngle();
    }

    public void initializeDriveDistanceUsingEncoder() {
        leftSRX.setSelectedSensorPosition(0);
        rightSRX.setSelectedSensorPosition(0);
        desiredDriveHeading = Robot.robotSensors.getGyroAngle();
        SmartDashboard.putNumber("StartingHeading", desiredDriveHeading);
    }

    public boolean isDistanceReached(double inches) {
        double leftDistance = getDistanceTravelled(leftSRX);
        double rightDistance = getDistanceTravelled(rightSRX);
        SmartDashboard.putNumber("LeftDistance", leftDistance);
        SmartDashboard.putNumber("RightDistance", rightDistance);

        if (Math.abs((leftDistance - inches)) < ENCODER_INCHES_TOLERANCE) {
            if (Math.abs((rightDistance - inches)) < ENCODER_INCHES_TOLERANCE) {
                SmartDashboard.putString("DistanceCheck", "Right & Left in tolerance");
            }
            else {
                SmartDashboard.putString("DistanceCheck", "Right NOT in tolerance");
            }
            SmartDashboard.putNumber("EndingHeading", Robot.robotSensors.getGyroAngle());
            return true;
        }
        else
            return false;

    }

    private double getDistanceTravelled(WPI_TalonSRX encoderInformation) {
        /* 
         * Assume the encoder started at 0, 4096 ticks per rotation
         */

        int currentPosition =  encoderInformation.getSelectedSensorPosition();
        double distanceTravelled = (currentPosition / 4096) * INCHES_PER_ROTATION;
        return distanceTravelled;

    }

    public void driveStraightUsingGyro(double inches) {

        /* 
            Initialization gives us the X Position from which you drive straight.
            Error comes from the gyro, not the camera.
            Adjust the drive position to initial X + error.
        */

     
    }
}
