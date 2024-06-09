package com.example.screentime1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button

class DetailedViewActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view)


        val dates = intent.getStringArrayListExtra("dates") ?: arrayListOf()
        val morningScreenTimes = intent.getIntegerArrayListExtra("morningScreenTimes") ?: arrayListOf()
        val afternoonScreenTimes = intent.getIntegerArrayListExtra("afternoonScreenTimes") ?: arrayListOf()
        val activityNotes = intent.getStringArrayListExtra("activityNotes") ?: arrayListOf()

        val detailsTextView = findViewById<TextView>(R.id.ViewDetailsTextView)
        val averageScreenTimeTextView = findViewById<TextView>(R.id.averageTextView)
        val backButton = findViewById<Button>(R.id.BackButton)

        var totalScreenTime = 0
        var displayText = ""
        for (i in dates.indices) {
            val dailyScreenTime = morningScreenTimes[i] + afternoonScreenTimes[i]
            totalScreenTime += dailyScreenTime
            displayText += "Date: ${dates[i]}, Morning: ${morningScreenTimes[i]} min, Afternoon: ${afternoonScreenTimes[i]} min, Notes: ${activityNotes[i]}\n\n"
        }
        val averageScreenTime = if (dates.isNotEmpty()) totalScreenTime / dates.size else 0

        detailsTextView.text = displayText
        averageScreenTimeTextView.text = "Average Screen Time: $averageScreenTime minutes/day"

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
}


