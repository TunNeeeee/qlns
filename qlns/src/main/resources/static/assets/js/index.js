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
            img.css({transform: "rotate(0deg)"})
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
        repaintPaintTableHaveMaxRow("#table-contract") //chỉ áp dụng khi đặt thuộc tính "data-max-row"; chỉnh sửa lại kích thước của khung chỉ để hiển thị đúng với max-row đã xét.
        repaintPaintTableHaveMaxRow("#table-e-signature-management") //chỉ áp dụng khi đặt thuộc tính "data-max-row"; chỉnh sửa lại kích thước của khung chỉ để hiển thị đúng với max-row đã xét.
    })

    /*
    ** Menu sẽ được trọn khi f5
    */
     menuItems.eq(4).click()
    var currentPage = window.location.hash.substring(1)
    if(currentPage.length != 0) {
        var button = $(`a.menu-item[href='#${currentPage}']`);
        if(button.length == 0) menuItems.eq(0).click()
        else $(`a.menu-item[href='#${currentPage}']`).click()
    } else
        menuItems.eq(0).click()

    $("#signature-management-category li a").click(function () {
        $("#signature-management-category li a").removeClass("active")
        $(this).addClass("active")
    })

    loadFrameTable("#table-e-signature-management"); // chỉ áp dụng cho table có class là ln-table; dựng khung cho table
    loadFrameTable("#table-contract");  // chỉ áp dụng cho table có class là ln-table; dựng khung cho table
    loadFrameTable("#table-form");
    repaintTable("#table-e-signature-management") //chỉnh sửa khung dữ liệu (tbody) cho phù hợp với table
    repaintTable("#table-contract") //chỉnh sửa khung dữ liệu (tbody) cho phù hợp với table
    repaintTable("#table-form")



    /*
         // bắt event cho button có class là sortable và đang nhất sort.
         $(".btn-sort").on("onSorted", function(e){
            var status = e.detail.status; // trạng thái của nút sort
            console.log(e.detail.status)
         })
    */
})


