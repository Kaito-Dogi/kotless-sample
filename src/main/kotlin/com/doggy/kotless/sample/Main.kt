package com.doggy.kotless.sample

import io.kotless.dsl.lang.http.Get

@Get("/")
fun hello(): String = "Hello Server-Side Kotlin Meetup!"
