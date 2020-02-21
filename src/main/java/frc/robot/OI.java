// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.Conveyor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// ***** 5053 *****
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import java.util.Map;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
public JoystickButton aim;
public Joystick driver;
public JoystickButton intakeButton;
public JoystickButton shootAllButton;
public JoystickButton stopShootButton;
public JoystickButton hangerButton;
public JoystickButton prepareToShootButton;
public JoystickButton stopShootButton2;
public Joystick operator;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // ***** 5053 *****
    // CONSTANTS FOR XBOX360 JOYSTICK
    public static int XBOX360_LEFT_Y = 1;
    public static int XBOX360_LEFT_X = 0;
    public static int XBOX360_RIGHT_Y = 5;
    public static int XBOX360_RIGHT_X = 4;

    // ***** 5053 *****
    // SHUFFLEBOARD VARIABLES
    // ***** DRIVE TRAIN *****
    private ShuffleboardTab controlMotors;
    private NetworkTableEntry rightInput;
    private NetworkTableEntry leftInput;
    private NetworkTableEntry dualInput;
    private NetworkTableEntry rightEncoder;
    private NetworkTableEntry rightVelocity;
    private NetworkTableEntry leftEncoder;
    private NetworkTableEntry leftVelocity;
    private NetworkTableEntry dualEncoderDifference;
    private NetworkTableEntry dualVelocityDifference;
    private NetworkTableEntry gyroAngle;

    // ***** POWER-CELL *****
    private ShuffleboardTab powerCell;
    private NetworkTableEntry intakeInput;
    private NetworkTableEntry conveyorInput;
    private NetworkTableEntry indexerInput;
    private NetworkTableEntry turretInput;
    private NetworkTableEntry hoodInput;
    private NetworkTableEntry flywheelInput;
    private NetworkTableEntry bottomLimitSensor;
    private NetworkTableEntry readyToLoadSensor;
    private NetworkTableEntry topLimitSensor;
    private NetworkTableEntry flywheelVelocityInput;

    // ***** HANGER *****
    private ShuffleboardTab hangerTab;
    private NetworkTableEntry hangerInput;
    
    // ***** LIMELIGHT CAMERA *****
    private ShuffleboardTab vision;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;
    private NetworkTableEntry tv;
    private NetworkTableEntry ts;

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

operator = new Joystick(1);

stopShootButton2 = new JoystickButton(operator, 5);
stopShootButton2.whenReleased(new shootStop());
prepareToShootButton = new JoystickButton(operator, 5);
prepareToShootButton.whileHeld(new prepareToShoot());
hangerButton = new JoystickButton(operator, 1);
hangerButton.whileHeld(new HangerMove());
stopShootButton = new JoystickButton(operator, 6);
stopShootButton.whenReleased(new shootStop());
shootAllButton = new JoystickButton(operator, 6);
shootAllButton.whileHeld(new ConveyorMove());
intakeButton = new JoystickButton(operator, 2);
intakeButton.whileHeld(new IntakeMove(false));
driver = new Joystick(0);

