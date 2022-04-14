package com.example.assignment_3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_3.databinding.ActivityAddBookBinding
import com.example.assignment_3.model.Book
import com.example.assignment_3.model.MyNotification
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class AddBookActivity : AppCompatActivity() {
    val TAG = "AddBookActivity"
    lateinit var binding: ActivityAddBookBinding
    val db = Firebase.database
    val mRef = db.reference
    val myNotification = MyNotification(this)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.getSerializableExtra("book") != null) {
            binding.btnAdd.setText("update Book")
            val myBook = intent.getSerializableExtra("book") as Book

            binding.edName.setText(myBook.name)
            binding.edAuthor.setText(myBook.author)
            binding.edYear.setText(myBook.year.toString())
            binding.edPrice.setText(myBook.price.toString())
            binding.ratingBar2.rating = myBook.rates

            binding.btnAdd.setOnClickListener {
                if (binding.edName.text.isNotEmpty()
                    && binding.edAuthor.text.isNotEmpty()
                    && binding.edYear.text.isNotEmpty()
                    && binding.edPrice.text.isNotEmpty()
                    && binding.ratingBar2.rating != 0.0f
                ) {
                    updateBook(
                        Book(
                            myBook.id,
                            binding.edName.text.toString(),
                            binding.edAuthor.text.toString(),
                            binding.edYear.text.toString().toInt(),
                            binding.ratingBar2.rating.toString().toFloat(),
                            binding.edPrice.text.toString().toInt(),
                        )
                    )
                } else {
                    Toast.makeText(this, "Fill the Fields", Toast.LENGTH_SHORT).show()
                }
            }

            binding.btnDelete.setOnClickListener {
                deleteBook(myBook.id!!)
            }
        } else {
            binding.btnDelete.visibility = View.GONE
            binding.btnAdd.setOnClickListener {
                if (binding.edName.text.isNotEmpty()
                    && binding.edAuthor.text.isNotEmpty()
                    && binding.edYear.text.isNotEmpty()
                    && binding.edPrice.text.isNotEmpty()
                    && binding.ratingBar2.rating != 0.0f
                ) {
                    addBook(
                        Book(
                            null,
                            binding.edName.text.toString(),
                            binding.edAuthor.text.toString(),
                            binding.edYear.text.toString().toInt(),
                            binding.ratingBar2.rating.toString().toFloat(),
                            binding.edPrice.text.toString().toInt(),
                        )
                    )
                } else {
                    Toast.makeText(this, "Fill the Fields", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun addBook(book: Book) {
        mRef.child("Books").child(UUID.randomUUID().toString()).setValue(book)
            .addOnSuccessListener {
                myNotification.sendNotification("Add Book", "Book added Successfully")
                Toast.makeText(this, "Book added Successfully", Toast.LENGTH_SHORT).show()
            }
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun updateBook(book: Book) {
        val newBook = book.toMap()

        val childrenUpdates = hashMapOf<String, Any?>(
            book.id!! to newBook
        )

        mRef.child("Books").updateChildren(childrenUpdates)
            .addOnSuccessListener {
                myNotification.sendNotification("Update Book", "book updated Successfully")
                Toast.makeText(this, "Book updated Successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }

    private fun deleteBook(book_id: String) {
        mRef.child("Books").child(book_id).removeValue()
            .addOnSuccessListener {
                myNotification.sendNotification("Delete Book", "Book deleted Successfully")
                Toast.makeText(this, "Book deleted Successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }

}