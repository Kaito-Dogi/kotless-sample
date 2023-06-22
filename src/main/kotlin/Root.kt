import io.kotless.dsl.lang.http.Get

@Get("/")
fun root(): String = "Hello Server-Side Kotlin Meetup!"
