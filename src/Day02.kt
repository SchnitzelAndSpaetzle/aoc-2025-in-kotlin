import kotlin.math.sqrt

fun main() {

    data class Range(val start: Long, val end: Long)

    fun createRange(input: String): List<Range> {
        val ranges = ArrayList<Range>()
        val delimiter = ','
        val idDelimiter = '-'
        val parts = input.split(delimiter)

        for (i in parts) {
            val parts = i.split(idDelimiter)
            val range = Range(parts[0].toLong(), parts[1].toLong())
            ranges.add(range)
        }
        return ranges

    }

    fun isAllDigitsSameUsingSet(number: Long): Boolean {
        val numberString = number.toString()
        return numberString.toSet().size <= 1
    }

    fun isInvalidId(idNum: Long): Boolean {
        val length = idNum.toString().length
        if (length.mod(2) == 0) {
            val mid = length / 2

            val firstHalf = idNum.toString().substring(0, mid).trim().toInt()
            val secondHalf = idNum.toString().substring(mid, length).trim().toInt()

            if (firstHalf == secondHalf) {
                return true
            }
        }
        return false
    }

    fun isInvalidIdPartialSequence(idNum: Long): Boolean {
        val length = idNum.toString().length

        if (length < 2) {
            return false
        }

        if (isAllDigitsSameUsingSet(idNum)) {
            return true
        }

        val candidates = mutableSetOf<Int>()
        val squareRootLimit = sqrt(length.toDouble()).toInt()

        for (pc in 1..squareRootLimit) {

            val pcChunk = length / pc

            if (pc in 2..<length) {
                candidates.add(pc)
            }

            if (pcChunk in 2..<length) {
                candidates.add(pcChunk)
            }
        }

        if (length == 2) {
            candidates.add(1)
        }

        for (c in candidates) {
            val listOfChunks = idNum.toString().chunked(c)
            if (listOfChunks.distinct().size == 1) {
                return true
            }
        }

        return false
    }

    fun part1(input: List<String>): Long {
        var sumOfInvalidId = 0L;
        val ranges = createRange(input[0])

        for (r in ranges) {
            for (i in r.start..r.end) {
                if (isInvalidId(i)) {
                    sumOfInvalidId += i;
                }
            }
        }
        return sumOfInvalidId
    }

    fun part2(input: List<String>): Long {
        var sumOfInvalidId = 0L;
        val ranges = createRange(input[0])

        for (r in ranges) {
            for (i in r.start..r.end) {
                if (isInvalidIdPartialSequence(i)) {
                    sumOfInvalidId += i;
                }
            }
        }
        return sumOfInvalidId
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
