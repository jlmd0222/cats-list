package jlmd.dev.android.catsapp.view.cats.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jlmd.dev.android.catsapp.core.usecases.GetCatsUseCase
import jlmd.dev.android.catsapp.common.RequestResult
import jlmd.dev.android.catsapp.core.model.Cat
import jlmd.dev.android.catsapp.view.cats.model.CatItem
import jlmd.dev.android.catsapp.view.cats.model.CatsViewState
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCatsUseCase: GetCatsUseCase
): ViewModel() {

    private val _catsListViewState = MutableLiveData<CatsViewState>()
    val catsListViewState: LiveData<CatsViewState> get() = _catsListViewState

    init {
        checkCatsData()
    }

    private fun checkCatsData() {
        _catsListViewState.value = CatsViewState.Loading

        viewModelScope.launch {
            val cats = getCatsUseCase.invoke()

            if (cats is RequestResult.Error){
                _catsListViewState.value = CatsViewState.Error
            } else {
                if (cats is RequestResult.Success) {
                    val catsList = cats.data.toViewData()
                    _catsListViewState.value = CatsViewState.ShowCats(catsList)
                }
            }
        }
    }

    private fun List<Cat>.toViewData(): List<CatItem> {
        return map {
            CatItem(
                id = it.id,
                breedName = it.breedName,
                origin = it.origin,
                intelligence = it.intelligence,
                imageUrl = it.imageUrl
            )
        }
    }
}