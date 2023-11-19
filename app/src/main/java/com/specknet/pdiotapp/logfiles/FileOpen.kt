package com.specknet.pdiotapp.logfiles

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.specknet.pdiotapp.R
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException


class FileOpen : AppCompatActivity() {

    private lateinit var filepath: String
    private lateinit var filename: String
    private lateinit var tableLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.read_file)
        tableLayout = findViewById(R.id.table)
        filepath = intent.extras!!.getString("FilePath").toString()
        filename = intent.extras!!.getString("FileName").toString()
        openFile()
    }
    private fun openFile() {
//        var string: String? = ""
//        val stringBuilder = StringBuilder()
        val file = File(filepath)
        val reader = BufferedReader(FileReader(file))
//        while (true) {
//            try {
//                if (reader.readLine().also { string = it } == null) break
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//            stringBuilder.append(string).append("\n")
//        }

        val result: MutableList<List<String>> = ArrayList()
        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                val tokens = line!!.split(",".toRegex()).toTypedArray()
                if (tokens.isNotEmpty()) {
                    result.add(tokens.toList())
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        setupTable(result)

        reader.close()
    }



    /**
     * setup the table based on the report of the activities
     * @param activities
     */

    private fun setupTable(activities: List<List<String>>){
        // get the table layout
        tableLayout = findViewById(R.id.table)
        // loop to display the activities data in each row
        for (i in activities.indices) {
            // create a new row to be added
            val row = LinearLayout(this)
            row.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            // loop to display the activities data in each column
            for (j in activities[i].indices) {
                // create a new text view to be added
                val tv = TextView(this)
                tv.text = activities[i][j]
                if(activities[i][j] == "Activity" || activities[i][j] == "Duration"){
                    tv.background = getDrawable(R.drawable.table_blocks)
                    tv.setTextColor(getColor(R.color.colorPrimary))
                    tv.textSize = 16F
                    tv.layoutParams = LinearLayout.LayoutParams(
                        600,
                        140
                    )
                }
                else if((activities[i][j].contains("#") || activities[i][j] == "") && i <= 4){
                    tv.setTextColor(getColor(R.color.colorPrimary))
                    tv.textSize = 16F
                    tv.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }
                else{
                    tv.layoutParams = LinearLayout.LayoutParams(
                        600,
                        140
                    )
                    tv.background = getDrawable(R.drawable.table_blocks)
                }
                tv.setPadding(10, 10, 10, 10)
                row.addView(tv)

//                if(activities[i][j].contains("#")){
//                    val tv = TextView(this)
//                    tv.layoutParams = LinearLayout.LayoutParams(
//                        600,
//                        140
//                    )
//                    tv.text = " "
//                    tv.setTextColor(getColor(R.color.colorPrimary))
//                    tv.textSize = 16F
//                    tv.setPadding(10, 10, 10, 10)
//                    row.addView(tv)
//                }
            }

            // add the row to the table layout
            tableLayout.addView(row)
        }
    }

}
