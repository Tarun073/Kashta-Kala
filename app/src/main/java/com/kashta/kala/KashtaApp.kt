package com.kashta.kala

import android.app.Application
import com.kashta.kala.data.local.KashtaDatabase
import com.kashta.kala.data.repository.KashtaRepository
import com.kashta.kala.utils.LanguageHelper

class KashtaApp : Application() {

    val database by lazy { KashtaDatabase.getDatabase(this) }
    val repository by lazy { KashtaRepository(database) }

    override fun onCreate() {
        super.onCreate()
        val lang = LanguageHelper.getLanguage(this)
        LanguageHelper.applyLanguage(this, lang)
    }
}