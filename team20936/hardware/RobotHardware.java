package org.firstinspires.ftc.teamcode.team20936.hardware;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RobotHardware {
    public MotorEx m_liftLeft, m_liftRight;

    public Motor.Encoder m_liftEncoder;

    public HardwareMap m_hardwareMap;

    public Telemetry m_telemetry;
    private static volatile RobotHardware m_instance = null;

    private RobotHardware(){
    }

    public static RobotHardware getInstance(){
        if ( m_instance != null )
            return m_instance;
        synchronized (RobotHardware.class){
            if ( m_instance == null )
                m_instance = new RobotHardware();
        }
        return m_instance;
    }

    public void initialize(HardwareMap hardwareMap, Telemetry telemetry){
        m_hardwareMap = hardwareMap;
        m_liftLeft = new MotorEx(m_hardwareMap, "liftStanga");
        m_liftRight = new MotorEx(m_hardwareMap, "liftDreapta");

        m_liftEncoder = m_liftRight.encoder;
    }

    public void reset(){
        m_liftEncoder.reset();
    }

}
