/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 6:34AM - 29/09/2024
 *  User: lam-nguyen
 **/


$(document).ready(function () {
    $(".switch-form").click(function () {
        const formLogin = $(".form-login")
        const formForgetPassword = $(".form-forget-password")

        if (formLogin.hasClass("disable")) {
            displayForm(formLogin, formForgetPassword)
            document.title = "Đăng nhập"
            return
        }

        displayForm(formForgetPassword, formLogin)
        document.title = "Đăng ký"
    })

    $("#action-password").click(function () {
        displayPassword($(this));
    })
})

const displayForm = (formDisplay, formHidden) => {
    formDisplay.removeClass("disable").addClass("active").css({animation: "linear 1s bottom-to-top"})
    formHidden.removeClass("active").addClass("disable").css({animation: "linear 1s top-to-bottom"})
    setTimeout(() => {
        formDisplay.removeClass("z-index-1").addClass("z-index-2")
        formHidden.addClass("z-index-1").removeClass("z-index-2")
    }, 700)
}

const displayPassword = (button) => {
    const img = button.find("img")
    const input = $("#password-login")
    const icon = img.attr("src").toString();
    if (icon.includes("off")) {
        img.attr("src", img.attr("src").replace("-off", "-on"))
        input.attr("type", "text")
        return
    }

    img.attr("src", img.attr("src").replace("-on", "-off"))
    input.attr("type", "password")
}
