package com.psbapp.appres

import syncride.kmpcorelib.resources.generated.resources.Res
import syncride.kmpcorelib.resources.generated.resources.did_not_receive_otp
import syncride.kmpcorelib.resources.generated.resources.email_empty_err
import syncride.kmpcorelib.resources.generated.resources.ic_check_white
import syncride.kmpcorelib.resources.generated.resources.ic_password_hide
import syncride.kmpcorelib.resources.generated.resources.ic_password_show
import syncride.kmpcorelib.resources.generated.resources.img_placeholder
import syncride.kmpcorelib.resources.generated.resources.incorrect_email_err
import syncride.kmpcorelib.resources.generated.resources.invalid_password_err
import syncride.kmpcorelib.resources.generated.resources.password_and_confirm_password_not_matched_err
import syncride.kmpcorelib.resources.generated.resources.password_empty_err
import syncride.kmpcorelib.resources.generated.resources.satoshi_bold
import syncride.kmpcorelib.resources.generated.resources.satoshi_edium
import syncride.kmpcorelib.resources.generated.resources.satoshi_regular
import syncride.kmpcorelib.resources.generated.resources.send_again


object CoreRes {
    object String {
        val email_empty_err = CoreStringRes.email_empty_err
        val incorrect_email_err = CoreStringRes.incorrect_email_err
        val invalid_password_err = CoreStringRes.invalid_password_err
        val password_and_confirm_password_not_matched_err = CoreStringRes.password_and_confirm_password_not_matched_err
        val password_empty_err = CoreStringRes.password_empty_err
        val send_again = CoreStringRes.send_again
        val did_not_receive_otp = CoreStringRes.did_not_receive_otp
    }

    object Drawable {
        val ic_password_hide = CoreDrawableRes.ic_password_hide
        val ic_password_show = CoreDrawableRes.ic_password_show
        val img_placeholder = CoreDrawableRes.img_placeholder
        val ic_check_white = CoreDrawableRes.ic_check_white
    }

    object Font{
        val satoshi_bold = CoreFontRes.satoshi_bold
        val satoshi_medium = CoreFontRes.satoshi_edium
        val satoshi_regular = CoreFontRes.satoshi_regular
    }

    val FileRes = Res

}