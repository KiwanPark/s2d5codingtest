package kr.co.dantalk.codingTest.api.response

import androidx.annotation.Keep

@Keep
data class AlbumItemResponse(
    val code: Int,
    val data: AlbumDetailData,
    val message: String
)