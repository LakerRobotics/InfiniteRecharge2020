// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


// Limelight Config:  


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
import edu.wpi.first.wpilibj.SPI;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DigitalInput;
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
private DigitalInput topLimit;
private Ultrasonic readyToLoadUltrasonic;
private DigitalInput bottomLimit;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // ***** 5053 *****
    private ADXRS450_Gyro frcGyroAccel;

    private NetworkTable table;
    private NetworkTableEntry tx;       // Horizontal Offset from Crosshair to Target -27 degrees to 27 degrees
    private NetworkTableEntry ty;       // Vertical Offset from Crosshair to Target -20.5 degrees to 20.5 degrees
    private NetworkTableEntry ta;       // Target Area 0% of image to 100% of image
    private NetworkTableEntry ts;       // Rotation or Skew of Target
    private NetworkTableEntry tv;       // Whether the limelight has any valid targets 0 or 1
    private NetworkTableEntry ledMode;
    public static final double VISION_DEFAULT = 999999;

    private final double INRANGE_MAX_RTL_DISTANCE = 4.5;     // Fine tune this as we improve the feed from the intake to the conveyor
    
    public RobotSensors() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
powerPanel = new PowerDistributionPanel(0);
addChild("powerPanel",powerPanel);

        
topLimit = new DigitalInput(8);
addChild("topLimit",topLimit);

        
readyToLoadUltrasonic = new Ultrasonic(4, 5);
addChild("ReadyToLoadUltrasonic",readyToLoadUltrasonic);

        
bottomLimit = new DigitalInput(7);
addChild("bottomLimit",bottomLimit);

        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    // ***** 5053 *****
    
    frcGyroAccel = new ADXRS450_Gyro();
    addChild("frcGyroAccel",frcGyroAccel);
    frcGyroAccel.calibrate();    // https://wiki.analog.com/first/adxrs450_gyro_board_frc/java
    
    /*
    myGyro = new ADXRS450_Gyro();
    addChild("myGyro",myGyro);
    myGyro.calibrate(); 
    */

    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
    tv = table.getEntry("tv");
    ts = table.getEntry("ts");
    ledMode = table.getEntry("ledMode");

    // setLedMode(1);                              // Turn the LED on the camera off until the robot is enabled
    
    readyToLoadUltrasonic.setEnabled(true);
    readyToLoadUltrasonic.setAutomaticMode(true);

    powerPanel.clearStickyFaults();         
    
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
        Robot.oi.updateVisionTS(ts.getDouble(0));
        Robot.oi.updateVisionTV(tv.getBoolean(false));

        Robot.oi.updateReadyToLoadSensor(readyToLoadUltrasonic.getRangeInches());
        Robot.oi.updateBottomLimitSensor(bottomLimit.get());
        Robot.oi.updateTopLimitSensor(topLimit.get());

        Robot.oi.updateGyroAngle(frcGyroAccel.getAngle());
        

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public boolean isIntakeInRange() {
        double currentBottomSensorValue = readyToLoadUltrasonic.getRangeInches();
        
        if (currentBottomSensorValue >= INRANGE_MAX_RTL_DISTANCE) return false;
        else return true;
    }

    public boolean isBottomInRange() {
        /*
        double currentBottomSensorValue = bottomUltrasonic.getRangeInches();
       
        if (currentBottomSensorValue >= INRANGE_MAX_BOTTOM_DISTANCE) return false;
        else return true;
        */
        boolean currentBottomLimitValue = bottomLimit.get();
        return !currentBottomLimitValue;
    }

    public boolean isTopInRange() {
        boolean currentTopSensorValue = topLimit.get();
        return currentTopSensorValue;
        
        // if (currentTopSensorValue ) return false;
        // else return true;
    }

    public double getTX() {
        return tx.getDouble(VISION_DEFAULT);
    }

    public double getTY() {
        return ty.getDouble(VISION_DEFAULT);
    }

    public int getLedMode() {
        return (int) ledMode.getNumber(0);
    }

    public void setLedMode(int mode) {
        ledMode.setNumber(mode);
    }

    public double getGyroAngle() {
        return frcGyroAccel.getAngle();
        
    }

    public boolean getTV() {
        if (tv.getBoolean(false)) {
            // Target is acquired
            if (tx.getDouble(VISION_DEFAULT) == 0 &&
                ty.getDouble(VISION_DEFAULT) == 0 &&
                ts.getDouble(VISION_DEFAULT) == 0 &&
                ta.getDouble(VISION_DEFAULT) == 0) {
                SmartDashboard.putString("VISION ERROR", "TV & (TX, TY, TA, TS) DO NOT AGREE");
                return false;
                }
            else {
                SmartDashboard.putString("VISION ERROR", " ");
                return true;
            }
        }
        else {
            // Target is NOT acquired
            if (tx.getDouble(VISION_DEFAULT) == 0 &&
                ty.getDouble(VISION_DEFAULT) == 0 &&
                ts.getDouble(VISION_DEFAULT) == 0 &&
                ta.getDouble(VISION_DEFAULT) == 0) {
                    SmartDashboard.putString("VISION ERROR", " ");
                    return false;
                }
            else {
                SmartDashboard.putString("VISION ERROR", "TV & (TX, TY, TA, TS) DO NOT AGREE");
                return true;
            }
        }
    }
}

// Hayden's code notes.


// --------------------------------------------------------------------------------

// Void does not return any values, only completes an action or definition.
// Double can return a value and can hold double the information that a normal method can. (i.e Two Values)
// Int returns a single value.
// Boolean returns a boolean value (true or false, 1 or 0).


// --------------------------------------------------------------------------------

// Subsystems control the commands, acting as housing for the commands and the definitions..
// Methods feed off of the commands and exicute the actions of the commands; they give the commands character and abilities.
// Commands complete an action and allow the subsystem to do something, using the code inside of them.


// --------------------------------------------------------------------------------

// Private only allows you to use that method/object in the certain subsystem/command.
// Public allows you to use a method/object in all subsystems/commands.

// --------------------------------------------------------------------------------

// A Public Void Method: public void <methodName>(<Optional Perameters>) {
// <Method's code in here>
// }

// A Private Boolean Method: private boolean <methodName>(<Optional Perameters>) {
// <Method's code in here>
// }

// A Private Int Method: private int <methodName> (<Optional Perameters>) {
// <Method's code in here>
// }

//----------------------------------------------------------------------------------

// if's, elses and elseif's

// if's are conditional checking if a certain condition is true.

// if(<variable>) <condition (i.e. ==, =< >)> <number or variable> *optional && which adds another if* {
// <run code>
// }

// elses are the opposite of if's. If the if statement is false, it runs the else form of the statement. (such as if the orange is not an apple, (ELSE) write How is an orange an apple?)

// <if statement> {
// <if statement code>
// }
// else {
// <else statement code>
// }

// elseif's are optional if's if the first if statement is false.
