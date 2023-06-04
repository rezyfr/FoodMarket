package com.rezyfr.foodmarket.domain.order.usecase

import com.rezyfr.foodmarket.core.domain.UseCase
import com.rezyfr.foodmarket.core.domain.model.ViewError
import com.rezyfr.foodmarket.core.domain.utils.Either
import com.rezyfr.foodmarket.domain.order.model.PaymentDetails
import com.rezyfr.foodmarket.domain.order.model.PaymentParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPaymentDetailsUseCase @Inject constructor() : UseCase<PaymentParams, PaymentDetails>() {
    override fun execute(params: PaymentParams): Flow<Either<ViewError, PaymentDetails>> = flow {
        val tax = params.total * 0.1 // 10% of food price
        val driverFee = 50000 // dummy
        val totalWithTax = params.total + tax + driverFee
        val paymentDetails = PaymentDetails(
            tax = tax.toLong(),
            totalAmount = totalWithTax.toLong(),
            driverFee = driverFee.toLong()
        )
        emit(Either.Right(paymentDetails))
    }
}