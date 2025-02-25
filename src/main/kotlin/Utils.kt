import kotlin.random.Random
fun <T> List<T>.randomWeighted(weights: List<Double>): T {
    val cumulativeWeights = weights.scan(0.0) { acc, weight -> acc + weight }
    val randomValue = Random.nextDouble() * cumulativeWeights.last()
    return this[cumulativeWeights.indexOfFirst { it > randomValue } - 1]
}

fun pausa(milisegundos: Long) {
    try {
        Thread.sleep(milisegundos)
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
}