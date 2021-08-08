package com.example.chuibbo_android.api

import com.example.chuibbo_android.api.response.ApiResponse
import com.example.chuibbo_android.api.response.ResumePhotoUploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ImageApi {
    // 테스트
    @GET("/api/hello")
    suspend fun hello(): ApiResponse<String>

    // 취업 사진
    @Multipart
    @POST("/api/resume_photo/")
    fun uploadResumePhoto(
        @Part(encoding = "multipart") photo: MultipartBody.Part,
        @PartMap(encoding = "params") data: HashMap<String, RequestBody>
    ): Call<ResumePhotoUploadResponse>

    companion object {
        val instance = ApiGenerator()
            .generate(ImageApi::class.java)
    }
}
