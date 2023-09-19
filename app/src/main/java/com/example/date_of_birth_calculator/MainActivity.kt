package com.example.date_of_birth_calculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View){
        val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)


        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val mounth = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{
                    view, selectedYear, selectedMounth, selectedDayOfMounth ->
                Toast.makeText(this,  "The chosen year is $selectedYear, the mounth is $selectedMounth, and day is $selectedDayOfMounth", Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedDayOfMounth/${selectedMounth+1}/$selectedYear"
                tvSelectedDate.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate  = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate!!.time /60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateInMinutes = currentDate!!.time / 60000

                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
            }, year, mounth, day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}