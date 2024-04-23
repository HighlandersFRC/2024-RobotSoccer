package org.firstinspires.ftc.teamcode.Commands;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Subsystems.Peripherals;
import org.firstinspires.ftc.teamcode.Tools.Constants;
import org.firstinspires.ftc.teamcode.Tools.PID;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;

public class Drive extends Command {
    org.firstinspires.ftc.teamcode.Tools.
            PID PID = new PID(0.03, 0.0, 0.0);
    org.firstinspires.ftc.teamcode.Tools.PID DrivePID = new PID(0.03, 0.0, 0.0);

    public HardwareMap hardwareMap;
    public IMU imu;
    public AHRS navX;
    public double currentPos;
    public double PIDOutput;
    public double speed;
    public double distance;
    public double targetPos;
    public double avgEncoder;
    public double LeftEncoder;
    public double RightEncoder;

    public Drive(HardwareMap hardwareMap, double Speed, double Distance){
        this.speed = Speed;
        this.distance = Distance;
        PID.setSetPoint(0);
        DriveTrain.initialize(hardwareMap);
        Peripherals.initialize(hardwareMap);
        this.hardwareMap = hardwareMap;
    }
    public String getSubsystem() {
        return "DriveTrain";
    }
    public void start() {
        DriveTrain.resetEncoders();
        targetPos = distance * Constants.motorTicksPerMeter;
        PID.setSetPoint(0);
        DrivePID.setSetPoint(targetPos);
        PID.setMaxInput(180);
        PID.setMinInput(-180);
        PID.setContinuous(true);
        PID.setMinOutput(-0.25);
        PID.setMaxOutput(0.25);
        Peripherals.resetYaw();
    }

    public void execute() {
        LeftEncoder = DriveTrain.getLeftEncoder();
        RightEncoder = DriveTrain.getRightEncoder();
        avgEncoder = (LeftEncoder + RightEncoder) / 2;
        DrivePID.updatePID(avgEncoder);
        currentPos = Peripherals.getYaw();
        PID.updatePID(currentPos);
        /*currentPos = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);*/

        double correction = PID.getResult();

        double RightPower = (speed + correction);
        double LeftPower = (speed - correction);


        DriveTrain.Drive(LeftPower, RightPower);
    }

    public void end() {
        DriveTrain.Drive(0, 0);
        DriveTrain.brakeMotors();
    }

    public boolean isFinished() {
        if (Math.abs(avgEncoder) - 10 >= Math.abs(targetPos)) {
          return true;
        }
        return false;
    }
}