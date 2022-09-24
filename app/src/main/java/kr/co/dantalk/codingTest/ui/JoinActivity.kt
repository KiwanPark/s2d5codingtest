package kr.co.dantalk.codingTest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kr.co.dantalk.codingTest.R
import kr.co.dantalk.codingTest.api.CallApi
import kr.co.dantalk.codingTest.databinding.ActivityJoinBinding
import kr.co.dantalk.codingTest.ui.dialog.LottieDialog

class JoinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJoinBinding
    private val loadingDialog = LottieDialog()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityJoinBinding.inflate(layoutInflater)
        binding.apply {
            activity = this@JoinActivity
        }
        setContentView(binding.root)
        setTextWatcher()
    }

    private fun setTextWatcher() {
        binding.emailET.addTextChangedListener {
            val id = it.toString()
            if (id.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(id).matches()) {
                binding.emailET.error = getString(R.string.message_email_input_error)
            }
        }

        binding.passwordET.addTextChangedListener {
            val password = it.toString()
            if (password.isBlank() || password.length < 8) {
                binding.passwordET.setError(getString(R.string.message_password_input_error), null)
            }
        }
    }

    fun onClick(v: View) {
        when (v) {
            binding.signupBTN -> {
                loadingDialog.show(supportFragmentManager, null)
                CallApi().signUp(binding.emailET.text.toString(), binding.passwordET.text.toString()){ i: Int, s: String, s1: String? ->
                    Log.e("signUp", "$i $s $s1")
                    loadingDialog.dismiss()
                    if(i==200) {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                }
            }
            binding.signinPostfix -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}