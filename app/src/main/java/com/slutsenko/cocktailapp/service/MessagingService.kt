package com.slutsenko.cocktailapp.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.data.network.impl.extension.deserializeType
import com.slutsenko.cocktailapp.data.network.impl.extension.toJson
import com.slutsenko.cocktailapp.presentation.ui.activity.SplashActivity

class MessagingService : FirebaseMessagingService() {
    lateinit var manager: NotificationManager

    private val messageGson: Gson
        get() = GsonBuilder()
                .registerTypeAdapter(
                        deserializeType<MessagingModel>(),
                        MessagingDeserializer()
                )
                .setPrettyPrinting()
                .serializeNulls()
                .create()


    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("newtoken", p0)

    }


    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        if (p0.data.isNotEmpty()) {

            val notificationModel = messageGson.fromJson(
                    p0.data.toJson(GsonBuilder().create()),
                    MessagingModel::class.java
            )

            var intent: Intent? = null
            intent = Intent(this, SplashActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                putExtra("message", notificationModel)
            }

            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            createRegularNotification(pendingIntent, notificationModel)
        }
    }


    private fun createRegularNotification(
            pendingIntent: PendingIntent,
            model: MessagingModel
    ) {
        val builder = NotificationCompat.Builder(this, "NOTIFICATION_CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_notifications_24)
                .setContentTitle(model.title)
                .setContentText(model.body)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(9991, builder.build())
        }
    }

}