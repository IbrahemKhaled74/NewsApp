package com.example.newsapp.Home

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.newsapp.News.News
import com.example.newsapp.R
import com.example.newsapp.category.Categorie
import com.example.newsapp.category.Category_item
import com.example.newsapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    var category = Categorie()
    lateinit var homeBinding: ActivityHomeBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        initView()
        pushFragment(category)
        category.onCategory = object : Categorie.onCategoryClicked {
            override fun onCategoryClicke(category: Category_item) {
                pushFragment(News.getInstance(category))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initView() {
        setSupportActionBar(homeBinding.toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            homeBinding.drawerLayout,
            homeBinding.toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        homeBinding.drawerLayout.addDrawerListener(toggle)
        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()
        homeBinding.navView.setNavigationItemSelectedListener {
            if (it.itemId == R.id.catagory) {
                pushFragment(category)
                homeBinding.drawerLayout.close()
            }
            return@setNavigationItemSelectedListener true
        }
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_Container, fragment)
            .addToBackStack("")
            .commit()
    }
}