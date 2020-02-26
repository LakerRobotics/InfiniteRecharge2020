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
import edu.wpi.first.wpiutil.math.VecBuilder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import com.ctre.phoenix.motorcontrol.ControlMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
    import com.ctre.phoenix.motorcontrol.*;

/**
 *
 */
public class FlyWheel extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_VictorSPX flyWheelVictor;
private WPI_TalonSRX flyWheelTalon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // private final double MINICIM_MAX_FREE_RPM = (5840 * 0.9);         // DOCUMENTED FREE SPEED MINUS 10% AND NO GEAR REDUCTION
    private final double MINICIM_MAX_FREE_RPM = 4100;         // MEASURED LOAD SPEED 
    private final double VELOCITY_TOLERANCE = 200;

    public FlyWheel() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
flyWheelVictor = new WPI_VictorSPX(15);


        
flyWheelTalon = new WPI_TalonSRX(19);


        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        configurePID(flyWheelTalon);
        flyWheelTalon.setSensorPhase(true);
        flyWheelTalon.setSelectedSensorPosition(0, 0, 0);
        flyWheelTalon.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
        flyWheelTalon.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);

        flyWheelVictor.follow(flyWheelTalon);
        flyWheelVictor.setInverted(InvertType.OpposeMaster);
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
        SmartDashboard.putNumber("FlywheelPosition", flyWheelTalon.getSelectedSensorPosition());
        SmartDashboard.putNumber("FlywheelVelocity", flyWheelTalon.getSelectedSensorVelocity());
        //SmartDashboard.putNumber("FlywheelTarget", flyWheelTalon.getClosedLoopTarget());
        SmartDashboard.putNumber("FlywheelError", flyWheelTalon.getClosedLoopError());
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void stop() {
        flyWheelTalon.stopMotor();
    }

    public void move(double speed) {
        flyWheelTalon.set(ControlMode.PercentOutput, speed);
    }

    public void velocityMove(double velocity) {
        double newVelocity = velocity * MINICIM_MAX_FREE_RPM * 4096 / 600;
        flyWheelTalon.set(ControlMode.Velocity, newVelocity);
    }

    public boolean isFlywheelAtSpeed(double velocity) {
        // TODO: When the encoder is installed, re-enter this code
        // if (flyWheelTalon.getSelectedSensorVelocity() == velocity) return true;
        // else return false;
        if (Math.abs(flyWheelTalon.getClosedLoopError()) >= VELOCITY_TOLERANCE) return false;
        else return true;
    }

    private void configurePID(WPI_TalonSRX _talon) {
       final int    kSlotIdx = 0;
       final int    kPIDLoopIdx = 0;
       final int    kTimeoutMs = 30;
       final double kP = 0.6;
       final double kI = 0.001;
       final double kD = 10;
       final double kF = 0;
       final int    kIzone = 0;
       final double kPeakOutput = 1.00;

       /* Factory Default all hardware to prevent unexpected behaviour */
       _talon.configFactoryDefault();

       /* Config sensor used for Primary PID [Velocity] */
       _talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
                                           kPIDLoopIdx, 
                                           kTimeoutMs);
       
       /* Config the peak and nominal outputs */
       _talon.configNominalOutputForward(0, kTimeoutMs);
       _talon.configNominalOutputReverse(0, kTimeoutMs);
       _talon.configPeakOutputForward(1, kTimeoutMs);
       _talon.configPeakOutputReverse(-1, kTimeoutMs);
       
       /* Config the Velocity closed loop gains in slot0 */
       _talon.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
       _talon.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
       _talon.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
       _talon.config_kD(kPIDLoopIdx, kD, kTimeoutMs);
   }

   
   
}

