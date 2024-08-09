package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;

@TeleOp
public class test extends LinearOpMode {
    @Override
    public void runOpMode(){
        waitForStart();
        DriveTrain.initialize(hardwareMap);
        while (opModeIsActive()) {
            double R = DriveTrain.getRightEncoder();
            double L = DriveTrain.getLeftEncoder();
            System.out.println("r"+R);
            System.out.println("l"+L);
        }
    }
}
