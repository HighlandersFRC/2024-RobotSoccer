package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

@TeleOp
public class ArcadeDrive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        double LeftPower = 0;
        double RightPower = 0;
        DriveTrain.initialize(hardwareMap);
        Intake.initialize(hardwareMap);
        while (opModeIsActive()){
            LeftPower = gamepad1.left_stick_y;
            RightPower = gamepad1.right_stick_y;
            DriveTrain.Drive(LeftPower, RightPower);

            if (gamepad1.a){
                Intake.moveMotor(1);
            }
            if (gamepad1.b){
                Intake.moveMotor(-1);
            }
        }
    }
}
