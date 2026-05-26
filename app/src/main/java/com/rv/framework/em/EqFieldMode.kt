package com.rv.framework.em

enum class EqFieldMode(val code: Int) {
    OtherMode(0),
    FrontMode(1),
    CenterMode(2),
    RearMode(3);
    companion object {
        fun fromCode(code: Int): EqFieldMode {
            return values().find { it.code == code } ?: OtherMode
        }
    }
}
