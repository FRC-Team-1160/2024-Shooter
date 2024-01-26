// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Shooter;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class Invert extends Command {
  /** Creates a new TestCommand. */
  private Shooter m_shooter;

  public Invert(Shooter m_shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_shooter = m_shooter;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooter.inverted = !m_shooter.inverted;
    if (m_shooter.inverted){
      m_shooter.forwardMotor.setInverted(true);
      m_shooter.reverseMotor.setInverted(true);
    } else {
      m_shooter.forwardMotor.setInverted(false);
      m_shooter.reverseMotor.setInverted(false);
    }
    SmartDashboard.putBoolean("Directions inverted", m_shooter.inverted);
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
