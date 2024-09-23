package org.kompars.hotwire.turbo

import kotlinx.html.*
import kotlinx.html.attributes.*

private val turboTrackAttribute = EnumAttribute(TurboTrack.entries.associateBy { it.realValue })
private val booleanAttribute = BooleanAttribute()

@Suppress("EnumEntryName")
public enum class TurboTrack(override val realValue: String) : AttributeEnum {
    reload("reload"),
    dynamic("dynamic"),
}

public var FlowContent.turboTrack: TurboTrack
    get() = turboTrackAttribute[this, "data-turbo-track"]
    set(track) {
        turboTrackAttribute[this, "data-turbo-track"] = track
    }

public var FlowContent.turboPermanent: Boolean
    get() = booleanAttribute[this, "data-turbo-permanent"]
    set(track) {
        booleanAttribute[this, "data-turbo-permanent"] = track
    }

public fun HtmlHeadTag.metaTurboRoot(path: String) {
    meta(name = "turbo-root", content = path)
}

public fun HtmlHeadTag.metaCspNonce(nonce: String) {
    meta(name = "csp-nonce", content = nonce)
}

public fun HtmlHeadTag.metaTurboPrefetch(prefetch: Boolean) {
    meta(name = "turbo-prefetch", content = prefetch.toString())
}

public enum class TurboCacheControl(override val realValue: String) : AttributeEnum {
    NoCache("no-cache"),
    NoPreview("no-preview"),
}

public fun HtmlHeadTag.metaTurboCacheControl(cacheControl: TurboCacheControl) {
    meta(name = "turbo-cache-control", content = cacheControl.realValue)
}

public enum class TurboRefreshMethod(override val realValue: String) : AttributeEnum {
    Replace("replace"),
    Morph("morph"),
}

public fun HtmlHeadTag.metaTurboRefreshMethod(refreshMethod: TurboRefreshMethod) {
    meta(name = "turbo-refresh-method", content = refreshMethod.realValue)
}

public enum class TurboRefreshScroll(override val realValue: String) : AttributeEnum {
    Preserve("preserve"),
    Reset("reset"),
}

public fun HtmlHeadTag.metaTurboRefreshScroll(refreshScroll: TurboRefreshScroll) {
    meta(name = "turbo-refresh-scroll", content = refreshScroll.realValue)
}

public enum class TurboVisitControl(override val realValue: String) : AttributeEnum {
    Reload("reload"),
}

public fun HtmlHeadTag.metaTurboVisitControl(visitControl: TurboVisitControl) {
    meta(name = "turbo-visit-control", content = visitControl.realValue)
}
