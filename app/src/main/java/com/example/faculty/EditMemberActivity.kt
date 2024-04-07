package com.example.faculty

// EditMemberActivity.kt
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.faculty.registration.database.DatabaseHelper

class EditMemberActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var saveButton: Button

    // Inside EditMemberActivity.kt

    // Inside EditMemberActivity.kt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_member)

        // Retrieve member details passed from ListMemberActivity
        val originalName = intent.getStringExtra("NAME") ?: ""
        val originalEmail = intent.getStringExtra("EMAIL") ?: ""
        val originalPhone = intent.getStringExtra("PHONE") ?: ""

        // Find EditText fields and set their initial values to the original member details
        nameEditText = findViewById<EditText>(R.id.nameEditText).apply { setText(originalName) }
        emailEditText = findViewById<EditText>(R.id.emailEditText).apply { setText(originalEmail) }
        phoneEditText = findViewById<EditText>(R.id.phoneEditText).apply { setText(originalPhone) }

        // Find the Save button
        saveButton = findViewById<Button>(R.id.saveButton)

        // Set click listener for the Save button
        saveButton.setOnClickListener {
            // Retrieve updated member details from EditText fields
            val updatedName = nameEditText.text.toString()
            val updatedEmail = emailEditText.text.toString()
            val updatedPhone = phoneEditText.text.toString()

            // Update the database with the new member details
            val dbHelper = DatabaseHelper(this)
            dbHelper.updateMember(originalName, updatedName, updatedEmail, updatedPhone)

            // Provide confirmation to the user
            Toast.makeText(this, "Member details updated successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@EditMemberActivity, ListMemberActivity::class.java)
            startActivity(intent)
            // Finish the activity
            finish()
        }
    }



}
