package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain extends Subsystems{
    String name = "DriveTrain";

    public static DcMotor LeftMotor;
    public static DcMotor RightMotor;
    public static void Drive(double LeftMotorPower, double RightMotorPower){

        //new comp bot
        LeftMotor.setPower(LeftMotorPower);
        RightMotor.setPower(-RightMotorPower);

    }
    public static void initialize(HardwareMap hardwareMap){
        LeftMotor = hardwareMap.dcMotor.get("LeftMotor");
        RightMotor = hardwareMap.dcMotor.get("RightMotor");
    }
    public static void resetEncoders(){
        LeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
    public static void brakeMotors(){
        LeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public static DcMotor getRight(){
        return RightMotor;
    }
    public static DcMotor getLeft(){
        return LeftMotor;
    }
    public static double getRightEncoder(){
        return -RightMotor.getCurrentPosition();
    }
    public static double getLeftEncoder(){
        return LeftMotor.getCurrentPosition();
    }
}