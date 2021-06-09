package com.app.spotifyclone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.spotifyclone.databinding.ActivityDetailsBinding
import com.app.spotifyclone.fragments.HomeFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        intent.extras?.let {
            var album = it.getString("album")
            var title = it.getString("title")

            val image = binding.imageDetailsAlbum
            val titleAlbum = binding.textAlbum

            Picasso.get().load(album).into(image)
            titleAlbum.text = title
        }

        // window.statusBarColor = Color.DKGRAY
        binding.toolbar.navigationIcon = getDrawable(R.drawable.ic_back)
        binding.toolbar.setNavigationOnClickListener {
            startActivities(Intent(this, HomeFragment::class.java))
            finish()
        }
    }

    private fun startActivities(intent: Intent) {}
}