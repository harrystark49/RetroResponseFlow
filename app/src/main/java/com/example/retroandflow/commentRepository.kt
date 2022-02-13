package com.example.retroandflow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class commentRepository(val apiService: ApiService) {
    suspend fun getComment(id: Int):Flow<CommentApiState<CommentModel>>{
        return flow {
            val res=apiService.getComments(id)
            emit(CommentApiState.success(res))
        }.flowOn(Dispatchers.IO)
    }
}