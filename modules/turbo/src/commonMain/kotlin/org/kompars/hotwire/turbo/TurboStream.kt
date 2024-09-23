package org.kompars.hotwire.turbo

import kotlinx.html.*
import kotlinx.html.attributes.*
import kotlinx.html.stream.*

private val stringAttribute = StringAttribute()
private val booleanAttribute = BooleanAttribute()

public const val TURBO_STREAM_CONTENT_TYPE: String = "text/vnd.turbo-stream.html"

public class TurboStream(
    consumer: TagConsumer<*>,
    initialAttributes: Map<String, String> = emptyMap(),
) : CommonAttributeGroupFacadeFlowSectioningContent, HTMLTag(
    tagName = "turbo-stream",
    consumer = consumer,
    inlineTag = false,
    emptyTag = false,
    initialAttributes = initialAttributes,
)

public var TurboStream.action: String
    get() = stringAttribute[this, "action"]
    set(action) {
        stringAttribute[this, "action"] = action
    }

public var TurboStream.target: String
    get() = stringAttribute[this, "target"]
    set(target) {
        stringAttribute[this, "target"] = target
    }

public var TurboStream.targets: String
    get() = stringAttribute[this, "targets"]
    set(targets) {
        stringAttribute[this, "targets"] = targets
    }

public var A.turboStream: Boolean
    get() = booleanAttribute[this, "data-turbo-stream"]
    set(turboStream) {
        booleanAttribute[this, "data-turbo-stream"] = turboStream
    }

public inline fun FlowContent.turboStream(
    action: String? = null,
    targets: String? = null,
    target: String? = null,
    crossinline block: TurboStream.() -> Unit = {},
) {
    return TurboStream(consumer, attributesMapOf("action", action, "targets", targets, "target", target)).visit(block)
}

public class TurboStreamSource(
    consumer: TagConsumer<*>,
    initialAttributes: Map<String, String> = emptyMap(),
) : CommonAttributeGroupFacadeFlowSectioningContent, HTMLTag(
    tagName = "turbo-stream-source",
    consumer = consumer,
    inlineTag = false,
    emptyTag = true,
    initialAttributes = initialAttributes,
)

public var TurboStreamSource.src: String
    get() = stringAttribute[this, "src"]
    set(src) {
        stringAttribute[this, "src"] = src
    }

public inline fun FlowContent.turboStreamSource(
    src: String? = null,
    crossinline block: TurboStreamSource.() -> Unit = {},
) {
    return TurboStreamSource(consumer, attributesMapOf("src", src)).visit(block)
}

public class TurboStreamBuilder internal constructor(private val prettyPrint: Boolean = true) {
    private val builder = StringBuilder()

    public fun append(targets: String, block: TEMPLATE.() -> Unit) {
        custom("append", targets) { template(block) }
    }

    public fun prepend(targets: String, block: TEMPLATE.() -> Unit) {
        custom("prepend", targets) { template(block) }
    }

    public fun replace(targets: String, block: TEMPLATE.() -> Unit) {
        custom("replace", targets) { template(block) }
    }

    public fun update(targets: String, block: TEMPLATE.() -> Unit) {
        custom("update", targets) { template(block) }
    }

    public fun remove(targets: String) {
        custom("remove", targets)
    }

    public fun before(targets: String, block: TEMPLATE.() -> Unit) {
        custom("before", targets) { template(block) }
    }

    public fun after(targets: String, block: TEMPLATE.() -> Unit) {
        custom("after", targets) { template(block) }
    }

    public fun refresh() {
        custom("refresh")
    }

    public fun custom(action: String, targets: String? = null, block: TurboStream.() -> Unit = {}) {
        val consumer = builder.appendHTML(prettyPrint)
        val attributes = attributesMapOf("action", action, "targets", targets)

        TurboStream(consumer, attributes).visitAndFinalize(consumer, block)
    }

    override fun toString(): String = builder.toString()
}

public fun buildTurboStream(block: TurboStreamBuilder.() -> Unit): String {
    return TurboStreamBuilder().apply(block).toString()
}
