package com.example.data_oken

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.data_oken.adapters.MyRvAdapter
import com.example.data_oken.databinding.ActivityMainBinding
import com.example.data_oken.databinding.ItemDialogBinding
import com.example.data_oken.databinding.ItemRvBinding
import com.example.data_oken.db.MyDbHelper
import com.example.data_oken.models.MyContact

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var myRvAdapter: MyRvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)
        myRvAdapter = MyRvAdapter(myDbHelper.getAllContact())

        binding.apply {
            rv.adapter = myRvAdapter

            btnAdd.setOnClickListener {
                val dialog = AlertDialog.Builder(this@MainActivity).create()
                val itemDialog = ItemDialogBinding.inflate(layoutInflater)
                dialog.setView(itemDialog.root)

                itemDialog.apply {
                    btnSave.setOnClickListener {
                        val myContact = MyContact(
                             edtName.text.toString().trim(),
                             edtNumber.text.toString().trim()
                        )
                        myDbHelper.addContact(myContact)
                        Toast.makeText(this@MainActivity, "Save", Toast.LENGTH_SHORT).show()
                        dialog.cancel()
                        myRvAdapter.list = myDbHelper.getAllContact()
                        myRvAdapter.notifyDataSetChanged()

                    }
                }

                    dialog.show()
                }

            }

        }
    }
