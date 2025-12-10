fun main() {

    fun create2DList(input: List<String>): List<List<Char>> {
        return input.map { it.toList() }
    }

    fun getNeighboursFrom2DList(grid: List<List<Char>>, column: Int, row: Int): List<Char> {
        if (grid.isEmpty()) {
            return emptyList()
        }

        val directions = listOf(
            Pair(-1, -1), Pair(-1, 0), Pair(-1, 1),
            Pair(0, -1), Pair(0, 1),
            Pair(1, -1), Pair(1, 0), Pair(1, 1)
        )


        return directions.mapNotNull { (dirRow, dirCol) ->
            val nr = row + dirRow
            val nc = column + dirCol

            grid.getOrNull(nr)?.getOrNull(nc)
        }
    }

    fun cleanGrid(grid: MutableList<MutableList<Char>>, locationsForCleanup: MutableList<List<Int>>) {
        for ((row, col) in locationsForCleanup) {
            if (row in grid.indices && col in grid[row].indices) {
                if (grid[row][col] == '@') {
                    grid[row][col] = '.'
                }
            }
        }
    }

    fun movePaperRecursive(grid: MutableList<MutableList<Char>>, movedRolls: Int): Int {
        var canAccess = 0
        val locationsForCleanup: MutableList<List<Int>> = mutableListOf();
        for ((i, row) in grid.withIndex()) {
            for ((j, col) in row.withIndex()) {
                if (col == '@') {
                    val neighbours = getNeighboursFrom2DList(grid, j, i).filter { it == '@' }
                    if (neighbours.size < 4) {
                        canAccess++
                        val forCleanup = listOf(i, j)
                        locationsForCleanup.add(forCleanup)
                    }
                }

            }
        }

        return if (canAccess != 0) {
            cleanGrid(grid, locationsForCleanup)
            movePaperRecursive(grid, canAccess + movedRolls)
        } else {
            movedRolls
        }
    }


    fun part1(input: List<String>): Int {
        var canAccess = 0
        val grid = create2DList(input);
        for ((i, row) in grid.withIndex()) {
            for ((j, col) in row.withIndex()) {
                if (col == '@') {
                    val neighbours = getNeighboursFrom2DList(grid, j, i).filter { it == '@' }
                    if (neighbours.size < 4) {
                        canAccess++
                    }
                }
            }
        }
        return canAccess
    }

    fun part2(input: List<String>): Int {
        val grid = create2DList(input);
        val mutableGrid: MutableList<MutableList<Char>> =
            grid.map { it.toMutableList() }.toMutableList()
        return movePaperRecursive(mutableGrid, 0)
    }


    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
