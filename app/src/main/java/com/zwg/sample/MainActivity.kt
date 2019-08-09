package com.zwg.sample


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list?.setOnClickListener(this)
        array?.setOnClickListener(this)
        arrayList?.setOnClickListener(this)
        recycleList?.setOnClickListener(this)
        recycleArray?.setOnClickListener(this)
        recycleArrayList?.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        var bundle = Bundle()
        var intent = Intent()
        intent.setClass(this, ListActivity::class.java)
        when (v?.id) {
            R.id.list -> {
                bundle.putString("type", "list")
            }
            R.id.array -> {
                bundle.putString("type", "array")
            }
            R.id.arrayList -> {
                bundle.putString("type", "arrayList")
            }
            R.id.recycleList -> {
                bundle.putString("type", "recycleList")
            }
            R.id.recycleArray -> {
                bundle.putString("type", "recycleArray")
            }
            R.id.recycleArrayList -> {
                bundle.putString("type", "recycleArrayList")
            }
        }
        intent.putExtra("data", bundle)
        startActivity(intent)
    }
}
