//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
//import org.firstinspires.ftc.teamcode.Subsystems.Intake;
//
//@TeleOp
//public class ArcadeDrive extends LinearOpMode {
//    @Override
//    public void runOpMode() throws InterruptedException {
//        waitForStart();
//        DriveTrain.initialize(hardwareMap);
//        Intake.initialize(hardwareMap);
//        while (opModeIsActive()){
//
//            double y = gamepad1.left_stick_y;
//            double rx = gamepad1.right_stick_x;
//            double leftTrigger = gamepad1.left_trigger;
//            double rightTrigger = gamepad1.right_trigger;
//
//            double intakePower = leftTrigger - rightTrigger ;
//
//            double denominator = Math.max(Math.abs(y) + Math.abs(rx), 1);
//            double leftPower = (y + rx) / denominator;
//            double rightPower = (y - rx) / denominator;
//            DriveTrain.Drive(leftPower, rightPower);
//
//            Intake.moveIntakeMotor(intakePower);
//
//            if (gamepad1.a){
//                Intake.moveShooterMotor(1);
//            }
//            else if (gamepad1.b){
//                Intake.moveShooterMotor(0.5);
//            }
//            else {
//                Intake.moveShooterMotor(0);
//            }
//
//            if (y == 0){
//                DriveTrain.floatMotors();
//            }
//
//        telemetry.addData("LeftMotor", DriveTrain.getLeftEncoder());
//            telemetry.addData("RightMotor", DriveTrain.getRightEncoder());
//            telemetry.update();
//        }
//    }
//}
