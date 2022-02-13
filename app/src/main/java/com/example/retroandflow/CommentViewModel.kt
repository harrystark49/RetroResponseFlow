package com.example.retroandflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CommentViewModel:ViewModel() {
    var commentrepo=commentRepository(AppConfig.ApiService())

    var commentstate= MutableStateFlow(
        CommentApiState(Status.LOADING,CommentModel(),"")
    )

    init {
        getNewComment(1)
    }

     fun getNewComment(id: Int) {
        commentstate.value= CommentApiState.loading()
        viewModelScope.launch {
            commentrepo.getComment(id)
                .catch {
                    commentstate.value=CommentApiState.error(it.message.toString())
                }
                .collect {
                    commentstate.value = CommentApiState.success(it.data)
                }

        }
    }
}