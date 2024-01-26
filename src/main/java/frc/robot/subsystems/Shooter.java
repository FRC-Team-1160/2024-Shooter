package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;

import edu.wpi.first.hal.CANAPITypes.CANDeviceType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;



public class Shooter extends SubsystemBase{
    private static Shooter m_instance;

    public CANSparkMax forwardMotor;
    public CANSparkMax reverseMotor;

    public double f_speed;
    public double r_speed;
    public double temp_f_speed;
    public double temp_r_speed;
    public boolean inverted;
    public Timer m_timer;

    public static Shooter getInstance(){
        System.out.println("shooterinstancs");
        if (m_instance == null){
          m_instance = new Shooter();
        }
        return m_instance;
      }


    private Shooter(){
        forwardMotor = new CANSparkMax(10, CANSparkLowLevel.MotorType.kBrushless); //BRUSHED (set to brushless for sim)
        //reverseMotor = new CANSparkMax(9, CANSparkLowLevel.MotorType.kBrushless); //BRUSHLESS FOR INTAKE, BRUSHED FOR SHOOTER
        reverseMotor = new CANSparkMax(8, CANSparkLowLevel.MotorType.kBrushless); //BRUSHED
        inverted = false;
        forwardMotor.setInverted(inverted);
        reverseMotor.setInverted(inverted);
        f_speed = 0.0;
        r_speed = 0.0;
        temp_f_speed = 0.0;
        temp_r_speed = 0.0;
        m_timer = new Timer();
        m_timer.start();
        //m_compressor.disable();
        System.out.println("shooter init");
        //
    }

    public void changeSpeed(Joystick stick){
      double left = stick.getRawAxis(1);
      double right = stick.getRawAxis(5);
      if (m_timer.advanceIfElapsed(0.2)){
        if (Math.abs(left) > 0.2){
          temp_r_speed -= Math.signum(left) * 0.05;
          temp_r_speed = Math.max(Math.min(temp_r_speed, 1), 0);
        }
        if (Math.abs(right) > 0.2){
          temp_f_speed -= Math.signum(right) * 0.05;
          temp_f_speed = Math.max(Math.min(temp_f_speed, 1), 0);

        }

        SmartDashboard.putNumber("temp_f_speed", temp_f_speed);
        SmartDashboard.putNumber("temp_r_speed", temp_r_speed);
      }
      
    }
}
