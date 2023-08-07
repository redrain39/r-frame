package com.redrain.sup_base.widgets.list

enum class RefreshState {
    REFRESHING,
    REFRESH_FINISH,
    REFRESH_FINISH_WITH_NO_DATA,
    REFRESH_ERROR,
    LOAD_MORE_FINISH,
    LOAD_MORE_WITH_NO_DATA,
    LOAD_MORE_ERROR,
    NO_DATA,
}