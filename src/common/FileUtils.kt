package common

import java.io.File

fun readFileAsStringList(filename: String): List<String> = File(filename).useLines { it.toList() }

fun readFileAsIntList(filename: String): List<Int> = File(filename).useLines { lines ->
    val arrayList = arrayListOf<Int>()
    lines.forEach { line -> arrayList.add(line.toInt()) }
    arrayList.toList()
}
