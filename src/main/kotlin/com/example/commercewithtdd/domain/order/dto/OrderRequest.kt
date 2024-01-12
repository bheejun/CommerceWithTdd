package com.example.commercewithtdd.domain.order.dto

import com.example.commercewithtdd.domain.user.model.User

data class OrderRequest(
    var userId : Long,
    var productIdList : MutableList<Long>,



)
