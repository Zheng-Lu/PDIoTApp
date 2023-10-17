package com.specknet.pdiotapp.login

data class User(val id: Int = -1,
                          val username: String = "",
                          val email: String = "",
                          val password: String = "",
                          val isLogined : Boolean = false)