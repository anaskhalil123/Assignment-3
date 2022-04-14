package com.example.assignment_3.model

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.assignment_3.R
import org.json.JSONObject


var NOTIFICATION_ID = 1

class MyNotification(var context: Context) {

    private val notification_ID = 200
    private val CHANNEL_ID = "channel id"
    private val TAG = "MyNotification"
    private var queue: RequestQueue? = null

    @SuppressLint("UnspecifiedImmutableFlag")
    fun showNotification(id: Int, title: String, message: String, intent: Intent) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.setShowBadge(true)
            channel.description = message
            channel.enableLights(true)

            notificationManager.createNotificationChannel(channel)
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            notification_ID,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)

        val notification: Notification = builder
            .setSmallIcon(R.drawable.adaptive_icon)
            .setContentIntent(pendingIntent)
            .setContentText(message)
            .setContentTitle(title)
            .build()

        notificationManager.notify(id, notification)
    }

    /* fun sendNotification(title: String, message: String) {
         val stringRequest =
             object : StringRequest(
                 Request.Method.POST,
                 "https://fcm.googleapis.com/fcm/send",
                 Response.Listener { it ->
                     Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                 },
                 Response.ErrorListener { it ->
                     Log.e(TAG, it.toString())
                 }) {

                 override fun getBody(): ByteArray? {
                     val jsonBody = JSONObject()
                     jsonBody.put(
                         "to",
                         "cZBz_U-cTwq6xifZ6rfs1S:APA91bE2q2bRifD0R5LOBq_YmTx_ozh3FgaVf9aCVXYddDsTX7Cn-_q3_oinUrfXRUkCHpwsxSKNLwYLmQuunETq-lNYtwYaWo3UHXToMnecMIsxG0F67HythCRpmDHU0EuATpvC0Q3A"
                     )
                     jsonBody.put(
                         "notification",
                         JSONObject().put("body", "Hello").put("title", "This is test message.")
                     )
                     val requestBody = jsonBody.toString()
                     return try {
                         requestBody.toByteArray(Charset.defaultCharset())
                     } catch (uee: UnsupportedEncodingException) {
                         VolleyLog.wtf(
                             "Unsupported Encoding while trying to get the bytes of %s using %s",
                             requestBody,
                             "utf-8"
                         )
                         null
                     }
                 }

                 override fun getHeaders(): MutableMap<String, String> {
                     val headers = HashMap<String, String>()
                     headers.put("Content-Type", "application/json; charset=utf-8")
                     headers.put(
                         "Authorization",
                         "key=AAAAY8t94KE:APA91bG8KefbFTsoHf_S4tqzw7yTwFRhskfp924M0TSDSFEMKTz_zix_fEzE4EroykoiFo8lD8k_RPTvz2dK5VWc17dhQbYS9WJrXQWUISvKNMSKxtLHx3NcsBsvio8-qIlS3XNivdC8"
                     )
                     return headers
                 }

             }
         VolleySingleton.getInstance(context).addToRequestQueue(stringRequest)
     }*/

    fun sendNotification(title: String, message: String) {
        try {

            // queue of requests
            if (queue != null) {
                queue?.cancelAll { true }
                Log.d(TAG, "cancel all the requests , with sequenceNumber ${queue?.sequenceNumber}")
            } else {
                queue = Volley.newRequestQueue(context)
            }

            // data format of for request body
            val data: JSONObject = JSONObject()
                .put("title", title)
                .put("body", message)

            val notificationData = JSONObject()
                .put("notification", data)
                .put(
                    "to",
                    "/topics/book_library"
                )

            // request format
            val request = object : JsonObjectRequest(
                Request.Method.POST, URLs.FIREBASE_CLOUD_MESSAGING_URL, notificationData,
                {

                }, {
                    Log.e(TAG, "request fail due to ${it.message}")
                }) {
                // add headers
                override fun getHeaders(): MutableMap<String, String> {
                    val api_key =
                        "key=AAAAY8t94KE:APA91bG8KefbFTsoHf_S4tqzw7yTwFRhskfp924M0TSDSFEMKTz_zix_fEzE4EroykoiFo8lD8k_RPTvz2dK5VWc17dhQbYS9WJrXQWUISvKNMSKxtLHx3NcsBsvio8-qIlS3XNivdC8"
                    val headers = HashMap<String, String>()
                    headers["Content-Type"] = "application/json"
                    headers["Authorization"] = api_key
                    return headers
                }
            }

            // add request to the volley request queue
            queue?.add(request)
        } catch (e: Exception) {

            Log.e(TAG, "send request fail due to  ${e.message}")
        }

        /*
        * resource for volley with cloud messaging:
        *  https://mobikul.com/android-sending-notification-app-via-firebase-console/
        */

    }

}