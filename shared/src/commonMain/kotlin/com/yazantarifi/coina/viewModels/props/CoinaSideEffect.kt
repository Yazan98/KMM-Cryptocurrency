package com.yazantarifi.coina.viewModels.props

abstract class CoinaSideEffect<Action: CoinaAction, Result> {

    abstract fun onExecuteSideEffect(newAction: Action): Result

    abstract fun getSideEffectKey(): String

}
