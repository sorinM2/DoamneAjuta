package org.firstinspires.ftc.teamcode.team20936.opmode.teleop;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team20936.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.team20936.subsystems.LiftSubsystem;

@TeleOp
public class teleop extends CommandOpMode {

    private RobotHardware m_RobotHardware;
    private LiftSubsystem m_LiftSubsystem;

    @Override public void initialize(){
    m_RobotHardware = RobotHardware.getInstance();
    m_RobotHardware.initialize(hardwareMap, telemetry);
    m_RobotHardware.reset();

    m_LiftSubsystem = LiftSubsystem.getInstance();
    }

    @Override public void run(){
        CommandScheduler.getInstance().run();
        LiftSubsystem.getInstance().loop();
    }
}
