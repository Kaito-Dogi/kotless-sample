import io.kotless.MimeType
import io.kotless.dsl.lang.http.Get

@Get(
  path = "/",
  mime = MimeType.PLAIN,
)
fun hello(): String = "Hello Server-Side Kotlin Meetup!"
