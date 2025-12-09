fun main() {

    data class Battery(
        val power: Int,
        val index: Int
    )

    fun orderBattery(batteryBank: String): String {
        val split = batteryBank.chunked(1)
        val batteryWithIndex = mutableListOf<Battery>()

        for ((index, value) in split.withIndex()) {
            batteryWithIndex.add(Battery(value.toInt(), index))
        }

        batteryWithIndex.sortByDescending { it.power }

        val startingIndex = batteryWithIndex[0].index
        if (startingIndex != split.lastIndex) {
            val fromBiggest = batteryWithIndex.filter { it.index >= startingIndex }
            val bb = fromBiggest.toList().take(2).sortedBy { it.index }
            return bb.joinToString("") { it.power.toString() }
        } else {
            val bb = batteryWithIndex.toList().take(2).sortedBy { it.index }

            return bb.joinToString("") { it.power.toString() }
        }
    }

    fun removeNDigitsToMax(num: String, n: Int): String {
        var toRemove = n
        val stack = ArrayDeque<Char>()

        for (c in num) {
            while (toRemove > 0 && stack.isNotEmpty() && stack.last() < c) {
                stack.removeLast()
                toRemove--
            }
            stack.addLast(c)
        }

        repeat(toRemove) { stack.removeLast() }

        return stack.joinToString("")
    }


    fun part1(input: List<String>): Int {
        var joltage = 0
        for (b in input) {
            val sorted = orderBattery(b)
            joltage += sorted.toInt()
        }

        return joltage
    }

    fun part2(input: List<String>): Long {

        var joltage = 0L
        for (b in input) {
            val sorted = removeNDigitsToMax(b, b.chunked(1).size - 12)
            joltage += sorted.toLong()
        }

        return joltage
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
