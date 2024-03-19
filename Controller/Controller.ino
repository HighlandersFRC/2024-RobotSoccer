#include <Bluepad32.h>
#include <ESP32Servo.h>

ControllerPtr myControllers[BP32_MAX_GAMEPADS];
Servo myservo;  // Create a servo object to control a servo

const int joystickPin = 34;  // Analog pin connected to the X-axis of the joystick
const int servoPin = 12;     // GPIO pin connected to the servo

void onConnectedController(ControllerPtr ctl) {
    // Implementation of connection callback
}

void onDisconnectedController(ControllerPtr ctl) {
    // Implementation of disconnection callback
}

void processControllers() {
    for (auto myController : myControllers) {
        if (myController && myController->isConnected() && myController->hasData()) {
            // Get the joystick value
            int joystickValue = myController->axisX();

            // Map the joystick value (0-1023) to servo position (0-180)
            int servoPosition = map(joystickValue, 0, 1023, 0, 180);

            // Set the servo position
            myservo.write(servoPosition);

            // Print the joystick value and servo position for debugging
            Serial.print("Joystick Value: ");
            Serial.print(joystickValue);
            Serial.print(" - Servo Position: ");
            Serial.println(servoPosition);
        }
    }
}

void setup() {
    Serial.begin(115200);
    myservo.attach(servoPin);  // Attach the servo to the GPIO pin
    // Initialize Bluepad32
    BP32.setup(&onConnectedController, &onDisconnectedController);
    BP32.forgetBluetoothKeys();
    BP32.enableVirtualDevice(false);
}

void loop() {
    bool dataUpdated = BP32.update();
    if (dataUpdated)
        processControllers();
    delay(150);
}
