package kr.co.dantalk.codingTest.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Html
import android.util.Log
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import kr.co.dantalk.codingTest.R
import kr.co.dantalk.codingTest.api.CallApi
import kr.co.dantalk.codingTest.api.response.AlbumData
import kr.co.dantalk.codingTest.api.response.AlbumDetailData
import kr.co.dantalk.codingTest.api.response.AlbumItemResponse
import kr.co.dantalk.codingTest.databinding.ActivityDetailBinding
import kr.co.dantalk.codingTest.ui.dialog.LottieDialog

class DetailActivity : AppCompatActivity() {

    private val dataId by lazy { intent.getSerializableExtra(DATA_ID) as String }
    private val imgUri by lazy { intent.getSerializableExtra(IMAGE_URI) as String }
    private var data: AlbumDetailData? = null
    private val loadingDialog = LottieDialog()

    companion object {
        const val DATA_ID = "DATA_ID"
        const val IMAGE_URI = "IMAGE_URI"
        fun startActivityWithTransition(
            activity: Activity,
            id:String,
            imgUri: String,
            view: ImageView,
        ) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DATA_ID, id)
            intent.putExtra(IMAGE_URI, imgUri)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, view, view.transitionName
            )
            activity.startActivity(intent, options.toBundle())
        }
    }


    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        transitionImage()

        setData()
    }

    private fun setData() {
        loadingDialog.show(supportFragmentManager, null)
        CallApi().albumItem(dataId){ i: Int, s: String, albumData: AlbumDetailData? ->
            Log.e("CallApi().albumItem", "$i $s $albumData")
            data = albumData
            setView()
            loadingDialog.dismiss()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setView() {
        binding.titleTV.text = data?.name
        binding.artistTV.text = data?.artist
        binding.artistInfoTitleTV.text = "Who is ${data?.artist}?"
        binding.artistInfoTV.text = HtmlCompat.fromHtml(data?.artistInfo?:"", HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.albumInfoTitleTV.text = "What about ${data?.name}?"
        binding.albumInfoTV.text = HtmlCompat.fromHtml(data?.albumInfo?:"", HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    private fun transitionImage() {
        supportPostponeEnterTransition()
        Glide.with(this).load(imgUri).into(binding.mainIv)
        supportStartPostponedEnterTransition()
    }
}