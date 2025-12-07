import kotlin.math.floor

fun main() {

    data class DirectionAmount(
        val dir: Direction,
        val amount: Int
    )

    fun separateDirectionAndAmount(i: String): DirectionAmount {
        val dir = when (i.first()) {
            'L' -> Direction.L
            'R' -> Direction.R
            else -> error("Invalid direction: $i")
        }

        val amount = i.drop(1).toInt()

        return DirectionAmount(dir, amount)
    }

    fun noOfRotations(currPoint: Int, d: DirectionAmount): Int {
        return if (d.dir == Direction.R) {
            val total = currPoint + d.amount
            total / 100
        } else {
            val mirror = (100 - currPoint).mod(100)
            val total = mirror + d.amount
            total / 100
        }
    }

    fun part1(input: List<String>): Int {

        var password = 0
        var currentPoint = 50

        for(i in input) {
            val actionToTake = separateDirectionAndAmount(i)

            currentPoint = if (actionToTake.dir == Direction.L) {
                (currentPoint - actionToTake.amount) % 100
            } else {
                (currentPoint + actionToTake.amount) % 100
            }

            if(currentPoint == 0) {
                password++
            }
        }
        return password
    }

    fun part2(input: List<String>): Int {

        var password = 0
        var currentPoint = 50

        for(i in input) {
            val actionToTake = separateDirectionAndAmount(i)

            password += noOfRotations(currentPoint, actionToTake, )

            currentPoint = if (actionToTake.dir == Direction.L) {
                (currentPoint - actionToTake.amount).mod(100)
            } else {
                (currentPoint + actionToTake.amount).mod(100)
            }
        }
        return password
    }


    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
