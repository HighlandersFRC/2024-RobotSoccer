package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake extends Subsystems{
    public static String name = "Intake";

    public static DcMotor Intake;
    public static DcMotor Shooter;

    public static void initialize(HardwareMap hardwareMap){
        Intake = hardwareMap.dcMotor.get("Intake");
        Shooter = hardwareMap.dcMotor.get("Shooter");
    }
    public static void moveIntakeMotor(double Power){
        Intake.setPower(-Power);
    }
    public static void moveShooterMotor(double Power){
        Shooter.setPower(-Power);
    }
}
