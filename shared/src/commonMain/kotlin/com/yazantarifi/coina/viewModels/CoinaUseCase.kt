package com.yazantarifi.coina.viewModels

abstract class CoinaUseCase<Arguments, Result> {

    abstract fun execute(arguments: Arguments): Result

}