package com.example.turkcellcustomtablab

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.turkcellcustomtablab.databinding.ActivityMainBinding
import com.example.turkcellcustomtablab.databinding.CustomTabBinding
import com.google.android.material.tabs.TabLayout
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding
    lateinit var secondBinding: CustomTabBinding
    lateinit var tLayout: TabLayout
    lateinit var vPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        secondBinding = CustomTabBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.root.addView(secondBinding.root)

        tLayout = mainBinding.tabLayout
        vPager = mainBinding.viewPager

        viewPagerOlustur()
        tLayout.setupWithViewPager(vPager)

        tLayout.addTab(tLayout.newTab())
        tLayout.addTab(tLayout.newTab())

        tabOlustur()

    }

    private fun tabOlustur() {
        val tab1 = LayoutInflater.from(this).inflate(R.layout.custom_tab,null)
        val tab1Baslik = tab1.findViewById<TextView>(R.id.tvBaslik)
        val tab1Foto = tab1.findViewById<ImageView>(R.id.tvFoto)
        tab1Baslik.text = "Tab1"
        tab1Foto.setImageResource(R.mipmap.calendar)
        tLayout.getTabAt(0)?.setCustomView(tab1)

        val tab2 = LayoutInflater.from(this).inflate(R.layout.custom_tab,null)
        val tab2Baslik = tab2.findViewById<TextView>(R.id.tvBaslik)
        val tab2Foto = tab2.findViewById<ImageView>(R.id.tvFoto)
        tab2Baslik.text = "Tab2"
        tab2Foto.setImageResource(R.mipmap.calendar)
        tLayout.getTabAt(1)?.setCustomView(tab2)
    }

    private fun viewPagerOlustur() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.fragmentAdd(Fragment1())
        adapter.fragmentAdd(Fragment2())
        vPager.adapter = adapter

    }

    internal class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager){

        private val fragmentList: MutableList<Fragment> = ArrayList()

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        fun fragmentAdd(fragment: Fragment){
            fragmentList.add(fragment)
        }
    }

    override fun onBackPressed() {
        var uyari = AlertDialog.Builder(this)
        uyari.setTitle("Çıkış")
        uyari.setMessage("Çıkış Yapılacaktır Emin Misiniz ?")
        uyari.setPositiveButton("Evet",DialogInterface.OnClickListener { dialogInterface, i ->
            super.onBackPressed()
        })
        uyari.setNegativeButton("Hayır",null)

        uyari.create().show()
    }
}