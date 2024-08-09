// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class DriveSubsystem extends SubsystemBase {
   // Creating all our variables, we will initialize them and set their values later
   //create motors : motorcontrolers are talon srx/ talon fx in code : check pheonix 5 docs
   WPI_TalonFX m_leftTalon;
   WPI_TalonFX m_rightTalon;
   WPI_TalonFX m_leftFollowerTalon;
   WPI_TalonFX m_rightFollowerTalon;
   DifferentialDrive m_robotDrive;
   //create differential drive or arcade drive : check WPILib docs
  

  public DriveSubsystem() {
     //initialize motor controllers
  m_leftTalon = new WPI_TalonFX(Constants.leftLeaderCANID);
  m_rightTalon = new WPI_TalonFX(Constants.rightLeaderCANID);
  m_leftFollowerTalon = new WPI_TalonFX(Constants.leftFollowerCANID);
  m_rightFollowerTalon = new WPI_TalonFX(Constants.rightFollowerCANID);

  


  
   
     
     //set to factory defaults
    m_leftTalon.configFactoryDefault();
    m_rightTalon.configFactoryDefault();
     //set motors to default to braking
    m_leftTalon.setNeutralMode(NeutralMode.Brake);
    m_rightTalon.setNeutralMode(NeutralMode.Brake);

    //create differential drive
    m_robotDrive = new DifferentialDrive(m_leftTalon, m_rightTalon);
  
    
    //Makes follower motors do the same thing as the leaders so that we don't have to pass arguments for all four
    m_leftFollowerTalon.follow(m_leftTalon);
    m_rightFollowerTalon.follow(m_rightTalon);

    // invert left motors from the right motors because they are inverted 180 degrees
    m_leftTalon.setInverted(true);
    m_leftFollowerTalon.setInverted(true);
    
    
  }

  public void drive(double power, double rotation) {
    //Drive command
     m_robotDrive.arcadeDrive(power, rotation);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  
  
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
