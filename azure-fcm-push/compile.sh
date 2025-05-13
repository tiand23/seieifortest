#!/bin/bash

# コンパイル
javac -cp Notification-Hubs-java-sdk-0.4.2.jar AzurePushSender.java

# JARファイル生成
jar cfe azure-push.jar AzurePushSender AzurePushSender.class

echo "✅ コンパイル完了，azure-push.jar生成済み"