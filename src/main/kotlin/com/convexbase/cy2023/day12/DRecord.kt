package com.convexbase.cy2023.day12

class DRecord(
    val rows: List<DRecordRow>
) {

    val counts: Long
        get() {
            return rows.map {row -> row.getMatchCount()}.sum()
        }
    companion object {
        fun toDRecord(input: List<String>): DRecord {
            return DRecord(
                rows = input.map {row -> DRecordRow.toDRecordRow(row)}
            )
        }

        fun to5XDrecord(input: List<String>):DRecord {
            return DRecord(
                rows = input.map {row -> DRecordRow.to5XDRecordRow(row)}
            )
        }
    }
}