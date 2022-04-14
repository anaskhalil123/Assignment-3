package com.example.assignment_3

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import com.example.assignment_3.model.MyNotification
import com.example.assignment_3.model.NOTIFICATION_ID
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FirebaseService : FirebaseMessagingService() {
    private val TAG = "FirebaseService"


    override fun onMessageReceived(message: RemoteMessage) {
        Log.d(TAG, "From: ${message.from}")

        if (message.notification != null) {
            Log.d(TAG, "Message Notification Body: ${message.notification?.body}")
            Log.d(TAG, "Message Notification Body: ${message.notification?.title}")
            Log.d(TAG, "Message Notification Body: ${message.data}")

            //show notification
            MyNotification(this).showNotification(
                NOTIFICATION_ID,
                message.notification?.title!!,
                message.notification!!.body!!,
                Intent(this, FirebaseService::class.java)
            )
            NOTIFICATION_ID++
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

}