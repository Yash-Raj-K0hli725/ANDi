package com.example.andi.viewModel

import com.example.andi.Utils.RetroInstance
import retrofit2.Response

class repo {
    suspend fun gethtml(): Response<String> {
        val instance = RetroInstance().getInstance().getRspns("cloudsoft.php")
        return instance
    }
}