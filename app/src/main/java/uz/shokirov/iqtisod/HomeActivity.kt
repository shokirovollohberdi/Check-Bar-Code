package uz.shokirov.iqtisod

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.shokirov.iqtisod.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardScanner.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
         binding.cardInput.setOnClickListener {
            startActivity(Intent(this,InputActivity::class.java))
        }
         binding.cardInfo.setOnClickListener {
            startActivity(Intent(this,AboutActivity::class.java))
        }


    }
}