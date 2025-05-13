import com.windowsazure.messaging.NotificationHub;
import com.windowsazure.messaging.NotificationOutcome;

public class AzurePushSender {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("四つ引数：hubName, connectionString, fcmToken, jsonPayload");
            return;
        }

        String hubName = args[0];
        String connectionString = args[1];
        String fcmToken = args[2];
        String jsonPayload = args[3];

        try {
            NotificationHub hub = new NotificationHub(hubName, connectionString);
            NotificationOutcome outcome = hub.sendGcmNativeNotification(jsonPayload, fcmToken);

            if (outcome != null) {
                System.out.println("✅ PUSH SUCCESS：" + outcome.getState());
            } else {
                System.out.println("❌ ERROR：outcome is null");
            }
        } catch (Exception e) {
            System.err.println("異常：" + e.getMessage());
            e.printStackTrace();
        }
    }
}