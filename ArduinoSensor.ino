#include <BH1750.h>
#include <Wire.h>



BH1750 lightMeter;

void setup() {
  Serial.begin(9600);
  
  Wire.begin();
  
  lightMeter.begin();

  Serial.println(F("BH1750 Test begin"));
}

void loop() {
  
  float total = 0;
  for(int i=0; i<=5; i++){
    float lux = lightMeter.readLightLevel();
    total = total+lux;
    delay(1000);    
  }
  Serial.print("Light: ");
  Serial.print(total/10);
  Serial.println(" lx");
  
}
