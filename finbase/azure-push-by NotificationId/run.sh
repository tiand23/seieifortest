#!/bin/bash

# パラメータ読込
while IFS='=' read -r key value; do
  case "$key" in
    hubName) hubName="$value" ;;
    connectionString) connectionString="$value" ;;
    notificationId) notificationId="$value" ;;
    platform) platform="$value" ;;
    jsonPayload) jsonPayload="$value" ;;
  esac
done < params.txt

# 実行
java -cp azure-push.jar:Notification-Hubs-java-sdk-1.1.0.jar AzurePushSender \
  "$hubName" "$connectionString" "$notificationId" "$platform" "$jsonPayload"