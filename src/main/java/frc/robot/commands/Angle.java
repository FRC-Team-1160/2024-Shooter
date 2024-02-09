/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.math.filter.SlewRateLimiter;

public class Angle extends Command {
  /**
   * Creates a new Angle.
   */
  private Shooter m_shooter;
  private double m_amount;
  private SlewRateLimiter filter;

  public Angle(Shooter cannon, double amount) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = cannon;
    m_amount = amount;
    filter = new SlewRateLimiter(0.3);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooter.moveAngle(filter.calculate(m_amount));
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    m_shooter.moveAngle(filter.calculate(m_amount));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //m_timer.stop();
    m_shooter.moveAngle(0);
    //m_timer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;//m_timer.get() >= 0.05;
  }
}
