package kr.co.dantalk.codingTest.api.response

import androidx.annotation.Keep

@Keep
data class BaseResponse(
    var code: Int = 0,
    var message: String = "",
    var data: String? = null
)