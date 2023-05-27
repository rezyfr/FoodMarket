package com.rezyfr.foodmarket.core.domain.utils

sealed class Either<out L, out R> {

    internal abstract val isRight: Boolean
    internal abstract val isLeft: Boolean

    /** * Represents the fail side of [Either] class which by convention is a "Failure". */
    data class Left<out L>(val data: L) : Either<L, Nothing>() {
        override val isRight = false
        override val isLeft = true
    }

    /** * Represents the success side of [Either] class which by convention is a "Success". */
    data class Right<out R>(val data: R) : Either<Nothing, R>(){
        override val isRight = true
        override val isLeft = false
    }


    fun <L> fail(a: L) = Left(a)
    fun <R> success(b: R) = Right(b)

    fun <C>fold(ifLeft: (L) -> C, ifRight: (R) -> C): C =
        when (this) {
            is Left -> ifLeft(data)
            is Right -> ifRight(data)
        }
}
fun <R> R.right(): Either<Nothing, R> = Either.Right(this)

fun <L> L.left(): Either<L, Nothing> = Either.Left(this)