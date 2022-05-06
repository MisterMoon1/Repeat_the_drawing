package com.example.repeat_the_drawing

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity2 : AppCompatActivity(), View.OnTouchListener {

    private lateinit var imageView: ImageView
    private lateinit var bitMap: Bitmap
    private lateinit var canvas: Canvas
    private lateinit var paint: Paint
    private var downX = 0f
    private var downY = 0f
    private var upX = 0f
    private var upY = 0f

    @RequiresApi(Build.VERSION_CODES.R)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val button2: Button = findViewById(R.id.button2)

        button2.setOnClickListener{
            val intent = Intent(this@MainActivity2, MainActivity3::class.java)
            startActivity(intent)
        }

        imageView = findViewById(R.id.imageView1)
        val currentDisplay = windowManager.currentWindowMetrics
        val dw = currentDisplay.bounds.width()
        val dh = currentDisplay.bounds.height()

        bitMap = Bitmap.createBitmap(dw, dh, Bitmap.Config.ARGB_8888)

        canvas = Canvas(bitMap)

        paint = Paint()
        paint.color = Color.RED
        paint.strokeWidth = 10F
        imageView.setImageBitmap(bitMap)
        imageView.setOnTouchListener(this)

    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
            }

            MotionEvent.ACTION_UP -> {
                upX = event.x
                upY = event.y
                canvas.drawLine(downX, downY, upX, upY, paint)
                imageView.invalidate()
            }
        }
        return true
    }
}