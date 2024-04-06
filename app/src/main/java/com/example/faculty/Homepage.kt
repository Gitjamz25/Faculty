package com.example.faculty


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        val button = findViewById<Button>(R.id.addMember)
        button.setOnClickListener {
            // Create and start the intent here
            val intent = Intent(this@Homepage, Login::class.java)
            startActivity(intent)
        }
    }
}