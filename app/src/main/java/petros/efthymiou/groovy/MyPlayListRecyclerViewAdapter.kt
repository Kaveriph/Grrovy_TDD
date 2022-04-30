package petros.efthymiou.groovy

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import petros.efthymiou.groovy.databinding.PlaylistItemBinding
import petros.efthymiou.groovy.placeholder.PlayList

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyPlayListRecyclerViewAdapter(
    private val values: List<PlayList>
) : RecyclerView.Adapter<MyPlayListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            PlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.playListName.text = item.name
        holder.playListCategory.text = item.category
        holder.playListImage.setImageResource(item.image)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: PlaylistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val playListName: TextView = binding.playlistName
        val playListCategory: TextView = binding.playlistCategory
        val playListImage: ImageView = binding.playlistImage

        override fun toString(): String {
            return super.toString() + " '"
        }
    }

}