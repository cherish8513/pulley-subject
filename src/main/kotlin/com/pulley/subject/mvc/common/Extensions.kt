package com.pulley.subject.mvc.common

fun <T> T?.assertNotNull(): T {
    return this ?: throw RuntimeException("we are expected not null but is null")
}