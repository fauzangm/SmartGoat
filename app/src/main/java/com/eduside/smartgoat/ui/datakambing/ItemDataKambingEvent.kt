package com.eduside.smartgoat.ui.datakambing


import com.eduside.smartgoat.data.local.db.entities.DatakambingVo

class ItemDataKambingEvent(var datakambing: DatakambingVo) {
    fun itemKategoriClicked(datakambing: DatakambingVo) {
        this.datakambing = datakambing
    }
}