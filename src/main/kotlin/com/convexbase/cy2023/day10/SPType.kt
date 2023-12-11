package com.convexbase.cy2023.day10

enum class SPType(val c: Char, val bc: Char) {
    START('S', '\u25C6'),
    VPIPE('|', '\u2502'),
    HPIPE('-', '\u2500'),
    BEND_UP_LEFT('7', '\u2510'),
    BEND_DOWN_LEFT('J', '\u2518'),
    BEND_UP_RIGHT('F', '\u250C'),
    BEND_DOWN_RIGHT('L', '\u2514'),
    GROUND('.', '\u2219');

}
