package com.johanpalacios.golearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewTreeObserver

class MainActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler = Handler()
        handler.postDelayed({
            var Intent = Intent(this,dashboard::class.java)
            startActivity(Intent)
            finish()
        },1000)
}
}
