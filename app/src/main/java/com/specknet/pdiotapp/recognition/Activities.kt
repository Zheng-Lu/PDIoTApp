package com.specknet.pdiotapp.recognition

enum class Action(val action: String) {

    ASCENDING_STAIRS("Ascending stairs"),
    DESCENDING_STAIRS("Descending stairs"),

    LYING_DOWN_BACK("Lying down back"),
    LYING_DOWN_BACK_HYPERVENTILATING("Lying down back Hyperventilating"),
    LYING_DOWN_BACK_COUGHING("Lying down back Coughing"),
    LYING_DOWN_BACK_OTHER("Lying down back Other"),

    LYING_DOWN_ON_STOMACH("Lying down on stomach"),
    LYING_DOWN_ON_STOMACH_HYPERVENTILATING("Lying down on stomach Hyperventilating"),
    LYING_DOWN_ON_STOMACH_COUGHING("Lying down on stomach Coughing"),
    LYING_DOWN_ON_STOMACH_OTHER("Lying down on stomach Other"),

    LYING_DOWN_ON_LEFT("Lying down on left"),
    LYING_DOWN_ON_LEFT_HYPERVENTILATING("Lying down on left Hyperventilating"),
    LYING_DOWN_ON_LEFT_COUGHING("Lying down on left Coughing"),
    LYING_DOWN_ON_LEFT_OTHER("Lying down on left Other"),

    LYING_DOWN_ON_RIGHT("Lying down on right"),
    LYING_DOWN_ON_RIGHT_HYPERVENTILATING("Lying down on right Hyperventilating"),
    LYING_DOWN_ON_RIGHT_COUGHING("Lying down on right Coughing"),
    LYING_DOWN_ON_RIGHT_OTHER("Lying down on right Other"),

    MISCELLANEOUS_MOVEMENTS("Miscellaneous movements"),
    NORMAL_WALKING("Normal walking"),
    RUNNING("Running"),
    SHUFFLE_WALKING("Shuffle walking"),

    SITTING_OR_STANDING("Sitting/Standing"),
    SITTING_OR_STANDING_HYPERVENTILATING("Sitting/Standing Hyperventilating"),
    SITTING_OR_STANDING_COUGHING("Sitting/Standing Coughing"),
    SITTING_OR_STANDING_OTHER("Sitting/Standing Other"),

    LOADING("Loading")
}