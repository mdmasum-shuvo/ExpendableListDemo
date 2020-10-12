package com.masum.listdemo.model

import java.util.*

class MainModel {
    var id = 0
    var name: String? = null
    var type: String? = null
    var img: String? = null
    var isExpended = false
    var subModels: List<SubModel> = ArrayList()
}