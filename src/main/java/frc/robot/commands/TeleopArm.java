// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;

public class TeleopArm extends Command {
  private Arm a_Arm;
  private DoubleSupplier speedSup;

  /** Creates a new TeleopArm. */
  public TeleopArm(Arm a_Arm, DoubleSupplier speedSup) {
    this.a_Arm = a_Arm;
    addRequirements(a_Arm);

    this.speedSup = speedSup;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = speedSup.getAsDouble();
    
    if(a_Arm.getArmPosition() <= 36000 && a_Arm.getArmPosition() >= 0){
      a_Arm.setArmMotor(speed);
    }
    else{
      if(a_Arm.getArmPosition() > 36000){
        if(speed < 0){
          a_Arm.setArmMotor(speed);
        }
        else{
          a_Arm.brakeArmMotor();
        }
      }
      if(a_Arm.getArmPosition() < 0){
        if(speed > 0){
          a_Arm.setArmMotor(speed);
        }
        else{
          a_Arm.brakeArmMotor();
        }
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    a_Arm.brakeArmMotor();
    System.out.println(a_Arm.getArmPosition());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
