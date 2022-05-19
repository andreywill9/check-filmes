package dev.andrey.checkfilmes.domain

class ListaFilmes {
    private val filmes = mutableListOf<Filme>()

    val ultimoId get() = filmes.map(Filme::id).maxOrNull()

    val todosFilmes get() = filmes.toList()

    fun adicionarFilmes(filmes: List<Filme>) {
        this.filmes.addAll(filmes)
    }

    fun adicionarFilme(filme: Filme) {
        filmes.add(filme)
    }

    fun limparLista() {
        filmes.clear()
    }

    fun obterFilmePorIndice(indice: Int): Filme {
        return filmes[indice]
    }

    fun obterFimePorId(id: Long): Filme? {
        return filmes.firstOrNull { filme -> filme.id == id }
    }

    fun atualizarFilme(filme: Filme) {
        filmes[filmes.indexOfFirst { filmeLista -> filmeLista.id == filme.id }] = filme
    }
}