package com.example.cgr.films

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cgr.AddFilmToPanierActivity
import com.example.cgr.R

class FilmsAdapter(private val context: Context, private val films: ArrayList<Film>): RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {

    private var onClickListener: View.OnClickListener? = null

    class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val textViewTitleFilm = view.findViewById<TextView>(R.id.textViewTitleFilm)
        val imageViewBackdropUrl = view.findViewById<ImageView>(R.id.imageViewBackdropUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_films, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return films.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film=this.films.get(position)
        holder.textViewTitleFilm.text=film.title
        Glide.with(holder.imageViewBackdropUrl.context).load(film.backdropUrl).into(holder.imageViewBackdropUrl);

        holder.itemView.setOnClickListener {
            val intent = Intent(context, AddFilmToPanierActivity::class.java)
            intent.putExtra("titleFilm", film.title)
            intent.putExtra("description", film.description)
            intent.putExtra("runTime", film.runTime)
            intent.putExtra("graphicUrl", film.graphicUrl)
            context.startActivity(intent)
        }
    }

    fun setOnItemClickListener(listener: View.OnClickListener) {
        onClickListener = listener
    }
}