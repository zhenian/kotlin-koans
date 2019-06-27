package iii_conventions

import i_introduction._5_String_Templates.month

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

fun MyDate.toDaySeq() = dayOfMonth + 100 * month + 10000 * year

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.compareTo(other: MyDate) = when{
    year != other.year -> year - other.year
    month != other.month -> month - other.month
    else -> dayOfMonth - other.dayOfMonth
}

operator fun MyDate.plus(interval: TimeInterval):MyDate = addTimeIntervals(interval, 1)

operator fun MyDate.plus(timeIntervals: RepeatedTimeInterval) = addTimeIntervals(timeIntervals.timeInterval, timeIntervals.number)


class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)
enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(time: Int) = RepeatedTimeInterval(this, time)

class DateRange(val start: MyDate, val endInclusive: MyDate)

operator fun DateRange.iterator(): Iterator<MyDate> = DateIterator(start, endInclusive)

operator fun DateRange.contains(d: MyDate): Boolean = when{
    start.toDaySeq() <= d.toDaySeq() && endInclusive.toDaySeq() >= d.toDaySeq() -> true
    else -> false
}


class DateIterator(start: MyDate, private val endInclusive: MyDate) : Iterator<MyDate> {
    var hasNext = start <= endInclusive
    var next = if (hasNext) start else endInclusive
    override fun hasNext(): Boolean = hasNext
    override fun next(): MyDate {
        val result = next
        next = next.nextDay()
        hasNext = next <= endInclusive
        return result
    }
}
