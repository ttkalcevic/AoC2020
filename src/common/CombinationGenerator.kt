package common

class CombinationGenerator<T>(private val items: List<T>, choose: Int = 1)
    : Iterator<List<T>>, Iterable<List<T>> {

    private val indices = Array(choose) { it }
    private var first = true

    override fun iterator(): Iterator<List<T>> = this

    override fun hasNext(): Boolean = indices.filterIndexed { index, it ->
        when (index) {
            indices.lastIndex -> items.lastIndex > it
            else -> indices[index + 1] - 1 > it
        }
    }.any()

    override fun next(): List<T> {
        if (!first) {
            incrementAndCarry()
        } else
            first = false
        return List(indices.size) { items[indices[it]] }
    }

    private fun incrementAndCarry() {
        var carry: Boolean
        var place = indices.lastIndex
        do {
            carry = if ((isLastPlace(place) && indices[place] < items.lastIndex)
                    || (isNotLastPlace(place) && indices[place] < indices[place + 1] - 1)) {
                indices[place]++
                (place + 1..indices.lastIndex).forEachIndexed { index, i ->
                    indices[i] = indices[place] + index + 1
                }
                false
            } else
                true
            place--
        } while (carry && place > -1)
    }

    private fun isLastPlace(place: Int): Boolean = place == indices.lastIndex

    private fun isNotLastPlace(place: Int) = !isLastPlace(place)
}
