package extensions

fun List<Int>.multiply() = reduce { acc, i -> acc * i }
