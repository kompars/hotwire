package org.kompars.hotwire.turbo

import kotlinx.html.*

// TODO: remove after support of <template> tag in kotlinx.html

public class TEMPLATE(
    consumer: TagConsumer<*>,
) : CommonAttributeGroupFacadeFlowSectioningContent, HTMLTag(
    tagName = "template",
    consumer = consumer,
    inlineTag = false,
    emptyTag = false,
    initialAttributes = attributesMapOf(),
)

public inline fun HTMLTag.template(crossinline block: TEMPLATE.() -> Unit) {
    return TEMPLATE(consumer).visit(block)
}
