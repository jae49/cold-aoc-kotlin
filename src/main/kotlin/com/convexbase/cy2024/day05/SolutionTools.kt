package com.convexbase.cy2024.day05

import com.convexbase.CoreUtils

typealias PageRule = Pair<Int,Int>
typealias Pages = List<Int>
typealias RuleMap = Map<Int, List<PageRule>>

enum class LineType {
    PAGERULE,
    PAGELIST,
    UNKNOWN;

    companion object {
        fun hint(line: String): LineType {
            if (line.contains('|')) return PAGERULE
            if (line.contains(',')) return PAGELIST
            return UNKNOWN
        }
    }
}

data class PrintProblem(
    val pageRules: List<PageRule>,
    val pageSections: List<Pages>
) {
    fun getRuleMap():RuleMap {
        val rulemap = mutableMapOf<Int,MutableList<PageRule>>()
        pageRules.forEach { rule ->
            rulemap.getOrDefault(rule.first, mutableListOf<PageRule>()).add(rule)
        }
        return rulemap
    }
}

object SolutionTools {

    fun isValidSection(pages: Pages, rules: List<PageRule>):Boolean {
        val pageMap = pages.mapIndexed { index, value -> value to index }.toMap()
        for (rule in rules) {
            val first = pageMap[rule.first]?: continue
            val second = pageMap[rule.second]?: continue
            if (first > second) return false
        }
        return true
    }

    fun repairInvalid(pages: Pages, rules: List<PageRule>):Pages {
        var pageMap = pages.mapIndexed { index, value -> value to index }.toMap()
        val returnPages = pages.toMutableList()
        for (rule in rules) {
            val first = pageMap[rule.first]?: continue
            val second = pageMap[rule.second]?: continue
            if (first > second) {
                returnPages[second] = rule.first
                returnPages[first] = rule.second
                //println("Fixed using ${rule.first}|${rule.second} = ${returnPages}")
                return returnPages.toList()
            }
        }
        return returnPages.toList()
    }

    fun middle(pages: Pages): Int = pages[pages.size / 2]

    fun getPart1Answer(problem: PrintProblem):Long {
        return problem.pageSections.filter{ isValidSection(it, problem.pageRules) }
            .sumOf { middle(it) }
            .toLong()
    }

    fun fix(pages: Pages, rules: List<PageRule>): Pages {
        var fixed = repairInvalid(pages, rules)
        while (!isValidSection(fixed, rules)) fixed = repairInvalid(fixed, rules)
        return fixed.toList()
    }

    fun getPart2Answer(problem: PrintProblem):Long {
        val invalid = problem.pageSections.filter{ !isValidSection(it, problem.pageRules) }
        val valid = mutableListOf<Pages>()
        for (section in invalid) {
            valid.add(fix(section, problem.pageRules))
        }
        return valid.sumOf { middle(it)}.toLong()
    }


    fun getPrintProblemFromRsrc(rsrc:String):PrintProblem {
        val data = CoreUtils.readLineFromResource(rsrc)
        return getPrintProblem(data)
    }

    fun getPrintProblem(data:String):PrintProblem{
        val lines = data.lines()
        val pageRules = mutableListOf<PageRule>()
        val pageSections = mutableListOf<Pages>()
        for (line in lines) {
            when (LineType.hint(line)) {
                LineType.PAGERULE -> pageRules.add(getPageRuleFromLine(line))
                LineType.PAGELIST -> pageSections.add(getPagesFromLine(line))
                LineType.UNKNOWN -> { } // do nothing
            }
        }
        return PrintProblem(pageRules, pageSections)
    }

    fun getPageRuleFromLine(line: String): PageRule {
        val (a, b) = line.split("|").map(String::toInt)
        return PageRule(a,b)
    }

    fun getPagesFromLine(line: String): Pages {
        return line.split(",").map(String::toInt) as Pages
    }
}