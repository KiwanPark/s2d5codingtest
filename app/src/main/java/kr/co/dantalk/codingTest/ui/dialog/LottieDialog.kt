package kr.co.dantalk.codingTest.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kr.co.dantalk.codingTest.R
import kr.co.dantalk.codingTest.databinding.DialogLottieBinding

class LottieDialog (
) : DialogFragment() {
    private lateinit var binding: DialogLottieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogLottieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.LoadingDialog)
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLottie()
    }

    private fun showLottie() {
        binding.loadingLAV.visibility = View.VISIBLE
        binding.loadingLAV.setAnimation(R.raw.loading)
        binding.loadingLAV.playAnimation()
        binding.loadingLAV.repeatCount = Integer.MAX_VALUE
    }
}