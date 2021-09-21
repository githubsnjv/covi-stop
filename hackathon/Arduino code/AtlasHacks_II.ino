#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
#include "MAX30100_PulseOximeter.h"
// Connections : SCL PIN - D1 , SDA PIN - D2 , INT PIN - D0
PulseOximeter pox;
# define REPORTING_PERIOD_MS 1000
float BPM, SpO2;
uint32_t tsLastReport = 0;
 
// Set these to run example.
#define FIREBASE_HOST "atlashacksHOST"
#define FIREBASE_AUTH "AUTHENTICATION CODE"
#define WIFI_SSID "Your_SSID"
#define WIFI_PASSWORD "Ur_PASSWORD"
int val;
int tempPin = A0;
 
void onBeatDetected()
{
    Serial.println("Beat Detected!");
}
void setup() {
  Serial.begin(9600);
  // connect to wifi.
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
     Serial.begin(115200);
   
    pinMode(19, OUTPUT);
   // Blynk.begin(auth, ssid, pass);
 
    Serial.print("Initializing Pulse Oximeter..");
 
    if (!pox.begin())
    {
         Serial.println("FAILED");
         for(;;);
    }
    else
    {
         Serial.println("SUCCESS");
         pox.setOnBeatDetectedCallback(onBeatDetected);
    }
 
    // The default current for the IR LED is 50mA and it could be changed by uncommenting the following line.
        pox.setIRLedCurrent(MAX30100_LED_CURR_7_6MA);
}

void loop() {

 //val = analogRead(tempPin);
  float mv = ( val/1024.0)*3300;
  float cel = mv/10;
  float farh = (cel*9)/5 + 32;

  {
    pox.update();
   
 
    BPM = pox.getHeartRate();
    SpO2 = pox.getSpO2();
    if(SpO2>0)
   //for(int i=0;i<=1;i++)
{    
   if (millis() - tsLastReport > REPORTING_PERIOD_MS)
    {
          Serial.print("TEMPRATURE = ");
  Serial.print(farh);
  Serial.print("*F");
 Serial.println();
 Serial.print("/Heart rate:");
        Serial.print(BPM);
        Serial.print(" bpm / SpO2:");
        Serial.print(SpO2);
        Serial.println(" %");
              tsLastReport = millis();
               Firebase.setFloat("Temp",farh);
               Firebase.setFloat("oxy",SpO2);
               SpO2=0;
       
 
 // handle error
 if (Firebase.failed()) {
     Serial.print("setting /number failed:");
     Serial.println(Firebase.error());  
     return;
 }
    }}}

}
