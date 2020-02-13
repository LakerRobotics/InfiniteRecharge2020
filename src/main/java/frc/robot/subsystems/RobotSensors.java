// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.subsystems;


import frc.robot.Robot;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Ultrasonic;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

// ***** 5053 *****
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 *
 */
public class RobotSensors extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private PowerDistributionPanel powerPanel;
private Ultrasonic bottomUltrasonic;
private Ultrasonic readyToLoadUltrasonic;
private Ultrasonic topUltrasonic;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // ***** 5053 *****
    private ADXRS450_Gyro frcGyroAccel;

    private NetworkTable table;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;
    private NetworkTableEntry tv;
    public static int BOTTOM_STATE;
    public static int INTAKE_STATE;
    public static int TOP_STATE;
    private final double INRANGE_MAX_RTL_DISTANCE = 2.7;
    private final double INRANGE_MAX_BOTTOM_DISTANCE = 5.0;
    private final double INRANGE_MAX_TOP_DISTANCE = 2.5;
    public static final int kInRange = 0;
    public static final int kIncreasing = 1;
    public static final int kEmpty = 2;
    public static final int kDecreasing = 3;
    private double previousBottomSensorValue;

    
    public RobotSensors() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
powerPanel = new PowerDistributionPanel(0);
addChild("powerPanel",powerPanel);

        
bottomUltrasonic = new Ultrasonic(2, 3);
addChild("BottomUltrasonic",bottomUltrasonic);

        
readyToLoadUltrasonic = new Ultrasonic(4, 5);
addChild("ReadyToLoadUltrasonic",readyToLoadUltrasonic);

        
topUltrasonic = new Ultrasonic(0, 1);
addChild("TopUltrasonic",topUltrasonic);

        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    // ***** 5053 *****
    frcGyroAccel = new ADXRS450_Gyro();
    addChild("frcGyroAccel",frcGyroAccel);
    frcGyroAccel.calibrate();                       // https://wiki.analog.com/first/adxrs450_gyro_board_frc/java

    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
    tv = table.getEntry("tv");

    readyToLoadUltrasonic.setEnabled(true);
    readyToLoadUltrasonic.setAutomaticMode(true);
    bottomUltrasonic.setEnabled(true);
    bottomUltrasonic.setAutomaticMode(true);
    topUltrasonic.setEnabled(true);
    topUltrasonic.setAutomaticMode(true);

    powerPanel.clearStickyFaults();         // TODO: Track occurrence before clearing
    
    previousBottomSensorValue = bottomUltrasonic.getRangeInches();
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        Robot.oi.updateVisionTA(ta.getDouble(0));
        Robot.oi.updateVisionTX(tx.getDouble(0));
        Robot.oi.updateVisionTY(ty.getDouble(0));
        Robot.oi.updateVisionTV(tv.getBoolean(false));

        Robot.oi.updateReadyToLoadSensor(readyToLoadUltrasonic.getRangeInches());
        Robot.oi.updateBottomSensor(bottomUltrasonic.getRangeInches());
        Robot.oi.updateTopSensor(topUltrasonic.getRangeInches());

        Robot.oi.updateGyroAngle(frcGyroAccel.getAngle());

        setIntakeState();
        setBottomState();
        setTopState();

        SmartDashboard.putNumber("Intake State Calculated", INTAKE_STATE);
        SmartDashboard.putNumber("Bottom State Calculated", BOTTOM_STATE);
        SmartDashboard.putNumber("Top State Calculated", TOP_STATE);
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private void setIntakeState() {
        double currentIntakeSensorValue = readyToLoadUltrasonic.getRangeInches();

        if (currentIntakeSensorValue <= INRANGE_MAX_RTL_DISTANCE)
            INTAKE_STATE = kInRange;
        else
            INTAKE_STATE = kEmpty;
    }

    private void setBottomState() {
        double currentBottomSensorValue = bottomUltrasonic.getRangeInches();
        
        /*
        if (currentBottomSensorValue >= INRANGE_MAX_BOTTOM_DISTANCE) {
            if (previousBottomSensorValue > currentBottomSensorValue)
                BOTTOM_STATE = kDecreasing;
            else
                BOTTOM_STATE = kIncreasing;
        }
        */
        if (currentBottomSensorValue <= INRANGE_MAX_BOTTOM_DISTANCE) BOTTOM_STATE = kEmpty;
        else BOTTOM_STATE = kInRange;
        
        previousBottomSensorValue = currentBottomSensorValue;

    }

    private void setTopState() {
        double currentTopSensorValue = topUltrasonic.getRangeInches();

        if (currentTopSensorValue <= INRANGE_MAX_TOP_DISTANCE)
            TOP_STATE = kInRange;
        else
            TOP_STATE = kEmpty;
    }

    public boolean isIntakeInRange() {
        double currentBottomSensorValue = readyToLoadUltrasonic.getRangeInches();
        
        if (currentBottomSensorValue >= INRANGE_MAX_RTL_DISTANCE) return false;
        else return true;
    }

    public boolean isBottomInRange() {
        double currentBottomSensorValue = bottomUltrasonic.getRangeInches();
        
        if (currentBottomSensorValue >= INRANGE_MAX_BOTTOM_DISTANCE) return false;
        else return true;
    }

    public boolean isTopInRange() {
        double currentBottomSensorValue = topUltrasonic.getRangeInches();
        
        if (currentBottomSensorValue >= INRANGE_MAX_TOP_DISTANCE) return false;
        else return true;
    }
}

