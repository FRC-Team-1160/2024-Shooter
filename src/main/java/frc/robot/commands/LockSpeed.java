// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Shooter;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class LockSpeed extends Command {
  /** Creates a new TestCommand. */
  private Shooter m_shooter;
  private double speed;

  public LockSpeed(Shooter m_shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_shooter = m_shooter;
    this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooter.f_speed = m_shooter.temp_f_speed;
    m_shooter.r_speed = m_shooter.temp_r_speed;

    m_shooter.forwardMotor.set(m_shooter.f_speed);
    m_shooter.reverseMotor.set(-m_shooter.r_speed); 
    SmartDashboard.putNumber("b_speed", m_shooter.f_speed*100);
    SmartDashboard.putNumber("t_speed", m_shooter.r_speed*100);
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
    return false;
  }
}
