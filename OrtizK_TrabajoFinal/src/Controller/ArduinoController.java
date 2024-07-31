/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ArduinoController {
    public SerialPort comPort;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ArduinoController(String portName) {
        comPort = SerialPort.getCommPort(portName);
        comPort.setComPortParameters(115200, 8, 1, 0);
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);
    }
    
    

    public void connect() {
        if (comPort.openPort()) {
            System.out.println("Port is opened.");
            inputStream = comPort.getInputStream();
            outputStream = comPort.getOutputStream();
        } else {
            System.out.println("Failed to open port.");
        }
    }

    public void disconnect() {
        if (comPort.closePort()) {
            System.out.println("Port is closed.");
        } else {
            System.out.println("Failed to close port.");
        }
    }

    public SerialPort sendCommand(char command) {
        if (comPort.isOpen()) {
            try {
                outputStream.write((byte) command);
                outputStream.flush();
                System.out.println("Command sent: " + command);
            } catch (IOException e) {
                System.out.println("Error writing to output stream: " + e.getMessage());
            }
            return comPort;
        } else {
            System.out.println("Port is not open.");
            return null;
        }
    }

    public String readResponse() {
        if (comPort.isOpen()) {
            try {
                byte[] readBuffer = new byte[1024];
                int numRead = inputStream.read(readBuffer);
                if (numRead > 0) {
                    String response = new String(readBuffer, 0, numRead).trim();
                    return response;
                } else {
                    System.out.println("No se leyeron datos.");
                }
            } catch (IOException e) {
                System.out.println("Error al leer del flujo de entrada: " + e.getMessage());
            }
        } else {
            System.out.println("El puerto no está abierto.");
        }
        return null;
    }
}
