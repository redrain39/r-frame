package com.redrain.sup_base.data.repository.impls

import com.redrain.sup_base.data.common.DataState
import com.redrain.sup_base.data.repository.interfaces.IBaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

abstract class BaseRepository : IBaseRepository {

    fun <T> launch(
        onLaunch: suspend (flowCollector: FlowCollector<DataState<T>>) -> Unit,
        onError: suspend (e: Exception) -> Unit = {},
        onComplete: suspend () -> Unit ={}
    ): Flow<DataState<T>> = flow {
        try {
            emit(DataState.Loading)
            onLaunch(this)
        } catch (e: Exception) {
            onError(e)
            emit(DataState.Error)
        } finally {
            onComplete()
        }
    }.catch {
        emit(DataState.Error)
    }
}