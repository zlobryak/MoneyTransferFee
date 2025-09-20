package ru.netology

fun main() {
    val amount = 1_000.0
//    val amount = 15_000.0
//    val amount = 75_000.0
//    val cardType = "Mastercard"
    val cardType = "Visa"
//    val cardType = "Mir"
    val amountDay = 65_000.0 //Переводы за день
    val amountMonth = 75_000.0 //Переводы за месяц

    moneyTransfer(amount, cardType, amountDay, amountMonth)
}

fun moneyTransfer(amount: Double, cardType: String, amountDay: Double = 0.0, amountMonth: Double = 0.0) {
    //Значения для карт Visa
    val visaFee = 0.0075
    val visaMinFee = 35.0
//    val visaNoFeeLimit = 0.0 (не используется по условию задачи
    //Значения для карт Mastercard
    val mastercardFee = 0.006
    val mastercardMinFee = 20.0
    val mastercardNoFeeLimit = 75_000.0
    //Значения для карт Mir (не использую, т.к. по условию задачи комиссия не взимается за переводы с карты мир
//    val mirFee = 0.0
//    val mirMinFee = 0.0
//    val mirNoFeeLimit = 0.0
    //Дневной и месячный лимиты
    val dayLimit = 150_000.0
    val monthLimit = 600_000.0
    //Сначала проверим, не превышен ли один из лимитов

    when ((amount + amountDay) > dayLimit || amount + amountMonth > monthLimit) {
        true -> println("Превышен лимит переводов")
        //
        else -> when (cardType) {
            "Mastercard" -> {
                val monthAmountCalc = amountMonth + amount
                val fee = when (monthAmountCalc < mastercardNoFeeLimit) {
                    true -> 0.0
                    false -> (monthAmountCalc - mastercardNoFeeLimit) * mastercardFee + mastercardMinFee
                }
                println("Переведено $amount руб. Комиссия составит ${String.format("%.2f", fee)} руб.")
            }

            "Visa" -> {
                val fee = amount * visaFee
                println(
                    "Переведено $amount  Комиссия составит ${
                        String.format("%.2f", maxOf(visaMinFee, fee))
                    } руб."
                )
            }

            "Mir" -> println(
                "Переведено $amount Комиссия составит 0 руб ."

            )
        }
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