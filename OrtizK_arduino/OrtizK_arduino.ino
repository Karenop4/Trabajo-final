#include <Wire.h>
#include "MAX30105.h"
#include "heartRate.h"

MAX30105 particleSensor;

const byte RATE_SIZE = 4;
byte rates[RATE_SIZE];
byte rateSpot = 0;
long lastBeat = 0;

float beatsPerMinute;
int beatAvg;

const byte ledInSensor = 3; // Led dedo en el sensor
const byte ledNoFinger = 2; // Led dedo no en el sensor

unsigned long startTime;
const unsigned long duration = 60000; // 60 segundos

float spo2 = 0;

void setup() {
  Serial.begin(115200);

  // Initialize sensor
  if (!particleSensor.begin(Wire, I2C_SPEED_FAST)) {
    while (1);
  }

  particleSensor.setup(); // Configure sensor with default settings
  particleSensor.setPulseAmplitudeRed(0x0A); // Turn Red LED to low to indicate sensor is running
  particleSensor.setPulseAmplitudeGreen(0); // Turn off Green LED
  pinMode(ledInSensor, OUTPUT); // Initialize LED pin
  pinMode(ledNoFinger, OUTPUT); // Initialize LED pin

}

void loop() {
  measureData();
  if (Serial.available()) {
    char command = Serial.read();
    if (command == 'S') {
      measureData();
    }
  }
}

void measureData() {

  while (millis() - startTime < duration) {
    long irValue = particleSensor.getIR();
    long redValue = particleSensor.getRed();

    // Check for beat
    if (checkForBeat(irValue) == true) {
      // We sensed a beat!
      long delta = millis() - lastBeat;
      lastBeat = millis();

      beatsPerMinute = 60 / (delta / 1000.0);

      if (beatsPerMinute < 255 && beatsPerMinute > 20) {
        rates[rateSpot++] = (byte)beatsPerMinute;
        rateSpot %= RATE_SIZE;

        beatAvg = 0;
        for (byte x = 0; x < RATE_SIZE; x++)
          beatAvg += rates[x];
        beatAvg /= RATE_SIZE;
      }
    }

    // Calculate SpO2
    spo2 = calculateSpO2(redValue, irValue);
    
    Serial.print("BPM: ");
      Serial.print(beatsPerMinute);
      Serial.print(" | Avg BPM: ");
      Serial.print(beatAvg);
      Serial.print(" | SpO2: ");
      Serial.println(spo2);

    if (irValue < 50000) {
      digitalWrite(ledNoFinger, HIGH);
      digitalWrite(ledInSensor, LOW);
    } else {
      digitalWrite(ledInSensor, HIGH);
      digitalWrite(ledNoFinger, LOW);
    }

    delay(100); // Adjust the delay if necessary
  }

  // Final data output
  digitalWrite(ledInSensor, LOW);
  digitalWrite(ledNoFinger, LOW);
}

float calculateSpO2(long red, long ir) {
  float ratio = (float)red / (float)ir;
  float spo2 = 110 - 25 * ratio;

  if (spo2 > 100) spo2 = 100;
  if (spo2 < 0) spo2 = 0;

  return spo2;
}
