import com.pi4j.wiringpi.Gpio
import kotlinx.coroutines.delay


class Nixie {

    companion object {

        private const val DELAY_SPI = 10L

        private const val DATA = 0
        private const val LATCH = 2
        private const val CLOCK = 3

        private const val B = 0b00000010
        private const val C = 0b00000100
        private const val D = 0b00001000
        private const val E = 0b00010000
        private const val F = 0b00100000
        private const val G = 0b01000000
        private const val H = 0b10000000

        private val DIGITS = arrayOf(
                D or E or C or G or B or H,
                B or C,
                D or B or F or H or G,
                D or B or F or C or G,
                E or B or F or C,
                D or E or F or C or G,
                D or E or F or C or G or H,
                D or B or C,
                D or E or F or C or G or B or H,
                D or E or F or C or G or B
        )
    }

    init {
        Gpio.wiringPiSetup()
        Gpio.pinMode(DATA, Gpio.OUTPUT)
        Gpio.pinMode(LATCH, Gpio.OUTPUT)
        Gpio.pinMode(CLOCK, Gpio.OUTPUT)
        Gpio.digitalWrite(DATA, 0)
        Gpio.digitalWrite(LATCH, 0)
        Gpio.digitalWrite(CLOCK, 0)
    }

    suspend fun off() {
        write(0)
    }

    suspend fun display(digit: Int) {
        val byte = DIGITS.getOrNull(digit) ?: return
        write(byte)
    }

    suspend fun dash() {
        val byte = F
        write(byte)
    }

    private suspend fun write(byte: Int) {
        for (bit in 0 until 8) {
            val output = 0x80 and (byte shl bit)
            Gpio.digitalWrite(DATA, output)
            Gpio.digitalWrite(CLOCK, Gpio.HIGH)
            delay(DELAY_SPI)
            Gpio.digitalWrite(CLOCK, Gpio.LOW)
        }
        Gpio.digitalWrite(LATCH, Gpio.HIGH)
        delay(DELAY_SPI)
        Gpio.digitalWrite(LATCH, Gpio.LOW)
    }
}
