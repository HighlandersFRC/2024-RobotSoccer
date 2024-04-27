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
        DriveTrain.initialize(hardwareMap);
        Intake.initialize(hardwareMap);
        while (opModeIsActive()){

            double y = gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(rx), 1);
            double leftPower = (y + rx) / denominator;
            double rightPower = (y - rx) / denominator;
            DriveTrain.Drive(leftPower, rightPower);

            if (gamepad1.a){
                Intake.moveMotor(1);
            }
            if (gamepad1.b){
                Intake.moveMotor(-1);
            }
        telemetry.addData("LeftMotor", DriveTrain.getLeftEncoder());
            telemetry.addData("RightMotor", DriveTrain.getRightEncoder());
            telemetry.update();
        }
    }
}
