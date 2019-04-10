package com.example.p6l2

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showDetail.setOnClickListener {
            var myDetailIntent = Intent(this, DetailActivity::class.java)
            startActivity(myDetailIntent)
        }

        doAsync {
            Thread.sleep(5000L)
            uiThread {
                showNotivy();
            }
        }
    }

    private fun showNotivy() {
        val notfyDetailIntent = Intent(this@MainActivity,
            DetailActivity::class.java)

        val myPendingIntent = TaskStackBuilder.create(this)
            .addParentStack(DetailActivity::class.java)
            .addNextIntent(notfyDetailIntent)
            .getPendingIntent(110, PendingIntent.FLAG_UPDATE_CURRENT)

        val myNotfyManager = this.getSystemService(android.content.Context.NOTIFICATION_SERVICE) as NotificationManager

        val myBuilder = NotificationCompat.Builder(this)
            .setContentTitle("Show Detail Contact")
            .setContentText("Click Me !")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(myPendingIntent)
            .setAutoCancel(true)
        myNotfyManager.notify(1101, myBuilder.build())
    }
}
