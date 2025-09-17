package ru.netology

fun main() {
    val amount = 150_000.0
    val cardType = "Mastercard"
    val amountDay = 0.0
    val amountMonth = 0.0

    moneyTransfer(amount, cardType, amountDay, amountMonth)
}

fun moneyTransfer(amount: Double, cardType: String, amountDay: Double, amountMonth: Double) {
    //Значения для карт Visa
    val visaFee = 100 - 0.75
    val visaMinFee = 35.0
    val visaNoFeeLimit = 0.0
    //Значения для карт Mastercard
    val mastercardFee = 100 - 0.6
    val mastercardMinFee = 20.0
    val mastercardNoFeeLimit = 75_000.0
    //Значения для карт Mir
    val mirFee = 100 - 0.0
    val mirMinFee = 0.0
    val mirNoFeeLimit = 0.0
    //Дневной и месячный лимиты
    val dayLimit = 150_000.0
    val monthLimit = 600_000

    when ((amount + amountDay) > dayLimit || amountDay + amountMonth > monthLimit) {
        true -> println("Превышен лимит переводов")

        else -> when (cardType) {
            "Mastercard" -> println(
                "Переведено $amount руб. " +
                        "комиссия составит ${
                            feeCalculator(
                                amountMonth - amount,
                                mastercardFee,
                                mastercardMinFee,
                                mastercardNoFeeLimit
                            )
                        } руб."
            )

            "Visa" -> println(
                "Переведено $amount  " +
                        "комиссия составит ${
                            feeCalculator(
                                amountMonth - amount,
                                visaFee,
                                visaMinFee,
                                visaNoFeeLimit
                            )
                        } руб."
            )

            "Mir" -> println(
                "Переведено $amount " +
                        "комиссия составит ${
                            feeCalculator(
                                amountMonth - amount,
                                mirFee,
                                mirMinFee,
                                mirNoFeeLimit
                            )
                        } руб."
            )
        }
    }
}

fun feeCalculator(
    amount: Double,
    fee: Double,
    minFee: Double,
    noFeeLimit: Double,
): Double {

    var result = amount * fee

    return when (result < minFee) {
        true -> minFee
        else -> result
    }
}

/*
За переводы с карты Mastercard комиссия не взимается,
пока не превышен месячный лимит в 75 000 руб.
Если лимит превышен, комиссия составит 0,6% + 20 руб.

За переводы с карты Visa комиссия составит 0,75%, минимальная сумма комиссии 35 руб.

За переводы с карты Мир комиссия не взимается.

150 000 руб. в сутки
600 000 руб. в месяц
 */