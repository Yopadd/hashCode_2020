import kotlin.math.min

fun resolve(inputData: InputData): OutputData {
    var remainingTime = inputData.metadata.nbDays
    val scannedBooks = mutableSetOf<Book>()
    val savedLibraries = mutableListOf<Library>()
    val libraries = inputData.libraries.toMutableList()

    fun selectLibrary(libraries: MutableList<Library>): Library? = libraries
        .sortedByDescending{ it.score(remainingTime) }
        .find { it.recordTime < remainingTime }

    fun saveLibrary(library: Library) {
        libraries.remove(library)
        savedLibraries.add(library)
        remainingTime -= library.recordTime
    }

    fun scanBooks(library: Library) {
        scannedBooks.addAll(library.books)
        libraries.forEach {
            it.books = it.books.filter { book -> !scannedBooks.contains(book) }
        }
    }

    var library = selectLibrary(libraries)
    while (library != null) {
        saveLibrary(library)
        scanBooks(library)
        library = selectLibrary(libraries)
    }
  
    return OutputData(savedLibraries)
}

private fun Library.score(remainingTime: Int) =
    books.slice(IntRange(0, min(remainingTime * scansPerDay, books.size - 1))).sumBy { it.score } /this.recordTime