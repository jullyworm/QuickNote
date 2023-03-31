package com.example.quicknote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}

class Texts {
    companion object {
        val texts: MutableList<NoteData> = mutableListOf(
            NoteData(0, "ЩУКа съела ацетат, получается цитрат."),
            NoteData(1, "Группа мятежных генералов пыталась добиться легализации K-pop в КНДР"),
            NoteData(2,"Экватор находится в России: РАН разоблачила очередной заговор западных учёных"),
            NoteData(3, "Мао Цзэдун перед смертью принял православие"),
            NoteData(4,"«Истребляет людей без ущерба природе»: Грета Тунберг поддержала боевое применение обеднённого урана"),
        )
    }
    fun addValue(string: String) {
        texts.add(NoteData(texts.size,string))
    }
}