package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.Command;

public class Intake extends Subsystems{
    public static String name = "Intake";

    public static DcMotor Intake;

    public static void initialize(HardwareMap hardwareMap){
        Intake = hardwareMap.dcMotor.get("Intake");
    }
    public static void moveMotor(double Power){
        Intake.setPower(-Power);
    }
}
