package org.firstinspires.ftc.teamcode.team20936.commands;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.team20936.subsystems.LiftSubsystem;

public class LiftPositionCommand extends InstantCommand {

    public LiftPositionCommand(double target_meters){
        super( ()->LiftSubsystem.getInstance().update(target_meters));
    }
}
