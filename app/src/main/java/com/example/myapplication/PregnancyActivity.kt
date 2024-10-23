package com.example.myapplication


import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import ca.uhn.fhir.context.FhirContext
import ca.uhn.fhir.context.FhirVersionEnum
import com.google.android.fhir.datacapture.QuestionnaireFragment
import com.google.android.fhir.datacapture.mapping.ResourceMapper
import kotlinx.coroutines.launch
import org.hl7.fhir.r4.model.Questionnaire

class PregnancyActivity : AppCompatActivity() {
    var questionnaireJsonString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregnancy)


        // Step 2: Configure a QuestionnaireFragment
        questionnaireJsonString = getStringFromAssets("questionnaire.json")

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(
                    R.id.fragment_container_view,
                    QuestionnaireFragment.builder().setQuestionnaire(questionnaireJsonString!!).build()
                )
            }



            // Submit button callback
            supportFragmentManager.setFragmentResultListener(
                QuestionnaireFragment.SUBMIT_REQUEST_KEY,
                this,
            ) { _, _ ->
                submitQuestionnaire()

                // Get a questionnaire response
                val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
                        as QuestionnaireFragment
                val questionnaireResponse = fragment.getQuestionnaireResponse()

                // Print the response to the log
                val jsonParser = FhirContext.forCached(FhirVersionEnum.R4).newJsonParser()
                val questionnaireResponseString =
                    jsonParser.encodeResourceToString(questionnaireResponse)

                Log.d("response", questionnaireResponseString)

            }
        }
    }

    // This method handles the questionnaire submission
    private fun submitQuestionnaire() {
        // You can add logic here for actual submission (e.g., send the response to a server)
        Log.d("Submit", "Questionnaire has been submitted")
    }


    // Method to load the questionnaire JSON file from the assets folder
    private fun getStringFromAssets(fileName: String): String {
        return applicationContext.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    }
}
