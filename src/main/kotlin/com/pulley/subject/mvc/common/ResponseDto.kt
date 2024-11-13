package com.pulley.subject.mvc.common

import org.springframework.http.HttpStatus

open class ResponseDto<T>(
    val meta: Meta<T>
) {
    constructor(data: T) : this(
        meta = Meta(
            code = HttpStatus.OK.value().toString(),
            message = "ok",
            data = data
        )
    )
}

data class Meta<T>(
    val code: String,
    val message: String,
    val data: T?,
)