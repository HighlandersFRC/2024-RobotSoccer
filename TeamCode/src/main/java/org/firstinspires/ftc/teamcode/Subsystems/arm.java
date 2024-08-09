package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Tools.PID;

public class arm extends Subsystems {
    String name = "arm";
    public static int targetPos;
    public static double armPower;
    public static DcMotor arm_Motor ;
    public static double pidSetPoint;
    static PID ArmPID = new PID(0.0015, 0.0, 0.0018);

    public static void initialize(HardwareMap hardwareMap){
        arm_Motor = hardwareMap.dcMotor.get("arm_motor");
    }
    public static double getArmPos (){
        return arm_Motor.getCurrentPosition();
    }
    public static void PidSetPoint (double pidPoint){
        pidSetPoint = pidPoint;
        ArmPID.setSetPoint(pidPoint);
    }
    public static void PidUpdate(){
        ArmPID.updatePID(getArmPos());
    }
    public static double PidGetError (){

        return ArmPID.getError();
    }
    public static void moveArm (int tPos){
        targetPos = tPos;
        PidSetPoint(targetPos);
        PidUpdate();
        arm_Motor.setPower(PidGetError());

    }
    public static void constantPowerArm (double armPow){
        armPower = armPow;
        arm_Motor.setPower(armPower);
    }
    public static void breakModeArm(){
        arm_Motor.setPower(0);
        arm_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

}
