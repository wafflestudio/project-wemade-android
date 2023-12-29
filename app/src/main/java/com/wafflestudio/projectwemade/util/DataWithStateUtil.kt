package com.wafflestudio.projectwemade.util

data class DataWithState<TData, TState>(
    val data: TData,
    val state: TState
)

fun <TData, TState> TData.toDataWithState(state: TState): DataWithState<TData, TState> =
    DataWithState(this, state)

typealias Selectable<T> = DataWithState<T, Boolean>
