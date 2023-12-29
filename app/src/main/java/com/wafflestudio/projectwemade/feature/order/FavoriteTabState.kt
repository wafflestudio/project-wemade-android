package com.wafflestudio.projectwemade.feature.order

import com.wafflestudio.projectwemade.model.dto.Menu
import com.wafflestudio.projectwemade.util.DataWithState

sealed class FavoriteTabState {
    class Viewing(
        val selectedMenu: DataWithState<Menu?, Int>
        = DataWithState<Menu?, Int>(null, 0)
    ) : FavoriteTabState()
    class Editing(val checkedMenus: Set<Menu> = emptySet()): FavoriteTabState()
}