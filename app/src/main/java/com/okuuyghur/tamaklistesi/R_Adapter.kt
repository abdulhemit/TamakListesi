package com.okuuyghur.tamaklistesi


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.okuuyghur.tamaklistesi.Model.Yemek_Model
import com.okuuyghur.tamaklistesi.databinding.RowYemekBinding

class R_Adapter(val yemekList : ArrayList<Yemek_Model>):RecyclerView.Adapter<R_Adapter.YemekHolder>() {

    inner class YemekHolder(val RowBinding :RowYemekBinding):RecyclerView.ViewHolder(RowBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekHolder {
        val itemview = RowYemekBinding.inflate(LayoutInflater.from(parent.context))
       return YemekHolder(itemview)
    }

    override fun onBindViewHolder(holder: YemekHolder, position: Int) {
        val yemek_Name = yemekList.get(position)
        holder.RowBinding.TextYemek.setText(yemek_Name.name)
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
           // bundle.putStringArrayList("yemek",)
            findNavController(it).navigate(R.id.tarifFragment,bundle)

        }
    }

    override fun getItemCount(): Int {
        return yemekList.size
    }
}