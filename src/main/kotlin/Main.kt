package ru.netology

fun main() {
    val amount = 140_234.0
    val cardType = "Mastercard"
    val amountDay = 0.0 //Переводы за день
    val amountMonth = 0.0 //Переводы за месяц

    moneyTransfer(amount, cardType, amountDay, amountMonth)
}

fun moneyTransfer(amount: Double, cardType: String, amountDay: Double, amountMonth: Double) {
    //Значения для карт Visa
    val visaFee = 0.0075
    val visaMinFee = 35.0
    val visaNoFeeLimit = 0.0
    //Значения для карт Mastercard
    val mastercardFee = 0.006
    val mastercardMinFee = 20.0
    val mastercardNoFeeLimit = 75_000.0
    //Значения для карт Mir
    val mirFee = 0.0
    val mirMinFee = 0.0
    val mirNoFeeLimit = 0.0
    //Дневной и месячный лимиты
    val dayLimit = 150_000.0
    val monthLimit = 600_000.0
    //Сначала проверим, не превышен ли один из лимитов
    when ((amount + amountDay) > dayLimit || amount + amountMonth > monthLimit) {
        true -> println("Превышен лимит переводов")
        //Если лимиты позволяют, вызовем функцию считающую комиссию
        else -> when (cardType) {
            "Mastercard" -> println(
                "Переведено $amount руб. Комиссия составит ${
                    String.format(
                        "%.2f",
                        feeCalculator(
                            amount,
                            mastercardFee,
                            mastercardMinFee,
                            mastercardNoFeeLimit,
                            amountMonth
                        )
                    )
                } руб."

            )

            "Visa" -> println(
                "Переведено $amount  Комиссия составит ${
                    String.format(
                        "%.2f",
                        feeCalculator(
                            amount,
                            visaFee,
                            visaMinFee,
                            visaNoFeeLimit,
                            amountMonth
                        )
                    )
                } руб."

            )

            "Mir" -> println(
                "Переведено $amount Комиссия составит ${
                    String.format(
                        "%.2f",
                        feeCalculator(
                            amount,
                            mirFee,
                            mirMinFee,
                            mirNoFeeLimit,
                            amountMonth
                        )
                    )
                } руб ."

            )

        }
    }
}

fun feeCalculator(
    amount: Double, //Сумма перевода
    fee: Double, //Процент комиссии
    minFee: Double, //Минимальная комиссия
    noFeeLimit: Double,
    amountMonth: Double, //Месячный лимит на переводы
): Double {
    //Первым делом проверим не превышен ли месячный лимит на переводы
    //и вычислим от какой суммы нужно вычислить комиссию
    val overLimit = maxOf(0.0, amountMonth + amount - noFeeLimit)
    return when (overLimit != 0.0) {
        true -> maxOf(minFee, overLimit * fee + minFee)
        else -> 0.0

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