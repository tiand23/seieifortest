import com.windowsazure.messaging.Notification;
import com.windowsazure.messaging.NotificationHub;
import com.windowsazure.messaging.NotificationOutcome;

public class AzurePushSender {
    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("使い方：");
            System.out.println("java AzurePushSender <hubName> <connectionString> <notificationId(tag)> <platform> <jsonPayload>");
            System.out.println("例：java AzurePushSender pushhub \"Endpoint=...\" 通知ID001 android '{\"data\":{\"message\":\"こんにちは\"}}'");
            return;
        }

        String hubName = args[0];
        String connectionString = args[1];
        String notificationId = args[2]; // ← これが tag（通知ID）
        String platform = args[3].toLowerCase(); // ios or android
        String jsonPayload = args[4];

        try {
            NotificationHub hub = new NotificationHub(hubName, connectionString);
            Notification notification;

            if (platform.equals("android")) {
                notification = Notification.createFcmV1Notification(jsonPayload);
            } else if (platform.equals("ios")) {
                notification = Notification.createAppleNotification(jsonPayload);
            } else {
                System.err.println("❌ プラットフォームが不正です：ios もしくは android を指定してください");
                return;
            }

            NotificationOutcome outcome = hub.sendNotification(notification, notificationId); // tag を使って送信

            if (outcome != null) {
                System.out.println("✅ PUSH SUCCESS: " + outcome.getState());
            } else {
                System.out.println("❌ ERROR: outcome is null");
            }
        } catch (Exception e) {
            System.err.println("送信エラー：" + e.getMessage());
            e.printStackTrace();
        }
    }
}