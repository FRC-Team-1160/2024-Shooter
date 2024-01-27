/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PortConstants;
import edu.wpi.first.math.filter.SlewRateLimiter;

public class DriveTrain extends SubsystemBase {
    /**
     * Creates a new DriveTrain.
     */
    private static DriveTrain m_instance;
    private CANSparkMax m_bL, m_mL, m_fL, m_bR, m_mR, m_fR;

    private SlewRateLimiter x_filter;

    public static DriveTrain getInstance(){
        if(m_instance == null)
        m_instance = new DriveTrain();
		return m_instance;
	}
	
    public DriveTrain() {
        m_bL = new CANSparkMax(PortConstants.BACK_LEFT, MotorType.kBrushless);
        m_mR = new CANSparkMax(PortConstants.MID_RIGHT, MotorType.kBrushless);
        m_bR = new CANSparkMax(PortConstants.BACK_RIGHT, MotorType.kBrushless);
        m_fL = new CANSparkMax(PortConstants.FRONT_LEFT, MotorType.kBrushless);
        m_mL = new CANSparkMax(PortConstants.MID_LEFT, MotorType.kBrushless);
        m_fR = new CANSparkMax(PortConstants.FRONT_RIGHT, MotorType.kBrushless);
    
        x_filter = new SlewRateLimiter(2.25);
        // limits the acelleration
        // higher number = more jerky but more responsve, lower = oppsite
    }

    public void tankDrive(double x, double z, double speed, boolean safe){
            x = x_filter.calculate(x);

            double l_speed = speed * (x+z);
            double r_speed = speed * (-x+z);
        if (safe){
            m_bL.set(l_speed);
            m_mL.set(l_speed);
            m_fL.set(l_speed);

            m_bR.set(r_speed);
            m_mR.set(r_speed);
            m_fR.set(r_speed);
        } else {
            m_bL.set(0);
            m_mL.set(0);
            m_fL.set(0);

            m_bR.set(0);
            m_mR.set(0);
            m_fR.set(0);
        }
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}