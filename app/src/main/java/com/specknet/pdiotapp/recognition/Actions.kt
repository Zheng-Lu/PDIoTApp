package com.specknet.pdiotapp.recognition

enum class Action(val action: String) {
    ASCENDING_STAIRS("Ascending stairs"),
    DESCENDING_STAIRS("Descending stairs"),
    LYING_DOWN_BACK("Lying down back"),
    LYING_DOWN_ON_LEFT("Lying down on left"),
    LYING_DOWN_ON_STOMACH("Lying down on stomach"),
    LYING_DOWN_ON_RIGHT("Lying down on right"),
    MISCELLANEOUS_MOVEMENTS("Miscellaneous movements"),
    NORMAL_WALKING("Normal walking"),
    RUNNING("Running"),
    SHUFFLE_WALKING("Shuffle walking"),
    SITTING("Sitting"),
    STANDING("Standing"),
    LOADING("Loading")
}