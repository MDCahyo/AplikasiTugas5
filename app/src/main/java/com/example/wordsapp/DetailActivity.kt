/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    // Penggunaan kata kunci companion berarti berkaitan dengan class DetailActivity
    // Menambahkan konstanta baru dengan value URL untuk penelusuran Google
    companion object {

        // Membuat properti untuk konstanta huruf
        // Menambahkan konstanta untuk penelusuran Google dengan URL dasar
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // Ambil objek yang mengikat yang memungkinkan Anda merujuk ke tampilan dengan nama id
        // Nama-nama diubah dari kotak ular menjadi kotak unta.
        // Misalnya, Tampilan dengan id word_one dirujuk sebagai binding.wordOne
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil LETTER dari Intent extras
        // intent.extras.getString mengembalikan String? (String atau null)
        // Memanggil toString() menjamin bahwa nilainya akan berupa String, bukan null

        // Kode untuk mendapatkan letterId yang diteruskan dari intent
        // dan untuk menggunakan konstanta huruf
        val letterId = intent?.extras?.getString(LETTER).toString()

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(letterId, this)

        // Menambahkan [DividerItemDecoration] di antara item
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        title = getString(R.string.detail_prefix) + " " + letterId
    }
}