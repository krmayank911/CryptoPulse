package `in`.crypto.fake.generator

import java.math.BigDecimal
import kotlin.random.Random

object PriceSimulator {

    fun nextPrice(
        current: BigDecimal,
    ): BigDecimal {

        val delta = Random.nextDouble(-0.001, 0.001)

        return current.multiply(BigDecimal.ONE + delta.toBigDecimal())
    }

}