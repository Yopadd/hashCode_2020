fun main() {
    listOf(
        "a_example",
        "b_read_on",
        "c_incunabula",
//        "d_tough_choices",
        "e_so_many_books",
        "f_libraries_of_the_world"
    )
        .parallelStream().forEach {
            it
                .run(::readInput)
                .also { _ -> println("start $it") }
                .run(::parseInput)
                .run(::resolve)
                .run(::parseOutPut)
                .run { writeOutput(this, it) }
                .also { _ -> println("end $it") }
        }
}