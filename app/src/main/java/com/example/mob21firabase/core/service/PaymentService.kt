package com.example.mob21firabase.core.service

class PrintingService {
    fun printReceipt() {
        println("Here is your Receipt")
    }
}

interface PaymentMethod {
    fun makePayment(amount: Float)
}

class CardPayment: PaymentMethod {
    override fun makePayment(amount: Float) {
        println("Payment $amount RM")
        println("Card payment is done")
    }
}

class CashPayment: PaymentMethod {
    override fun makePayment(amount: Float) {
        println("Payment $amount RM")
        println("Cash payment is done")
    }
}

class PaymentService() {
    fun pay(paymentMethod: PaymentMethod, amount: Float) {
        paymentMethod.makePayment(amount)
        PrintingService().printReceipt()
    }
}

fun main(args: Array<String>) {
    val paymentService = PaymentService()
    val cashPayment = CashPayment()
    paymentService.pay(cashPayment, 100f)
}