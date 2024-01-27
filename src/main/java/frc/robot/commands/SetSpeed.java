// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Shooter;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class SetSpeed extends Command {
  /** Creates a new TestCommand. */
  private Shooter m_shooter;
  private double speed;
  private boolean temp;

  public SetSpeed(Shooter m_shooter, double speed, boolean temp) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_shooter = m_shooter;
    this.speed = speed;
    this.temp = temp;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if ((m_shooter.f_speed == speed && m_shooter.r_speed == speed) || temp){
      m_shooter.temp_f_speed = speed;
      m_shooter.temp_r_speed = speed;
    }
    if (!temp){
      m_shooter.f_speed = speed;
      m_shooter.r_speed = speed;
    }
    SmartDashboard.putNumber("temp_b_speed", m_shooter.temp_f_speed*100);
    SmartDashboard.putNumber("temp_t_speed", m_shooter.temp_r_speed*100);
    SmartDashboard.putNumber("b_speed", m_shooter.f_speed*100);
    SmartDashboard.putNumber("t_speed", m_shooter.r_speed*100);

    m_shooter.forwardMotor.set(m_shooter.f_speed);
    m_shooter.reverseMotor.set(-m_shooter.r_speed); //INVERT
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
