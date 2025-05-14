# AZURE-FCM-PUSH（通知IDベース）

このツールは、Azure Notification Hubs を使ってローカルからプッシュ通知を送信する Java ベースの検証用スクリプトです。  
本バージョンは **「通知ID（＝タグ）」を使って送信対象を指定** する方式に対応しています。

---

## 📁 構成ファイル一覧

| ファイル名 | 説明 |
|------------|------|
| `AzurePushSender.java` | 通知IDを使ってプッシュ通知を送る Java プログラム |
| `Notification-Hubs-java-sdk-1.1.0.jar` | Azure SDK の JAR ライブラリ |
| `params_android.txt` | Android 向けのパラメータ定義ファイル |
| `params_ios.txt` | iOS 向けのパラメータ定義ファイル |
| `run.sh` | 実行スクリプト（params.txt を読み込んで送信） |
| `compile.sh` | Java コードのビルドスクリプト |
| `README_ja.md` | 本ドキュメント |

---

## ✅ 前提条件

- JDK 8 以上がインストール済みであること
- Azure Notification Hub が作成済みであること
- 事前に以下の情報を取得済み：
  - Hub 名
  - 接続文字列（`DefaultFullSharedAccessSignature` のフルアクセスキー）
- 通知対象のデバイス（iOS/Android）について、Azure Notification Hubs に対して以下のように登録済みであること：
  - `デバイストークン`
  - `通知ID`（タグとして登録）
  - `プラットフォーム（ios / android）`

---

## 📄 パラメータファイル（params.txt）のフォーマット

```txt
hubName=＜Hub 名＞
connectionString=＜接続文字列＞
notificationId=＜通知ID（タグ）＞
platform=ios または android
jsonPayload=＜通知メッセージ（JSON形式）＞



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