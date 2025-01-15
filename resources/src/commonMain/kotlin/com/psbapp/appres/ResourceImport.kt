package com.psbapp.appres

import gesundheitskiosk.core.resources.generated.resources.Res
import gesundheitskiosk.core.resources.generated.resources.did_not_receive_otp
import gesundheitskiosk.core.resources.generated.resources.email_empty_err
import gesundheitskiosk.core.resources.generated.resources.ic_password_hide
import gesundheitskiosk.core.resources.generated.resources.ic_password_show
import gesundheitskiosk.core.resources.generated.resources.img_placeholder
import gesundheitskiosk.core.resources.generated.resources.incorrect_email_err
import gesundheitskiosk.core.resources.generated.resources.invalid_password_err
import gesundheitskiosk.core.resources.generated.resources.password_and_confirm_password_not_matched_err
import gesundheitskiosk.core.resources.generated.resources.password_empty_err
import gesundheitskiosk.core.resources.generated.resources.satoshi_bold
import gesundheitskiosk.core.resources.generated.resources.satoshi_edium
import gesundheitskiosk.core.resources.generated.resources.satoshi_regular
import gesundheitskiosk.core.resources.generated.resources.send_again


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
    }

    object Font{
        val satoshi_bold = CoreFontRes.satoshi_bold
        val satoshi_medium = CoreFontRes.satoshi_edium
        val satoshi_regular = CoreFontRes.satoshi_regular
    }

    val FileRes = Res

}