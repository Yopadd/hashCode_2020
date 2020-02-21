import java.io.File

data class InputData(val libraries: List<Library>, val metadata: Metadata) {
    data class Metadata(val nbBooks: Int, val nbLibrary: Int, val nbDays: Int)
}

fun readInput(fileName: String): List<String> = File("./input/${fileName}.txt").readLines()

fun parseInput(input: List<String>): InputData {
    val metadata = input[0].splitBySpaceMapToInt().let { InputData.Metadata(it[0], it[1], it[2]) }
    val books = input[1].splitBySpaceMapToInt().mapIndexed(::Book)

    val libraries = input
        .takeLast(input.size - 2)
        .run {
            val evenLine = this.filterIndexed { index, _ -> index.isEven() }
            val oddLine = this.filterIndexed { index, _ -> index.isOdd() }
            oddLine.zip(evenLine)
        }
        .mapIndexed { id, (first, second) ->
            val (_, recordTime, scanByDays) = first.splitBySpaceMapToInt()
            val booksForLibrary = second.splitBySpaceMapToInt().map(books::get).sortedByDescending(Book::score)
            Library(id, recordTime, booksForLibrary, scanByDays)
        }
    return InputData(libraries, metadata)
}

private fun String.splitBySpaceMapToInt(): List<Int> = split(" ").map(String::toInt)

private fun Int.isEven(): Boolean = this % 2 != 0
private fun Int.isOdd(): Boolean = this % 2 == 0