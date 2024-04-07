package com.example.faculty


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.faculty.registration.database.DatabaseHelper

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        val button = findViewById<Button>(R.id.addMember)
        button.setOnClickListener {
            // Create and start the intent here
            val intent = Intent(this@Homepage, MemberActivity::class.java)
            startActivity(intent)
        }
        val button2 = findViewById<Button>(R.id.viewMember)
        button2.setOnClickListener {
            // Create and start the intent here
            val intent = Intent(this@Homepage, ListMemberActivity::class.java)
            startActivity(intent)
        }

    }
}