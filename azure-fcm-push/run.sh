#!/bin/bash

# 引数取得
while IFS='=' read -r key value; do
  case "$key" in
    hubName) hubName="$value" ;;
    connectionString) connectionString="$value" ;;
    fcmToken) fcmToken="$value" ;;
    jsonPayload) jsonPayload="$value" ;;
  esac
done < params.txt

# Java実行
java -cp azure-push.jar:notification-hubs-java-backend-0.0.5.jar AzurePushSender "$hubName" "$connectionString" "$fcmToken" "$jsonPayload"