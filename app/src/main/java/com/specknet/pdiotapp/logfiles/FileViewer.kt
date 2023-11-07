package com.specknet.pdiotapp.logfiles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.specknet.pdiotapp.R
import java.io.File

class FileViewer : AppCompatActivity() {

    private val activity = this@FileViewer
    private lateinit var fileAdapter: FileRecyclerAdapter
    private lateinit var fileRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_files)
        initRecyclerView()
        addData()
    }

    private fun initRecyclerView() {
        fileRecycler = findViewById(R.id.fileRecyclerViewer)
        fileRecycler.layoutManager = LinearLayoutManager(this@FileViewer)
        fileAdapter = FileRecyclerAdapter()
        fileRecycler.adapter = fileAdapter
    }

    private fun addData() {
        val email = intent.extras!!.getString("email").toString()
        val hfile = File(getExternalFilesDir(null)!!.absolutePath + "/" + email)
        val data = hfile.listFiles()!!.toList().sortedByDescending {x -> x.lastModified()}
        fileAdapter.submitList(data.toMutableList())
    }
}