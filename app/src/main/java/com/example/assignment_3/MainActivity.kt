package com.example.assignment_3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment_3.Adapter.RecyclerBookAdapter
import com.example.assignment_3.databinding.ActivityMainBinding
import com.example.assignment_3.model.Book
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val db = Firebase.database
    val mRef = db.reference
    private val TAG = "MainActivity"
    var myBooks: ArrayList<Book> = ArrayList()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Firebase.messaging.unsubscribeFromTopic("book_library")

        Firebase.messaging.subscribeToTopic("book_library")
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "user now subscribe the topic")
                } else {
                    Log.d(TAG, "not subscribe")
                }
            }

        Log.d(TAG, ".onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // add Recycler View items
        binding.rcyBooks.adapter = RecyclerBookAdapter(this, myBooks)
        binding.rcyBooks.layoutManager = LinearLayoutManager(this)


        // Notification messages
        if (checkGooglePlayServices()) {
            //Get Token
            FirebaseMessaging.getInstance().token
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                        return@addOnCompleteListener
                    }

                    val token = task.result
                    Log.d(TAG, "Token $token")


                }

        } else {
            Toast.makeText(this, "can't used notification from firebase", Toast.LENGTH_SHORT).show()
        }


        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddBookActivity::class.java))
        }

        // read from Realtime Database
        val readDate = ReadDate()
        val myRef = mRef.child("Books")
        myRef.addValueEventListener(readDate)

    }

    inner class ReadDate : ValueEventListener {
        @SuppressLint("NotifyDataSetChanged")

        override fun onDataChange(snapshot: DataSnapshot) {
            myBooks.clear()
            for (book in snapshot.children) {
                val myBook = Book(
                    book.key.toString(),
                    book.child("name").value.toString().trim(),
                    book.child("author").value.toString().trim(),
                    (book.child("year").value.toString().toInt()),
                    (book.child("rates").value.toString().toFloat()),
                    (book.child("price").value.toString().toInt())
                )
                myBooks.add(myBook)
            }
            binding.rcyBooks.adapter!!.notifyDataSetChanged()
        }

        override fun onCancelled(error: DatabaseError) {
            Log.d(TAG, "error with Exception : ${error.message}")
        }

    }


    private fun checkGooglePlayServices(): Boolean {
        val status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)
        return if (status != ConnectionResult.SUCCESS) {
            Log.e(TAG, "Error")
            false
        } else {
            Log.d(TAG, "Google play services updated")
            true
        }
    }

}