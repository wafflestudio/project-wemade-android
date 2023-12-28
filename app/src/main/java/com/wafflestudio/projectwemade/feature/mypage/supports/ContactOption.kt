package com.wafflestudio.projectwemade.feature.mypage.supports

enum class ContactOption {
    USAGE_INQUIRY,
    SERVICE_INCONVENIENCE,
    SUGGESTION,
    ORDER_RELATED,
    ETC, ;

    override fun toString(): String {
        return when (this) {
            USAGE_INQUIRY -> "이용문의"
            SERVICE_INCONVENIENCE -> "서비스 불편 신고"
            SUGGESTION -> "건의사항"
            ORDER_RELATED -> "주문 관련"
            ETC -> "기타"
        }
    }
}