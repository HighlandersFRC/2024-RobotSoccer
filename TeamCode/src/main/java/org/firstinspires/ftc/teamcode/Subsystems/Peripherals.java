package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Peripherals extends Subsystems{
    public static IMU imu;
    public static String name = "Peripherals";
    public static void initialize(HardwareMap hardwareMap){
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));
        imu.initialize(parameters);
    }
    public static double getYaw(){
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }
    public static void resetYaw(){imu.resetYaw();}
    public static double getRoll(){
        return imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.DEGREES);
    }
    public static double getPitch(){
        return imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES);
    }
}
//s223
