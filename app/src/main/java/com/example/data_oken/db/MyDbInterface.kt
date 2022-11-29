package com.example.data_oken.db

import com.example.data_oken.models.MyContact

interface MyDbInterface {
    fun addContact(myContact: MyContact)
    fun getAllContact():List<MyContact>

}