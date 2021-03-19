#include <BH1750.h>
#include <Wire.h>

unsigned long int milli_time;


BH1750 lightMeter;

void setup() {
  Serial.begin(9600);
  Serial.println("CLEARDATA");
  
  Wire.begin();
  
  lightMeter.begin();

  Serial.println("LABEL,Computer Time,Time (Milli Sec.),Intensity (Average Lux)");
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
  }
  Serial.print("DATA,TIME,");
  Serial.print(milli_time);
  Serial.print(",");
  Serial.println(total/count);
  
}
