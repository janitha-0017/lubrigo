#include <BH1750.h>
#include <Wire.h>

unsigned long int milli_time;


BH1750 lightMeter;

void setup() {
  Serial.begin(9600);
  Serial.println("CLEARDATA");
  
  Wire.begin();
  
  lightMeter.begin();

  Serial.println("LABEL,Computer Time,Time (Milli Sec.),Lux");
}

void loop() {
  milli_time = millis();
  float total = 0;
  float lux = lightMeter.readLightLevel();
  for(int i=0; i<=5; i++){
    total = total+lux;
    delay(1000);    
  }
  Serial.print("DATA,TIME,");
  Serial.print(milli_time);
  Serial.print(",");
  Serial.println(total/10);
  
}
