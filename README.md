📢 前提条件：
本資料は、Win11 ローカル環境において JAR を使用して Azure Notification Hubs への送信が成功するかどうかを検証するためのものです。
⚠️ 注意：本資料では最終的なアプリ側で通知を受信できるかどうかは検証できません。あくまでバックエンド側の送信確認のみを目的としています。

🌐 1. Azure 環境の準備

✅ 必須の入力条件：
- Azure アカウントとサブスクリプション
- Azure Portal で以下を作成：
    ・Notification Hubs 名前空間
    ・Notification Hub
    ・Access Policies から以下を取得：
        - Hub 名（例：my-hub）
        - Namespace 名（例：mynamespace）
        - Connection String（DefaultFullSharedAccessSignature）

Endpoint=sb://mynamespace.servicebus.windows.net/;
SharedAccessKeyName=DefaultFullSharedAccessSignature;
SharedAccessKey=XXXXXXXXXXXXXXXXXXXXXX

✅ プラットフォーム設定：
- iOS → APNs 証明書
- Android → FCM Server Key
- Windows → WNS Access Token

------------------------------------

💻 2. Win11 環境の準備

2.1 ネットワーク接続確認（コマンド例）

nslookup mynamespace.servicebus.windows.net
ping mynamespace.servicebus.windows.net
Test-NetConnection mynamespace.servicebus.windows.net -Port 443
openssl s_client -connect mynamespace.servicebus.windows.net:443

------------------------------------

2.2 Java 環境の準備

✅ JDK インストール（推奨 JDK 11 以上）
✅ Maven インストール（依存管理）

pom.xml 依存設定：
```xml
    <dependency>
    <groupId>com.windowsazure</groupId>
    <artifactId>Notification-Hubs-java-sdk</artifactId>
    <version>1.1.0</version>
</dependency>
```
------------------------------------

2.3 Java コード例（引数対応版）

import com.windowsazure.messaging.*;

public class NotificationTest {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: java NotificationTest <ConnectionString> <HubName> <Payload>");
            System.exit(1);
        }

        String connectionString = args[0];
        String hubName = args[1];
        String payload = args[2];

        try {
            NotificationHub hub = new NotificationHub(hubName, connectionString);
            Notification notification = Notification.createAppleNotification(payload);
            NotificationOutcome outcome = hub.sendNotification(notification);
            System.out.println("Notification sent, outcome: " + outcome.getState());
        } catch (Exception e) {
            System.err.println("Error sending notification: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

------------------------------------

2.4 ビルド・実行コマンド

Maven ビルド：
mvn clean compile
mvn package

Maven 実行：
mvn exec:java -Dexec.mainClass="NotificationTest" -Dexec.args="<ConnectionString> <HubName> \"<Payload>\""

手動コンパイル・実行：
javac -cp .;path\to\Notification-Hubs-java-sdk-1.1.0.jar NotificationTest.java
java -cp .;path\to\Notification-Hubs-java-sdk-1.1.0.jar NotificationTest "<ConnectionString>" "<HubName>" "{\"aps\":{\"alert\":\"Hello from Java!\"}}"

------------------------------------

🏗 入力パラメータ例

ConnectionString：
Endpoint=sb://mynamespace.servicebus.windows.net/;SharedAccessKeyName=DefaultFullSharedAccessSignature;SharedAccessKey=xxxxx

HubName：
my-hub

Payload：
{"aps":{"alert":"Hello from Java!"}}

------------------------------------

⚠️ よくあるエラーと対策

Unauthorized / 401 エラー：
→ DefaultFullSharedAccessSignature を使用しているか確認

NoSuchMethodError：
→ JDK を 11 以上に更新

HttpClient バージョン衝突：
→ Maven で依存を統一管理

タイムアウト：
→ ファイアウォール・VPN・プロキシ設定を確認

------------------------------------
Notification Hub → 左侧菜单 → 診断ログ（診断設定 / Logs）


📦 提供可能な追加リソース

・Maven プロジェクトテンプレート
・Windows .bat スクリプト
・Android (FCM)、Windows (WNS) コード例

必要な場合：
「Maven テンプレート希望」
「.bat スクリプト希望」
「Android コード例希望」
とお知らせください！
