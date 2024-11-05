package com.example.salarycalculator

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val scrollView: ScrollView = findViewById(R.id.scrollView) // Yeni əlavə edilən referens
        Log.d("DEBUG", "ScrollView initialized")
        val editTextIsciAd: EditText = findViewById(R.id.editTextIsciAd)
        val editTextEmekHaqqi: EditText = findViewById(R.id.editTextEmekHaqqi)
        val editTextGelmeyenGunler: EditText = findViewById(R.id.editTextGelmeyenGunler)
        val editTextCekMeblegi: EditText = findViewById(R.id.editTextCekMeblegi)
        val editTextAvansMeblegi: EditText = findViewById(R.id.editTextAvansMeblegi)
        val editTextBonusMeblegi: EditText = findViewById(R.id.editTextBonusMeblegi)
        val buttonCalculate: Button = findViewById(R.id.buttonCalculate)
        val textViewResult: TextView = findViewById(R.id.textViewResult)

        buttonCalculate.setOnClickListener {
            Log.d("DEBUG", "Hesabla düyməsinə basıldı")
            // İstifadəçi tərəfindən daxil edilən dəyərləri oxuyun
            val isciAd = editTextIsciAd.text.toString()
            Log.d("DEBUG", "İşçi adı: $isciAd")
            val emekHaqqi = editTextEmekHaqqi.text.toString().toDoubleOrNull() ?: 0.0
            val gelmeyenGunler = editTextGelmeyenGunler.text.toString().toIntOrNull() ?: 0
            val cekMeblegi = editTextCekMeblegi.text.toString().toDoubleOrNull() ?: 0.0
            val avansMeblegi = editTextAvansMeblegi.text.toString().toDoubleOrNull() ?: 0.0
            val bonusMeblegi = editTextBonusMeblegi.text.toString().toDoubleOrNull() ?: 0.0

            // Hesablamalar
            val gunlukMaas = emekHaqqi / 30
            val gelmeyenGunCixisi = gunlukMaas * gelmeyenGunler
            val umumiTutulanMebleg = gelmeyenGunCixisi + cekMeblegi + avansMeblegi
            val qaliqMebleg = emekHaqqi - umumiTutulanMebleg + bonusMeblegi
            Log.d("DEBUG", "Hesablamalar tamamlandı")
            // Nəticəni göstər
            textViewResult.text = """
                İşçi: $isciAd
             
                Əmək haqqı: $emekHaqqi AZN
                
                Gəlməyən günlərin çıxılması: ${"%.2f".format(gelmeyenGunCixisi)} AZN
                
                Çek məbləğinin çıxılması: ${"%.2f".format(cekMeblegi)} AZN
                
                Verilən avans məbləği: ${"%.2f".format(avansMeblegi)} AZN
                
                Bonusların məbləği: ${"%.2f".format(bonusMeblegi)} AZN
                
                Ümumi tutulan məbləğ: ${"%.2f".format(umumiTutulanMebleg)} AZN
                
                Qalıq işçiyə veriləcək məbləğ: ${"%.2f".format(qaliqMebleg)} AZN
""".trimIndent()
            scrollView.post {
                Log.d("DEBUG", "Aşağı sürüşdürməyə çalışılır - scrollTo metodu ilə")
                scrollView.scrollTo(0, textViewResult.bottom) // Yeni sürüşdürmə metodu
            }
        }


    }

}