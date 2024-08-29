package frc.robot;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.unmanaged.Unmanaged;
import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Spec.Device;
import frc.robot.Spec.Message;

public class Robot extends TimedRobot {
  int id;
  boolean motorInit = false;

  TalonSRX dyno;
  TalonFX talon;

  @Override
  public void robotInit() {
    SmartDashboard.putBoolean("REQUESTSERVER", false);
    Unmanaged.loadPhoenix();

    // connectMotor();
  }
  

  @Override
  public void robotPeriodic() {
    if (SmartDashboard.getBoolean("REQUESTSERVER", false)) {
      connectMotor();
    }
    if (!motorInit) {
      return;
    }

    // motorInit = false;

    talon.setControl(new VoltageOut(4));

    
    SmartDashboard.putNumber("velocity", talon.getVelocity().getValueAsDouble());
    SmartDashboard.putNumber("supply current", talon.getSupplyCurrent().getValueAsDouble());
    SmartDashboard.putNumber("torque current", talon.getTorqueCurrent().getValueAsDouble());
    
    SmartDashboard.putNumber("srx", dyno.getMotorOutputVoltage());
    // for (int i = 0; i < 40; i += 2) {
    //     final int index = i;
    //     Commands.runOnce(() -> {
    //         dyno.set(ControlMode.PercentOutput, index / 10 / dyno.getBusVoltage());
    //     }).withTimeout(2);
    // }

    dyno.set(ControlMode.PercentOutput, 90);

    SmartDashboard.putBoolean("REQUESTSERVER", false);

  }

  private void connectMotor() {
    SmartDashboard.putBoolean("REQUESTSERVER", false);
    HttpClient httpClient = HttpClient.newHttpClient();
    //http://ip:1250/?action=getdevices
    HttpResponse<String> response;
    HttpRequest httpRequest = HttpRequest.newBuilder()
    .uri(URI.create("http://127.0.0.1:1250/?action=getdevices"))
    .GET()
    .build();
    
    try {
      response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

      ObjectMapper objectMapper = new ObjectMapper();
      Spec.Message data = objectMapper.readValue(response.body(), Spec.Message.class);
      data.deviceArray.forEach(device -> {
        System.out.println("Device ID: " + device.id);
        if (device.model.equals("Talon FX")) {
          id = device.id;
          System.out.println("Found Talon FX");
        }
      });
      // Files.write(Paths.get("/home/lvuser/output.json"), response.body().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
      
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }  
    
    dyno = new TalonSRX(62);
    talon = new TalonFX(id);
    motorInit = true;
  }
}
