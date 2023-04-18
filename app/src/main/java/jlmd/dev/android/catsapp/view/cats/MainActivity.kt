package jlmd.dev.android.catsapp.view.cats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import jlmd.dev.android.catsapp.databinding.ActivityMainBinding
import jlmd.dev.android.catsapp.view.cats.model.CatItem
import jlmd.dev.android.catsapp.view.cats.model.CatsViewState
import jlmd.dev.android.catsapp.view.cats.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private val adapter = CatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindListeners()
        bindObservers()
    }

    private fun bindObservers() {
        viewModel.catsListViewState.observe(this) { viewState ->
            when(viewState) {
                is CatsViewState.Loading -> showLoading(true)
                is CatsViewState.ShowCats -> showList(viewState.catsList)
                is CatsViewState.Error -> showMessageError()
            }
        }
    }

    private fun bindListeners() {
        binding.recyclerCats.adapter = adapter
    }

    private fun showMessageError(){
        Toast.makeText(this, "Ocurrio un error al consultar las razas", Toast.LENGTH_SHORT).show()
        binding.recyclerCats.visibility = View.GONE
    }

    private fun showLoading(show: Boolean){
        if (show)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }

    private fun showList(catsList: List<CatItem>) {
        binding.recyclerCats.visibility = View.VISIBLE

        binding.recyclerCats.scheduleLayoutAnimation()
        adapter.items = catsList

        showLoading(false)
    }
}