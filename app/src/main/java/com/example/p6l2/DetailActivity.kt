package com.example.p6l2

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    var DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME
    var NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER
    val myListContact : MutableList<myContact> = ArrayList();

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<Cursor> {
        val MyContentUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val myProjection = arrayOf(DISPLAY_NAME, NUMBER)
        return CursorLoader(this, MyContentUri, myProjection, null, null, DISPLAY_NAME+ " ASC")
    }

    override fun onLoadFinished(p0: Loader<Cursor>, p1: Cursor?) {
        myListContact.clear()
        if (p1!=null) {
            p1.moveToFirst()
            while(!p1.isAfterLast()) {
                myListContact.add(myContact(nama = p1.getString(p1.getColumnIndex(DISPLAY_NAME)),nomorHp = p1.getString(p1.getColumnIndex(NUMBER))))
                p1.moveToNext()
            }
        }

        val contactAdapter = myAdapterRecyView(myListContact)
        myRecyView.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            adapter = contactAdapter
        }
    }

    override fun onLoaderReset(p0: Loader<Cursor>) {
        val contactAdapter = myAdapterRecyView(myListContact)
        myRecyView.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            adapter = contactAdapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        LoaderManager.getInstance(this).initLoader(1, null, this)
    }
}
