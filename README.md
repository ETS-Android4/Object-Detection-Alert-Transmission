# Object-Detection-Alert-Transmission
This is an Android application which detects different objects and sends message to other Android devices having the application when a particular object is detected.
This project is built using the Tensorflow Lite Object Detection Android app provided by TensorFlow.
I have used the Paho Mqtt Android Library which uses the Mqtt protocol for sending and receiving messages.
Whenever a particular object is detected, it sends alert message to nearby devices having the application, all of which are on the same network. This entire process works offline leveraging the power of MQTT. Currently this app sends alerts for human detection but can be modified for other objects. It uses quantized MobileNet SSD model trained on COCO dataset for object detection. This model is also provided by Tensorflow but we can also use our trained model. 
Its application can mainly be found in surveillance of areas with poor internet connectivity. Since MQTT is a popular protocol in IOT, this technology can be expanded beyond Android to work on IOT devices. 
