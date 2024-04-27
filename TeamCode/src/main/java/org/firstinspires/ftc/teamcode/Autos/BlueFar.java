package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commands.CommandGroup;
import org.firstinspires.ftc.teamcode.Commands.Drive;
import org.firstinspires.ftc.teamcode.Commands.Scheduler;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

@Autonomous
public class BlueFar extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DriveTrain.initialize(hardwareMap);
        Intake.initialize(hardwareMap);
        Scheduler scheduler = new Scheduler();
        waitForStart();
        scheduler.add(new CommandGroup(scheduler,
                new Drive(hardwareMap, 1, 1)
                ));
        while (opModeIsActive()){
            scheduler.update();
        }
    }
}
