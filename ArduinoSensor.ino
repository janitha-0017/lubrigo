#include <BH1750.h>
#include <Wire.h>

unsigned long int milli_time;
int ledRed = 13;
int ledGreen = 12;

BH1750 lightMeter;

void setup() {
  Serial.begin(9600);
  Serial.println("CLEARDATA");
  
  Wire.begin();
  pinMode(ledRed, OUTPUT);
  pinMode(ledGreen, OUTPUT);
  
  lightMeter.begin();

  Serial.println("LABEL,Computer Time,Time (Milli Sec.),Intensity (Average Lux)");
  
//  milli_time = millis();
//  float total = 0;
//  float count = 0;
//  
//  for(int i=0; i<=5; i++){
//    float lux = lightMeter.readLightLevel();
//    Serial.println(lux);
//    total = total+lux;
//    count = count + 1;
//    delay(1000);
//    digitalWrite(ledRed, HIGH);    
//  }
//  digitalWrite(ledRed, LOW);
//  digitalWrite(ledGreen, HIGH);
//  
//  Serial.print("DATA,TIME,");
//  Serial.print(milli_time);
//  Serial.print(",");
//  Serial.println(total/count);
}

void loop() {
  milli_time = millis();
  float total = 0;
  float count = 0;
  
  for(int i=0; i<=5; i++){
    float lux = lightMeter.readLightLevel();
    Serial.println(lux);
    total = total+lux;
    count = count + 1;
    delay(1000);
    digitalWrite(ledRed, HIGH);    
  }
  digitalWrite(ledRed, LOW);
  digitalWrite(ledGreen, HIGH);
  
  Serial.print("DATA,TIME,");
  Serial.print(milli_time);
  Serial.print(",");
  Serial.println(total/count);
  
}
