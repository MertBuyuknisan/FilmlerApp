package com.mertbuyuknisan.filmlerapp

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mertbuyuknisan.filmlerapp.databinding.CardTasarimBinding
import com.mertbuyuknisan.filmlerapp.databinding.FragmentAnasayfaBinding

class FilmlerAdapter (var mContext: Context,var filmlerListesi:List<Filmler>): RecyclerView.Adapter<FilmlerAdapter.cardTasarimTutucu>(){


    //context bulundugu sayfayı temsil eder
    //card tasarımı temsil eden inner class olustur.
    inner class cardTasarimTutucu(var tasarim: CardTasarimBinding): RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardTasarimTutucu {//kart tasarım üzerindeki görsel nesnelere erişiyoruz burada binding yapıyoruz.
         var binding= CardTasarimBinding.inflate(LayoutInflater.from((mContext)), parent, false)
        return cardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: cardTasarimTutucu, position: Int) {
        val film=filmlerListesi.get(position)//kac film var 0-1-2-3 sırayla film nesnesi verir döngü gibi calısır
        val t=holder.tasarim
        t.textViewFiyat.text="${film.fiyat}₺ "
        /*t.imageViewFilm.setImageResource(
            mContext.resources.getIdentifier(film.resim,"drawable",mContext.packageName)
        )*/
        t.imageViewFilm.setImageResource(mContext.resources.getIdentifier(film.resim,"drawable",mContext.packageName))
        t.buttonSepet.setOnClickListener {
            Snackbar.make(it,"${film.ad} sepete eklendi", Snackbar.LENGTH_SHORT).show()
        }
        t.cardViewFilm.setOnClickListener {
            val gecis= AnasayfaFragmentDirections.detayGecis(filmNesnesi = film)
            Navigation.findNavController(it).navigate(gecis) //o anda secilen film nesnesini gecis yaptık bunu adapterda yapıyoruz cünkü burayla alakalıç
        }
    }

    override fun getItemCount(): Int {
        return filmlerListesi.size
    }

}