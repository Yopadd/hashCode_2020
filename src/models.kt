data class Book(val id: Int, val score: Int)

data class Library(val id: Int, val recordTime: Int, var books: List<Book>, val scansPerDay: Int)