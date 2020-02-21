import java.io.File

data class OutputData(val libraries: List<Library>)

fun writeOutput(data: List<String>, fileName: String) {
  File("./output/${fileName}.txt").writeText(data.joinToString("\n"))
}

fun parseOutPut(outputData: OutputData): List<String> {
  val outputLibraries: Array<String> = outputData.libraries.map {
    "${it.id} ${it.books.size}\n${it.books.map(Book::id).joinToString(" ")}"
  }.toTypedArray()
  return listOf(
    outputData.libraries.size.toString(),
    *outputLibraries
  )
}
