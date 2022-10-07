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
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.ActivityMainBinding

/**
 * Aktivitas Utama dan titik masuk untuk aplikasi. Menampilkan RecyclerView huruf.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    // Membuat properti untuk melacak status tata letak tempat aplikasi berada
    // dan menetapkan nilai default ke true
    private var isLinearLayoutManager = true

    // Perintah untuk mengatur tata letak item secara linear atau grid
    // Adaptor LetterAdaptor() digunakan untuk tata letak daftar dan petak
    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }
        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        // Atur drawable untuk ikon menu berdasarkan LayoutManager yang sedang digunakan

        // Ikon akan ditetapkan dengan syarat berdasarkan properti isLinearLayoutManager
        // Jika (isLinearLayoutManager) --> akan menampilkan tata letak petak
        //     menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
        // Jika tidak, maka akan menampilkan tata letak daftar
        //     menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
    }

    // onCreateOptionsMenu() adalah tempat untuk meng-inflate menu opsi
    // dan melakukan penyiapan tambahan
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        // Memanggil kode untuk menyetel ikon berdasarkan LinearLayoutManager dari RecyclerView
        setIcon(layoutButton)

        return true
    }

    // onOptionsItemSelected() adalah tempat untuk memanggil chooseLayout() saat tombol dipilih
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                // Setel isLinearLayoutManager (sebuah Boolean) ke nilai yang berlawanan
                isLinearLayoutManager = !isLinearLayoutManager
                // Mengatur tata letak dan ikon
                chooseLayout()
                setIcon(item)

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    // Panggil metode baru karena pengelola tata letak
    // dan adaptor sekarang ditetapkan dalam chooseLayout()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        // Set LinearLayoutManager dari recyclerview
        chooseLayout()
    }

}