aim = new JoystickButton(driver, 5);
aim.whenPressed(new AimUsingChassis());


        // SmartDashboard Buttons
        SmartDashboard.putData("AimUsingChassis", new AimUsingChassis());
        SmartDashboard.putData("ConveyorMove", new ConveyorMove());
        SmartDashboard.putData("conveyorStop", new conveyorStop());
        SmartDashboard.putData("conveyorMoveIntakeToBottom", new conveyorMoveIntakeToBottom());
        SmartDashboard.putData("driveArcadePercentOutput", new driveArcadePercentOutput());
        SmartDashboard.putData("driveArcadeVelocity", new driveArcadeVelocity());
        SmartDashboard.putData("driveTankPercentOutput", new driveTankPercentOutput());
        SmartDashboard.putData("driveCurvaturePercentOutput", new driveCurvaturePercentOutput());
        SmartDashboard.putData("driveDirectControl", new driveDirectControl());
        SmartDashboard.putData("driveDualDirectControl", new driveDualDirectControl());
        SmartDashboard.putData("FlyWheelMove", new FlyWheelMove());
        SmartDashboard.putData("flywheelIdle", new flywheelIdle());
        SmartDashboard.putData("flywheelTimed: default", new flywheelTimed(3));
        SmartDashboard.putData("flyWheelStop", new flyWheelStop());
        SmartDashboard.putData("HangerMove", new HangerMove());
        SmartDashboard.putData("hangerReset", new hangerReset());
        SmartDashboard.putData("HoodMove", new HoodMove());
        SmartDashboard.putData("IndexerMove", new IndexerMove());
        SmartDashboard.putData("indexerStop", new indexerStop());
        SmartDashboard.putData("indexerIdle", new indexerIdle());
        SmartDashboard.putData("TurretMove", new TurretMove());
        SmartDashboard.putData("shootAll", new shootAll());
        SmartDashboard.putData("shootStop", new shootStop());
        SmartDashboard.putData("prepareToShoot", new prepareToShoot());
        SmartDashboard.putData("conveyorIndexer", new conveyorIndexer());
        SmartDashboard.putData("limelightPowerSwitch: On", new limelightPowerSwitch(3));
        SmartDashboard.putData("limelightPowerSwitch: Off", new limelightPowerSwitch(1));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS        
    SmartDashboard.putData("FlyWheelWithVelocity", new FlyWheelWithVelocity(500));                  // Argument: Velocity
    SmartDashboard.putData("IntakeMove", new IntakeMove(true));                                     // Argument: Override Conditions
    SmartDashboard.putData("driveUsingGyro", new driveUsingGyro(36));                               // Argument: Inches
    SmartDashboard.putData("driveDistanceUsingEncoder", new driveDistanceUsingEncoder(36, 150));    // Arguments: Inches, Speed
    
    // ***** 5053 *****
    // Shuffleboard Tabs
    // ***** DRIVE TRAIN *****
    // Allows the user to operate the DriveTrain.  
    // Motor inputs are not persistent, since
    // we always want these to start at 0.
    controlMotors = Shuffleboard.getTab("DriveTrain");
        
    leftInput = controlMotors.add("Left Speed", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of("min", -1, "max", 1))
        .withPosition(0, 0)
        .getEntry();
    leftEncoder = controlMotors.add("Left Encoder", 0)
        .withPosition(0, 1)
        .getEntry();
    leftVelocity = controlMotors.add("Left Velocity", 0)
        .withPosition(0, 2)
        .getEntry();
    controlMotors.add(new driveDirectControl())
        .withPosition(0, 3);

    rightInput = controlMotors.add("Right Speed", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of ("min", -1, "max", 1))
        .withPosition(1, 0)
        .getEntry();
    rightEncoder = controlMotors.add("Right Encoder", 0)
        .withPosition(1,1)
        .getEntry();
    rightVelocity = controlMotors.add("Right Velocity", 0)
        .withPosition(1, 2)
        .getEntry();
        
    dualInput = controlMotors.add("Dual Speed", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of("min", -1, "max", 1))
        .withPosition(2, 0)
        .getEntry();
    dualEncoderDifference = controlMotors.add("Dual Encoder", 0)
        .withWidget(BuiltInWidgets.kGraph)
        .withPosition(2, 1)
        .getEntry();
    dualVelocityDifference = controlMotors.add("Dual Velocity", 0)
        .withWidget(BuiltInWidgets.kGraph)
        .withPosition(2, 2)
        .getEntry();
    controlMotors.add(new driveDualDirectControl())
        .withPosition(2, 3);        

    gyroAngle = controlMotors.add("Gyro Angle", 0)
        .withWidget(BuiltInWidgets.kGyro)
        .withPosition(3, 0)
        .getEntry();

    controlMotors.add(new driveArcadePercentOutput())
        .withPosition(4, 0);
    controlMotors.add(new driveTankPercentOutput())
        .withPosition(4, 1);
    controlMotors.add(new driveCurvaturePercentOutput())
        .withPosition(4,2);

    // ***** POWER-CELL *****
    // Allows the user to operate the individual components
    // associated with collecting and shooting a power-cell.
    // This includes the intake, conveyor, indexer, turret,
    // hood, and the flywheel  
    // Motor inputs are persistent, since we find and save good default
    // values for these functions
    powerCell = Shuffleboard.getTab("Power-Cell");
        
    intakeInput = powerCell.addPersistent("Intake Speed", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of ("min", -1, "max", 1))
        .withPosition(0, 0)
        .getEntry();
    powerCell.add("Intake Overide", new IntakeMove(true))
        .withPosition(0, 1);
    powerCell.add("Intake No Overide", new IntakeMove(false))
        .withPosition(0, 2);
    readyToLoadSensor = powerCell.add("ReadyToLoadSensor", 0)
        .withPosition(0, 3)
        .getEntry();

    conveyorInput = powerCell.addPersistent("Conveyor Speed", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of("min", -1, "max", 1))
        .withPosition(1, 0)
        .getEntry();
    powerCell.add(new ConveyorMove())
        .withPosition(1, 1);
    powerCell.add(new conveyorMoveIntakeToBottom())
        .withPosition(1, 2);
    bottomLimitSensor = powerCell.add("BottomLimitSensor", true)
        .withWidget(BuiltInWidgets.kBooleanBox)
        .withSize(1,1)
        .withPosition(1, 3)
        .getEntry();

    indexerInput = powerCell.addPersistent("Indexer Speed", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of("min", -1, "max", 1))
        .withPosition(2, 0)
        .getEntry();
    powerCell.add(new IndexerMove())
        .withPosition(2, 1);
    topLimitSensor = powerCell.add("TopLimitSensor", true)
        .withWidget(BuiltInWidgets.kBooleanBox)
        .withSize(1,1)
        .withPosition(2,3)
        .getEntry();

    turretInput = powerCell.addPersistent("Turret Speed", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of("min", -1, "max", 1))
        .withPosition(3, 0)
        .getEntry();
    powerCell.add(new TurretMove())
        .withPosition(3, 1);

    hoodInput = powerCell.addPersistent("Hood Speed", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of("min", -1, "max", 1))
        .withPosition(4, 0)
        .getEntry();
    powerCell.add(new HoodMove())
        .withPosition(4, 1);

    flywheelInput = powerCell.addPersistent("Flywheel Speed", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of("min", -1, "max", 1))
        .withPosition(5, 0)
        .getEntry();
    powerCell.add(new FlyWheelMove())
        .withPosition(5, 1);

    flywheelVelocityInput = powerCell.addPersistent("Flywheel Velocity", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withPosition(6, 0)
        .getEntry();
    powerCell.add(new FlyWheelWithVelocity(500))
        .withPosition(6, 1);

    // ***** HANGER *****
    hangerTab = Shuffleboard.getTab("Hang");

    hangerInput = hangerTab.addPersistent("Hanger Speed", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of("min", -1, "max", 1))
        .withPosition(6, 0)
        .getEntry();

    hangerTab.add(new HangerMove())
        .withPosition(6, 1);

    // ***** LIMELIGHT *****
    // Allows the user to view targeting information
    vision = Shuffleboard.getTab("Vision");
        
    tv = vision.add("Target Visible", 0)
        .withWidget(BuiltInWidgets.kBooleanBox)
        .withPosition(0, 0)
        .getEntry();

    tx = vision.add("Horizontal Offset", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withPosition(0, 1)
        .getEntry();

    ty = vision.add("Vertical Offset", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withPosition(0, 2)
        .getEntry();

    ts = vision.add("Rotation or Skew", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withPosition(0, 3)
        .getEntry();

    ta = vision.add("Area % Image", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withPosition(0, 4)
        .getEntry();

    }


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public Joystick getdriver() {
        return driver;
    }

public Joystick getoperator() {
        return operator;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

    // ***** 5053 *****
    public void updateDriveRightEncoder(double _value) {
        rightEncoder.setDouble(_value);
    }

    public void updateDriveLeftEncoder (double _value) {
        leftEncoder.setDouble(_value);
    }

    public void updateDriveRightVelocity (double _value) {
        rightVelocity.setDouble(_value);
    }

    public void updateDriveLeftVelocity (double _value) {
        leftVelocity.setDouble(_value);
    }

    public void updateDriveDualEncoderDifference (double _value) {
        dualEncoderDifference.setDouble(_value);
    }

    public void updateDriveDualVelocityDifference (double _value) {
        dualVelocityDifference.setDouble(_value);
    }

    public double getDriveRightInput() {
        return rightInput.getDouble(0);
    }

    public double getDriveLeftInput() {
        return leftInput.getDouble(0);
    }

    public double getDriveDualInput() {
        return dualInput.getDouble(0);
    }

    public double getIntakeInput() {
        return intakeInput.getDouble(0);
    }

    public double getConveyorInput() {
        return conveyorInput.getDouble(0);
    }

    public double getIndexerInput() {
        return indexerInput.getDouble(0);
    }

    public double getTurretInput() {
        return turretInput.getDouble(0);
    }

    public double getHoodInput() {
        return hoodInput.getDouble(0);
    }

    public double getFlywheelInput() {
        return flywheelInput.getDouble(0);
    }

    public double getFlywheelVelocityInput() {
        return flywheelVelocityInput.getDouble(0);
    }

    public double getHangerInput() {
        return hangerInput.getDouble(0);
    }

    public void updateVisionTX(double _value) {
        tx.setDouble(_value);
    }

    public void updateVisionTY(double _value) {
        ty.setDouble(_value);
    }

    public void updateVisionTA(double _value) {
        ta.setDouble(_value);
    }

    public void updateVisionTS(double _value) {
        ts.setDouble(_value);
    }

    public void updateVisionTV(Boolean _value) {
        tv.setBoolean(_value);
    }

    public void updateReadyToLoadSensor(double _value) {
        readyToLoadSensor.setDouble(_value);
    }

    public void updateTopLimitSensor(boolean _value) {
        topLimitSensor.setBoolean(_value);
    }

    public void updateBottomLimitSensor(boolean _value) {
        bottomLimitSensor.setBoolean(_value);
    }
    
    public void updateGyroAngle(double _value) {
        gyroAngle.setDouble(_value);
    }
}
  
