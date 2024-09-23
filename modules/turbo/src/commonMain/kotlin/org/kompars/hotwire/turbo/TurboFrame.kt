package org.kompars.hotwire.turbo

import kotlinx.html.*
import kotlinx.html.attributes.*

private val stringAttribute = StringAttribute()
private val booleanAttribute = BooleanAttribute()

private val turboFrameLoadingAttribute = EnumAttribute(TurboFrameLoading.entries.associateBy { it.realValue })
private val turboActionAttribute = EnumAttribute(TurboAction.entries.associateBy { it.realValue })

public const val TURBO_FRAME_HEADER_NAME: String = "Turbo-Frame"

@Suppress("EnumEntryName")
public enum class TurboAction(override val realValue: String) : AttributeEnum {
    replace("replace"),
    advance("advance"),
}

@Suppress("EnumEntryName")
public enum class TurboFrameLoading(override val realValue: String) : AttributeEnum {
    eager("eager"),
    lazy("lazy"),
}

public class TurboFrame(
    consumer: TagConsumer<*>,
    initialAttributes: Map<String, String> = emptyMap(),
) : CommonAttributeGroupFacadeFlowSectioningContent, HTMLTag(
    tagName = "turbo-frame",
    consumer = consumer,
    inlineTag = false,
    emptyTag = false,
    initialAttributes = initialAttributes,
)

public var TurboFrame.id: String
    get() = stringAttribute[this, "id"]
    set(id) {
        stringAttribute[this, "id"] = id
    }

public var TurboFrame.target: String
    get() = stringAttribute[this, "target"]
    set(target) {
        stringAttribute[this, "target"] = target
    }

public var TurboFrame.src: String
    get() = stringAttribute[this, "src"]
    set(src) {
        stringAttribute[this, "src"] = src
    }

public var TurboFrame.loading: TurboFrameLoading
    get() = turboFrameLoadingAttribute[this, "loading"]
    set(loading) {
        turboFrameLoadingAttribute[this, "loading"] = loading
    }

public var TurboFrame.disabled: Boolean
    get() = booleanAttribute[this, "disabled"]
    set(disabled) {
        booleanAttribute[this, "disabled"] = disabled
    }

public var TurboFrame.autoScroll: Boolean
    get() = booleanAttribute[this, "autoscroll"]
    set(autoscroll) {
        booleanAttribute[this, "autoscroll"] = autoscroll
    }

public var TurboFrame.turboAction: TurboAction
    get() = turboActionAttribute[this, "data-turbo-action"]
    set(turboAction) {
        turboActionAttribute[this, "data-turbo-action"] = turboAction
    }

public inline fun FlowContent.turboFrame(id: String? = null, crossinline block: TurboFrame.() -> Unit) {
    TurboFrame(consumer, attributesMapOf("id", id)).visit(block)
}

public inline fun TagConsumer<*>.turboFrame(id: String? = null, crossinline block: TurboFrame.() -> Unit) {
    TurboFrame(this, attributesMapOf("id", id)).visitAndFinalize(this, block)
}

public var A.turboFrame: String
    get() = stringAttribute[this, "data-turbo-frame"]
    set(turboFrame) {
        stringAttribute[this, "data-turbo-frame"] = turboFrame
    }

public var BUTTON.turboFrame: String
    get() = stringAttribute[this, "data-turbo-frame"]
    set(turboFrame) {
        stringAttribute[this, "data-turbo-frame"] = turboFrame
    }

public var A.turboAction: TurboAction
    get() = turboActionAttribute[this, "data-turbo-action"]
    set(turboAction) {
        turboActionAttribute[this, "data-turbo-action"] = turboAction
    }
