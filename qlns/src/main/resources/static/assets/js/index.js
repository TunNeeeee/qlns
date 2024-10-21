/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:50 PM - 28/09/2024
 *  User: lam-nguyen
 **/

$(document).ready(function () {
    $(".notify").click(function () {
        const img = $(this).find("img");
        if (img.hasClass("active")) {
            img.removeClass("active");
            img.css({transform: "rotate(00deg)"})
            return;
        }

        img.css({transform: "rotate(180deg)"})
        img.addClass("active");
    })

    const menuItems = $(".menu-item");
    menuItems.click(function () {
        const indexFrame = $(this).data("index-frame-target");
        $(".menu-item").removeClass("active")
        $(this).addClass("active");
        $(".frame-data").css({transform: ""}).hide()
        $(`.frame-data[data-frame=${indexFrame}]`).show().css({transform: "translateY(-300px)"})
    })
    menuItems.eq(4).click()

    $("#signature-management-category li").click(function () {
        $("#signature-management-category li").removeClass("active")
        $(this).addClass("active")
    })

    loadFrameTable("#table-e-signature-management");
})


