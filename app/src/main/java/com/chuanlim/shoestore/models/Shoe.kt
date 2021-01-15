package com.chuanlim.shoestore.models

/**
 * Storing a Shoe item
 * Removing parcelable from the starter object as Rubric doesn't need it.
 */
data class Shoe(
    var name: String = "unassigned",
    var size: Double = 0.0,
    var company: String = "unassigned",
    var description: String = "unknown"
)