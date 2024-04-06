package com.example.faculty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.faculty.registration.database.DatabaseHelper

// MemberActivity.kt
// MemberActivity.kt
class MemberActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var phoneInput: EditText

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)

        nameInput = findViewById(R.id.name)
        emailInput = findViewById(R.id.email)
        phoneInput = findViewById(R.id.phone)

        dbHelper = DatabaseHelper(this)

        findViewById<Button>(R.id.save_button).setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val phone = phoneInput.text.toString()

            if (validateInput(name, email, phone)) {
                val member = Member(name, email, phone)
                dbHelper.addMember(member)

                // Display success message
                Toast.makeText(this, "Member added successfully!", Toast.LENGTH_SHORT).show()

                finish() // Close the activity after saving
            }
        }
    }

    private fun validateInput(name: String, email: String, phone: String): Boolean {
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            // Display error message
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!email.contains("@")) {
            // Display error message
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            return false
        }

        if (phone.length < 10) {
            // Display error message
            Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}