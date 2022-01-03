package uz.shokirov.iqtisod

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.shokirov.iqtisod.databinding.ActivityAboutBinding


class AboutActivity : AppCompatActivity() {
    lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.telegram.setOnClickListener {
            linkContact("https://t.me/shokirov_ollohberdi")
        }

        binding.tiktok.setOnClickListener {
            linkContact("https://vm.tiktok.com/ZSeyUsxFb/")
        }

        binding.instagram.setOnClickListener {
            linkContact("https://www.instagram.com/shokirov.blog/")
        }


    }

    private fun linkContact(link: String) {
        val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(myIntent)
    }
}