package ii_collections

import com.google.common.collect.Lists
import com.google.common.collect.Maps
import util.TODO

fun todoTask24(): Nothing = TODO(
    """
        Task 24.
        The function should do the same as '_24_JavaCode.doSomethingStrangeWithCollection'.
        Replace all invocations of 'todoTask24()' with the appropriate code.
    """,
    references = { c: Collection<String> -> _24_JavaCode().doSomethingStrangeWithCollection(c) }
)

fun doSomethingStrangeWithCollection2(collection: Collection<String>): Collection<String>? {
    val groupsByLength = Maps.newHashMap<Int, MutableList<String>>()
    for (s in collection) {
        var strings: MutableList<String>? = groupsByLength[s.length]
        if (strings == null) {
            strings = Lists.newArrayList()
            groupsByLength[s.length] = strings
        }
        strings!!.add(s)
    }

    var maximumSizeOfGroup = 0
    for (group in groupsByLength.values) {
        if (group.size > maximumSizeOfGroup) {
            maximumSizeOfGroup = group.size
        }
    }

    for (group in groupsByLength.values) {
        if (group.size == maximumSizeOfGroup) {
            return group
        }
    }
    return null
}

fun doSomethingStrangeWithCollection(collection: Collection<String>): Collection<String>? {
    val groupsByLength = collection.groupBy { s -> s.length }

    return groupsByLength.values.maxBy { group -> group.count() }
}

