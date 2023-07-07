package org.firstinspires.ftc.teamcode.team20936.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.team20936.hardware.RobotHardware;

import com.qualcomm.robotcore.util.Range;

import java.util.Timer;

@Config
public class LiftSubsystem extends SubsystemBase {

    private RobotHardware m_RobotHardware;

    private final double LiftTolerance_meters = 0.01;
    private double TargetPosition_meters = 0.0;
    public static double P = 0.5;
    public static double I = 0.1;
    public static double D = 0.1;
    private final double iLimit = 0.1;

    private double lastError_meters = 0.0;
    private double lastTimeStamp_seconds = 0.0;
    private double errorSum_meters = 0.0;


    private final double TicksPerRevolution = 537.7;
    private final double WheelSize_meters = 0.05865;
    private final double GearRatio = 1/19.2;
    private final double KTicksToMeters = 1.0/TicksPerRevolution * GearRatio * WheelSize_meters * Math.PI * 2;
    private static volatile LiftSubsystem m_instance = null;
    @Override public void periodic(){}

    private LiftSubsystem(){
        m_RobotHardware = RobotHardware.getInstance();
    }

    public static LiftSubsystem getInstance(){
        if ( m_instance != null )
            return m_instance;
        synchronized (LiftSubsystem.class){
            if ( m_instance == null )
                m_instance = new LiftSubsystem();
        }
        return m_instance;
    }

    public void update(double newTargetMeters )
    {
        TargetPosition_meters = newTargetMeters;
    }

    public void loop(){

        double error_meters = m_RobotHardware.m_liftEncoder.getPosition() * KTicksToMeters - TargetPosition_meters;
        double dt = System.currentTimeMillis() * 1000 - lastTimeStamp_seconds;

        if ( Math.abs(error_meters) < iLimit )
           errorSum_meters += dt * error_meters;


        double error_rate = (error_meters - lastError_meters)/dt;
        double NeededMotorPower = Range.clip( P * error_meters + I * errorSum_meters + D * error_rate, -1, 1);

        lastTimeStamp_seconds = System.currentTimeMillis() * 1000;

        m_RobotHardware.m_liftLeft.motor.setPower(-NeededMotorPower);
        m_RobotHardware.m_liftRight.motor.setPower(NeededMotorPower);

    }

}
