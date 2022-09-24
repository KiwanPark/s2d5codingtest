package kr.co.dantalk.codingTest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kr.co.dantalk.codingTest.R
import kr.co.dantalk.codingTest.adapter.MainAdapter
import kr.co.dantalk.codingTest.api.CallApi
import kr.co.dantalk.codingTest.api.response.AlbumData
import kr.co.dantalk.codingTest.databinding.ActivityMainBinding
import kr.co.dantalk.codingTest.ui.dialog.LottieDialog

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val datas: ArrayList<AlbumData> = ArrayList()
    private val loadingDialog = LottieDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        setRv()
        setData()
    }

    private fun setRv() {
        binding.mainRv.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = MainAdapter().apply {
                setDatas(datas)
                clickEvent = { s: String, s1: String, imageView: ImageView ->
                    //Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                    DetailActivity.startActivityWithTransition(this@MainActivity, s, s1, imageView)
                }
            }
        }
    }

    private fun setData() {
        loadingDialog.show(supportFragmentManager, null)
        CallApi().album() { i: Int, s: String, list: List<AlbumData>? ->
            Log.e("CallApi().album", "$i $s ${list?.size}")
            if (i == 200) {
                datas.clear()
                datas.addAll(list?: arrayListOf())
                (binding.mainRv.adapter as MainAdapter).setDatas(datas)
                loadingDialog.dismiss()
            }
        }
    }
}