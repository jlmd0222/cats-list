package jlmd.dev.android.catsapp.view.cats.model

sealed class CatsViewState {
    object Loading: CatsViewState()

    object Error: CatsViewState()

    data class ShowCats(
        val catsList: List<CatItem>
    ): CatsViewState()
}
