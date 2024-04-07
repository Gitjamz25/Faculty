package com.example.faculty

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.faculty.registration.database.DatabaseHelper

class ListMemberActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    // Inside your activity class
    companion object {
        const val EDIT_MEMBER_REQUEST_CODE = 1 // You can choose any integer value
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_member)

        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)
        dbHelper = DatabaseHelper(this)

        val members = dbHelper.getAllMembers()


        for (member in members) {
            val row = TableRow(this)
            val nameTextView = TextView(this).apply {
                text = member.name
                layoutParams = TableRow.LayoutParams(0, resources.getDimensionPixelSize(R.dimen.textview_height), 1f)
                setBackgroundResource(R.drawable.border_column) // Apply border
            }
            val emailTextView = TextView(this).apply {
                text = member.email
                layoutParams = TableRow.LayoutParams(0, resources.getDimensionPixelSize(R.dimen.textview_height), 1f)
                setBackgroundResource(R.drawable.border_column) // Apply border
            }
            val phoneTextView = TextView(this).apply {
                text = member.phone
                layoutParams = TableRow.LayoutParams(0, resources.getDimensionPixelSize(R.dimen.textview_height), 1f)
                setBackgroundResource(R.drawable.border_column) // Apply border
            }

            // Update Button
            // Inside the click event handler for the update button
            val updateButton = Button(this).apply {
                text = "Update"
                layoutParams = TableRow.LayoutParams(0, resources.getDimensionPixelSize(R.dimen.button_height), 0.5f)
                setBackgroundResource(R.drawable.border_column) // Apply border
                setOnClickListener {
                    // Handle update button click
                    val memberNameToUpdate = nameTextView.text.toString()
                    val memberEmailToUpdate = emailTextView.text.toString()
                    val memberPhoneToUpdate = phoneTextView.text.toString()

                    // Create an intent to launch the EditMemberActivity and pass the specific details
                    val intent = Intent(this@ListMemberActivity, EditMemberActivity::class.java).apply {
                        putExtra("NAME", memberNameToUpdate)
                        putExtra("EMAIL", memberEmailToUpdate)
                        putExtra("PHONE", memberPhoneToUpdate)
                    }

                    // Start the EditMemberActivity with startActivityForResult to handle the result
                    startActivityForResult(intent, EDIT_MEMBER_REQUEST_CODE)
                }
            }


            // Delete Button
            val deleteButton = Button(this).apply {
                text = "Delete"
                layoutParams = TableRow.LayoutParams(0, resources.getDimensionPixelSize(R.dimen.button_height), 0.5f)
                setBackgroundResource(R.drawable.border_column) // Apply border
                setOnClickListener {
                    val memberNameToDelete = nameTextView.text.toString() // Get the name of the member to be deleted

                    // Call the delete method in your DatabaseHelper passing the member name
                    dbHelper.deleteMember(memberNameToDelete)

                    // Remove the row from the table layout
                    tableLayout.removeView(row)

                    // Optionally, you can display a message indicating successful deletion
                    Toast.makeText(context, "Member deleted successfully!", Toast.LENGTH_SHORT).show()
                }
            }

            row.addView(nameTextView)
            row.addView(emailTextView)
            row.addView(phoneTextView)
            row.addView(updateButton)
            row.addView(deleteButton)
            tableLayout.addView(row)
        }



    }
}
