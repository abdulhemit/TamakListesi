package com.okuuyghur.tamaklistesi

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.okuuyghur.tamaklistesi.Model.Yemek_Model

class YemekHelper(context: Context):SQLiteOpenHelper(context,"Yemek.db",null,1){
    val TABLE_NAME = "yemekler"
    val ALAN_ID = "id"
    val ALAN_NAME = "name"
    val ALAN_TARIF= "tarif"
    val ALAN_IMAGE = "image"
    override fun onCreate(p0: SQLiteDatabase?) {
        val database = " CREATE TABLE IF NOT EXISTS $TABLE_NAME($ALAN_ID INTEGER PRIMARY KEY,$ALAN_NAME TEXT,$ALAN_TARIF TEXT,$ALAN_IMAGE TEXT)"
        p0?.execSQL(database)
    }
    fun yemekler(yemek:Yemek_Model){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ALAN_ID,yemek.id)
        contentValues.put(ALAN_NAME,yemek.name)
        contentValues.put(ALAN_TARIF,yemek.tarif)
        contentValues.put(ALAN_IMAGE,yemek.image)
        db.insert(TABLE_NAME,null,contentValues)
        db.close()
    }

    fun Read_Yemek():ArrayList<Yemek_Model>{
        val tizim = ArrayList<Yemek_Model>()
        val sql_str= " SELECT * FROM $TABLE_NAME "
        val db=this.readableDatabase
        val imlec=db.rawQuery(sql_str,null)
        while (imlec.moveToNext()){
            val id = imlec.getInt(0)
            val name = imlec.getString(1)
            val tarif = imlec.getString(2)
            val image = imlec.getString(3)
            val yemak = Yemek_Model(id,name,tarif,image)
            tizim.add(yemak)
        }
        db.close()
        return tizim
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}