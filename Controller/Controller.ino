#include <Bluepad32.h>
#include <ESP32Servo.h>

ControllerPtr myControllers[BP32_MAX_GAMEPADS];
Servo servos[4];  // Create servo objects to control servos

const int joystickPin = 34;  // Analog pin connected to the X-axis of the joystick


void onConnectedController(ControllerPtr ctl) {
    bool foundEmptySlot = false;
    for (int i = 0; i < BP32_MAX_GAMEPADS; i++) {
        if (myControllers[i] == nullptr) {
            Serial.printf("CALLBACK: Controller is connected, index=%d\n", i);
            ControllerProperties properties = ctl->getProperties();
            Serial.printf("Controller model: %s, VID=0x%04x, PID=0x%04x\n", ctl->getModelName().c_str(), properties.vendor_id,
                           properties.product_id);
            myControllers[i] = ctl;
            foundEmptySlot = true;
            break;
        }
    }
    if (!foundEmptySlot) {
        Serial.println("CALLBACK: Controller connected, but could not found empty slot");
    }
}

void onDisconnectedController(ControllerPtr ctl) {
    bool foundController = false;

    for (int i = 0; i < BP32_MAX_GAMEPADS; i++) {
        if (myControllers[i] == ctl) {
            Serial.printf("CALLBACK: Controller disconnected from index=%d\n", i);
            myControllers[i] = nullptr;
            foundController = true;
            break;
        }
    }

    if (!foundController) {
        Serial.println("CALLBACK: Controller disconnected, but not found in myControllers");
    }
}

void processControllers() {
    for (auto myController : myControllers) {
        if (myController && myController->isConnected() && myController->hasData()) {
            // Get the joystick value
            int joystickValue = myController->axisX();

            // Map the joystick value (0-1023) to servo position (0-180)
            int servoPosition = map(joystickValue, 0, 1023, 0, 180);

            // Set the servo position for all servos
            for (int i = 0; i < 4; ++i) {
                servos[i].write(servoPosition);
            }

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
    
    // Attach each servo to its corresponding GPIO pin
    servos[0].attach(12);
    servos[1].attach(2);
    servos[2].attach(0);
    servos[3].attach(4);

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
