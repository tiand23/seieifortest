#!/bin/bash

javac -cp Notification-Hubs-java-sdk-1.1.0.jar AzurePushSender.java
jar cfe azure-push.jar AzurePushSender AzurePushSender.class