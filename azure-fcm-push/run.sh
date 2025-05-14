#!/bin/bash

# 引数取得（key=value）
while IFS='=' read -r key value; do
  case "$key" in
    hubName) hubName="$value" ;;
    connectionString) connectionString="$value" ;;
    deviceToken) deviceToken="$value" ;;
    platform) platform="$value" ;;
    jsonPayload) jsonPayload="$value" ;;
  esac
done < params.txt

# Debug log
echo "🔧 Hub: $hubName"
echo "🔧 Platform: $platform"
echo "🔧 Device Token: $deviceToken"
echo "🔧 Payload: $jsonPayload"

# Java実行
java -cp azure-push.jar:notification-hubs-java-sdk-1.1.0.jar AzurePushSender \
  "$hubName" "$connectionString" "$deviceToken" "$platform" "$jsonPayload"