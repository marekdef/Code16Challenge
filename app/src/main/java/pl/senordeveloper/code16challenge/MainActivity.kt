package pl.senordeveloper.code16challenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.senordeveloper.code16challenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)
    }
}