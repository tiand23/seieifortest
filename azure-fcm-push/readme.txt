# AZURE-FCM-PUSH

このツールは、Java を使ってローカルから Azure Notification Hubs 経由でプッシュ通知を送信するシンプルな検証用スクリプトです。  
Android（FCM）および iOS（APNs）の両方に対応しています。

---

##  構成ファイル一覧

| ファイル名 | 説明 |
|------------|------|
| `AzurePushSender.java` | プッシュ送信用 Java プログラム |
| `Notification-Hubs-java-sdk-1.1.0.jar` | Azure Notification Hub SDK（v1.1.0） |
| `params_android.txt` | Android 用の送信パラメータ |
| `params_ios.txt` | iOS 用の送信パラメータ |
| `compile.sh` | Java コードのコンパイル用スクリプト |
| `run.sh` | プッシュ送信実行スクリプト（params.txt を読み込む） |

---

## 事前準備

- Java（JDK 8 以上）がインストールされていること
- Azure Notification Hub が作成済みで、以下の情報が取得できること：
  - Hub 名
  - 接続文字列（Connection String）※送信権限付き（`DefaultFullSharedAccessSignature`）
  - FCM／APNs の設定済み
- 実際のデバイスから取得した有効な FCM トークン または APNs トークン

---

##  コンパイル方法

以下のコマンドを実行してください：

```bash
bash compile.sh



##実行方法
Android に通知を送信する場合：
cp params_android.txt params.txt
bash run.sh

iOS に通知を送信する場合：
cp params_ios.txt params.txt
bash run.sh