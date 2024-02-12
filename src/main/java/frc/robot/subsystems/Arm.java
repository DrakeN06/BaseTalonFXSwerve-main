// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  private TalonFX mArmMotor;
  private final VoltageOut m_request = new VoltageOut(0);
  
  /*arm motor control requests */
  
  public Arm(){
    /*Arm Motor Config */
    mArmMotor = new TalonFX(Constants.Arm.armMotorID);
  }

  public void setArmMotor(double speed){
    double percentVoltage = Constants.Arm.maxVoltage;
    mArmMotor.setControl(m_request.withOutput(speed * percentVoltage * Constants.Arm.basePercentOutput));
  }

  /*public void brakeArmMotor(){
    mArmMotor.setControl(m_request.withOutput(0));
  }*/

  public void brakeArmMotor(){
    mArmMotor.setControl(m_request.withOutput(0));
    //mArmMotor.setNeutralMode(NeutralModeValue.Brake);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Arm Angle", getArmPosition());
    System.out.println(getArmPosition());
  }

  public double getArmPosition(){
    var positionSignal = mArmMotor.getPosition();
    double anglePosition = positionSignal.getValueAsDouble();
    return Units.rotationsToDegrees(anglePosition);
  }
}


