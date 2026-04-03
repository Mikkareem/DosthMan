package com.techullurgy.dosthman.domain.models

import kotlinx.serialization.Serializable

@Serializable
enum class AppHttpStatusCode(val code: Int, val text: String) {
    Continue(100, "Continue"),
    SwitchingProtocols(101, "Switching Protocols"),
    Processing(102, "Processing"),
    OK(200, "OK"),
    Created(201, "Created"),
    Accepted(202, "Accepted"),
    NonAuthoritativeInformation(203, "Non-Authoritative Information"),
    NoContent(204, "No Content"),
    ResetContent(205, "Reset Content"),
    PartialContent(206, "Partial Content"),
    MultiStatus(207, "Multi-Status"),
    MultipleChoices(300, "Multiple Choices"),
    MovedPermanently(301, "Moved Permanently"),
    Found(302, "Found"),
    SeeOther(303, "See Other"),
    NotModified(304, "Not Modified"),
    UseProxy(305, "Use Proxy"),
    SwitchProxy(306, "Switch Proxy"),
    TemporaryRedirect(307, "Temporary Redirect"),
    PermanentRedirect(308, "Permanent Redirect"),
    BadRequest(400, "Bad Request"),
    Unauthorized(401, "Unauthorized"),
    PaymentRequired(402, "Payment Required"),
    Forbidden(403, "Forbidden"),
    NotFound(404, "Not Found"),
    MethodNotAllowed(405, "Method Not Allowed"),
    NotAcceptable(406, "Not Acceptable"),
    ProxyAuthenticationRequired(407, "Proxy Authentication Required"),
    RequestTimeout(408, "Request Timeout"),
    Conflict(409, "Conflict"),
    Gone(410, "Gone"),
    LengthRequired(411, "Length Required"),
    PreconditionFailed(412, "Precondition Failed"),
    PayloadTooLarge(413, "Payload Too Large"),
    RequestURITooLong(414, "Request-URI Too Long"),
    UnsupportedMediaType(415, "Unsupported Media Type"),
    RequestedRangeNotSatisfiable(416, "Requested Range Not Satisfiable"),
    ExpectationFailed(417, "Expectation Failed"),
    UnprocessableEntity(422, "Unprocessable Entity"),
    Locked(423, "Locked"),
    FailedDependency(424, "Failed Dependency"),
    TooEarly(425, "Too Early"),
    UpgradeRequired(426, "Upgrade Required"),
    TooManyRequests(429, "Too Many Requests"),
    RequestHeaderFieldsTooLarge(431, "Request Header Fields Too Large"),
    InternalServerError(500, "Internal Server Error"),
    NotImplemented(501, "Not Implemented"),
    BadGateway(502, "Bad Gateway"),
    ServiceUnavailable(503, "Service Unavailable"),
    GatewayTimeout(504, "Gateway Timeout"),
    HTTPVersionNotSupported(505, "HTTP Version Not Supported"),
    VariantAlsoNegotiates(506, "Variant Also Negotiates"),
    InsufficientStorage(507, "Insufficient Storage"),

    Unknown(1000, "Unknown");

    fun getAppHttpStatus(): AppHttpStatus {
        return when(code) {
            in 100..110 -> AppHttpStatus.BASIC
            in 200..299 -> AppHttpStatus.SUCCESS
            in 300..399 -> AppHttpStatus.INTERMEDIATE
            in 400..599 -> AppHttpStatus.FAILURE
            else -> AppHttpStatus.UNKNOWN
        }
    }
}