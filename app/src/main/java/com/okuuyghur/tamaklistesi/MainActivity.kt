package com.okuuyghur.tamaklistesi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuinflater= menuInflater
        menuinflater.inflate(R.menu.row_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_item_1){
            findNavController(R.id.fragmentContainerView).navigate(R.id.tarifFragment   )
        }
        return super.onOptionsItemSelected(item)

    }
}