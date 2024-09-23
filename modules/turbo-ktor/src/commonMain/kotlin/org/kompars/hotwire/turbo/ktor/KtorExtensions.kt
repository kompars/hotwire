package org.kompars.hotwire.turbo.ktor

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.utils.io.charsets.*
import kotlinx.html.stream.*
import org.kompars.hotwire.turbo.*

public val ContentType.Text.TurboStream: ContentType by lazy {
    ContentType.parse(TURBO_STREAM_CONTENT_TYPE)
}

public val ApplicationRequest.isTurboStream: Boolean
    get() {
        return acceptItems().any { it.value == TURBO_STREAM_CONTENT_TYPE }
    }

public val ApplicationRequest.turboFrame: String?
    get() {
        return header(TURBO_FRAME_HEADER_NAME)
    }

public suspend fun ApplicationCall.respondTurboFrame(
    id: String? = null,
    status: HttpStatusCode? = null,
    block: TurboFrame.() -> Unit,
) {
    respondText(status = status, contentType = ContentType.Text.Html.withCharset(Charsets.UTF_8)) {
        buildString {
            appendHTML().turboFrame(id = id, block)
        }
    }
}

public suspend fun ApplicationCall.respondTurboStream(
    status: HttpStatusCode? = null,
    block: TurboStreamBuilder.() -> Unit,
) {
    respondText(
        text = buildTurboStream(block),
        status = status,
        contentType = ContentType.Text.TurboStream.withCharset(Charsets.UTF_8),
    )
}
