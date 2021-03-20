package com.kirillshiryaev.mynavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kirillshiryaev.mynavigation.screens.chooser.StartFragment
import com.kirillshiryaev.mynavigation.screens.counter.CountFragment
import com.kirillshiryaev.mynavigation.screens.map.MapFragment
import com.kirillshiryaev.mynavigation.screens.stringer.StringerFragment
import com.kirillshiryaev.unav.ScreensManager

class MainActivity : AppCompatActivity()
{
    lateinit var router: ScreensManager.Router

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val screensManager = ScreensManager(supportFragmentManager).apply {
            add(CountFragment::class)
            add(StartFragment::class)
            add(MapFragment::class)
            add(StringerFragment::class)
        }

        router = screensManager.Router(R.id.fragment_container)

        router.setStartScreen(StartFragment::class)
    }
}