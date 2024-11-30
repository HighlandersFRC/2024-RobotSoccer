package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Tools.Vector;

public class DriveTrain extends Subsystems {
    String name = "DriveTrain";

    public static DcMotor left_motor;
    public static DcMotor right_motor;

    private static double x = 0;
    private static double y = 0;
    private static double theta = 0;

    private static double lastLeftEncoder = 0;
    private static double lastRightEncoder = 0;

    private static double offsetX = 0;
    private static double offsetY = 0;

    private static final double WHEEL_DIAMETER = 4.0;
    private static final double TICKS_PER_REV = 537.6;
    private static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    private static final double TICKS_PER_INCH = TICKS_PER_REV / WHEEL_CIRCUMFERENCE;
    private static final double TRACK_WIDTH = 12.0;

    public static void Drive(double LeftMotorPower, double RightMotorPower) {
        left_motor.setPower(LeftMotorPower);
        right_motor.setPower(RightMotorPower);
    }

    public static void initialize(HardwareMap hardwareMap) {
        left_motor = hardwareMap.dcMotor.get("left_motor");
        right_motor = hardwareMap.dcMotor.get("right_motor");
        left_motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public static void resetEncoders() {
        left_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public static void updateOdometry() {
        double leftEncoder = left_motor.getCurrentPosition();
        double rightEncoder = right_motor.getCurrentPosition();

        double leftDelta = (leftEncoder - lastLeftEncoder) / TICKS_PER_INCH;
        double rightDelta = (rightEncoder - lastRightEncoder) / TICKS_PER_INCH;

        lastLeftEncoder = leftEncoder;
        lastRightEncoder = rightEncoder;

        double distance = (leftDelta + rightDelta) / 2.0;
        theta = Math.toRadians(Peripherals.getYaw());
        x += distance * Math.cos(theta);
        y += distance * Math.sin(theta);
    }

    public static void resetOdometry() {
        x = offsetX;
        y = offsetY;
        Peripherals.resetYaw();
        resetEncoders();
    }

    public static void setOffsetPosition(double xOffset, double yOffset) {
        offsetX = xOffset;
        offsetY = yOffset;
    }

    public static double getX() {
        return x;
    }

    public static double getY() {
        return y;
    }

    public static double getTheta() {
        return theta;
    }
    public static void autoDrive(Vector vector, double angle) {
        double vx = vector.getI();
        double vy = -vector.getJ();

        double rotationFactor = -angle;

        double botHeading = Math.toRadians(Peripherals.getYaw());

        double rotX = vx * Math.cos(-botHeading) + vy * Math.sin(-botHeading);
        double rotY = -vx * Math.sin(-botHeading) + vy * Math.cos(-botHeading);

        double forwardPower = rotY;
        double turnPower = rotationFactor;

        double leftMotorPower = forwardPower + turnPower;
        double rightMotorPower = forwardPower - turnPower;

        double maxMagnitude = Math.max(Math.abs(leftMotorPower), Math.abs(rightMotorPower));
        if (maxMagnitude > 1.0) {
            leftMotorPower /= maxMagnitude;
            rightMotorPower /= maxMagnitude;
        }

        DriveTrain.Drive(leftMotorPower, rightMotorPower);
    }

    public static void stop() {
        DriveTrain.Drive(0,0);
        left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
