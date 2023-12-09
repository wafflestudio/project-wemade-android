package com.wafflestudio.projectwemade.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.wafflestudio.projectwemade.R
import com.wafflestudio.projectwemade.exception.IncorrectPasswordException
import com.wafflestudio.projectwemade.exception.UserNotFoundException
import com.wafflestudio.projectwemade.exception.UsernameDuplicatedException

fun launchApi(      //TODO: 단순하게 개선 필요
    context: Context,
    onError: () -> Unit = {},
    onSuccess: () -> Unit = {},
    api: () -> Unit,
) {
    runCatching {
        api()
    }.onFailure { error ->      //TODO: catch 안되는 거 고치기
        Handler(Looper.getMainLooper())
            .post {
                when (error) {
                    is UsernameDuplicatedException -> {
                        Toast.makeText(
                            context,
                            context.getString(R.string.error_duplicated_username),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is UserNotFoundException -> {
                        Toast.makeText(
                            context,
                            context.getString(R.string.error_unknown_username),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is IncorrectPasswordException -> {
                        Toast.makeText(
                            context,
                            context.getString(R.string.error_incorrect_password),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        Toast.makeText(
                            context,
                            context.getString(R.string.error_unknown),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                onError()
            }
    }.onSuccess {
        onSuccess()
    }
}