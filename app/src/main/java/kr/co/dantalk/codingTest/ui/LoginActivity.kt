package kr.co.dantalk.codingTest.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kr.co.dantalk.codingTest.R
import kr.co.dantalk.codingTest.api.CallApi
import kr.co.dantalk.codingTest.databinding.ActivityLoginBinding
import kr.co.dantalk.codingTest.ui.dialog.LottieDialog

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loadingDialog = LottieDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.apply {
            activity = this@LoginActivity
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
            binding.loginBTN -> {
                loadingDialog.show(supportFragmentManager, null)
                CallApi().signIn(binding.emailET.text.toString(), binding.passwordET.text.toString()){ i: Int, s: String, s1: String? ->
                    Log.e("signIn", "$i $s $s1")
                    loadingDialog.dismiss()
                    if(i==200) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
            }
            binding.signupPostfix -> {
                startActivity(Intent(this, JoinActivity::class.java))
                finish()
            }
        }
    }
}