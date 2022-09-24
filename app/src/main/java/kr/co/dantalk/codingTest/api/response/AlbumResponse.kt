package kr.co.dantalk.codingTest.api.response

import androidx.annotation.Keep

@Keep
data class AlbumResponse(
    val code: Int,
    val data: List<AlbumData>,
    val message: String
)