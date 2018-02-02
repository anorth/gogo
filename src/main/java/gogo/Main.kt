package gogo

import com.pi4j.io.gpio.*

val LED_PIN_IDS = listOf(
    RaspiBcmPin.GPIO_18,
    RaspiBcmPin.GPIO_23,
    RaspiBcmPin.GPIO_24,
    RaspiBcmPin.GPIO_25,
    RaspiBcmPin.GPIO_08,
    RaspiBcmPin.GPIO_07,
    RaspiBcmPin.GPIO_12,
    RaspiBcmPin.GPIO_16,
    RaspiBcmPin.GPIO_20
)

fun main(args: Array<String>) {
  println("Gogo world")

  GpioFactory.setDefaultProvider(RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING))
  val gpio = GpioFactory.getInstance();

  println("Provisioning pins")
  val ledPins = LED_PIN_IDS.map { gpio.provisionDigitalOutputPin(it, PinState.LOW) }
  ledPins.forEach { it.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF) }

  println("Hi")
  ledPins.forEach { it.high() }
  println("Sleep")
  Thread.sleep(1000)
  println("Lo")
  ledPins.forEach { it.low() }
}
