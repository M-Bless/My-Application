package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        clickListener();
    }
    public fun clickListener() {

        val cardMenstruation = findViewById<CardView>(R.id.cvMenstruation)
        val cardPregnancy = findViewById<CardView>(R.id.cvPregnancy)
        val cardOvulation = findViewById<CardView>(R.id.cvOvulation)
        val cardExercise = findViewById<CardView>(R.id.cvExercise)
        val cardExamination = findViewById<CardView>(R.id.cvBreastExamination)
        val cardCramping = findViewById<CardView>(R.id.cvCramps)

        cardMenstruation.setOnClickListener {
            openMenstruationActivity()
        }

        cardPregnancy.setOnClickListener {
            openPregnancyActivity()
        }

        cardOvulation.setOnClickListener {
            openOvulationActivity()
        }

        cardOvulation.setOnClickListener {
            openOvulationActivity()
        }

        cardExercise.setOnClickListener {
            openExerciseActivity()
        }


        cardExamination.setOnClickListener {
            openExaminationActivity()
        }

        cardCramping.setOnClickListener {
            openCrampingActivity()
        }

    }
    public fun openCrampingActivity() {
        startActivity(Intent(this@DashboardActivity,CrampingActivity::class.java))
    }

    public fun openExaminationActivity() {
        startActivity(Intent(this@DashboardActivity,ExaminationActivity::class.java))
    }

    public fun openExerciseActivity() {
        startActivity(Intent(this@DashboardActivity,ExerciseActivity::class.java))
    }


    public fun openOvulationActivity() {
        startActivity(Intent(this@DashboardActivity,OvulationActivity::class.java))
    }


    public fun openMenstruationActivity(){
        startActivity(Intent( this@DashboardActivity, MenstruationActivity::class.java))
    }

    public fun openPregnancyActivity(){

        startActivity(Intent( this@DashboardActivity, PregnancyActivity::class.java))
    }
}
