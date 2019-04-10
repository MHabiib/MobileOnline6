package com.example.p6l2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_recy_view.view.*

class myAdapterRecyView(private  val contact : List<myContact>): RecyclerView.Adapter<myHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myHolder {
        return myHolder(LayoutInflater.from(p0.context)
            .inflate(R.layout.layout_recy_view,p0,false))
    }

    override fun getItemCount(): Int  = contact.size
    override fun onBindViewHolder(p0: myHolder, p1: Int) {
        p0.bindContact(contact[p1])
    }
}


class myHolder(view: View):RecyclerView.ViewHolder(view) {
    private  val contactName = view.itemName
    private  val contactNumber = view.itemNumber

    fun bindContact(tmp: myContact) {
        contactName.text = "${contactName.text} ${tmp.nama}"
        contactNumber.text = "${contactNumber.text} ${tmp.nomorHp}"
    }
}